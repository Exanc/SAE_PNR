package controlleur.saisie_espece;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import controlleur.NumericField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import modele.donnee.EspeceBatracien;
import modele.donnee.Observateur;
import modele.traitement.DataObservateur;
import modele.traitement.SQLQuerys;

/**
 * Formulaire de saisie ObsBatracien
 */
public class SaisieBatracien {

    @FXML TextField fObservateurs, fNbAdultes, fNbAmplexus, fNbTetards, fNbPontes, fPosX, fPosY;
    @FXML DatePicker dpDate;
    @FXML Spinner<Integer> sHeure, sMinutes;
    @FXML ComboBox cbEspece;

    /**
     * Initialisation du formulaire
     */
    public void initialize() {
        cbEspece.setItems(FXCollections.observableArrayList(EspeceBatracien.values()));

        fNbAdultes.textProperty().addListener(NumericField.onlyDigit(fNbAdultes));
        fNbAmplexus.textProperty().addListener(NumericField.onlyDigit(fNbAmplexus));
        fNbPontes.textProperty().addListener(NumericField.onlyDigit(fNbPontes));
        fNbTetards.textProperty().addListener(NumericField.onlyDigit(fNbTetards));
        fPosX.textProperty().addListener(NumericField.onlyDigit(fPosX));
        fPosY.textProperty().addListener(NumericField.onlyDigit(fPosY));
    }
    
    /**
     * Method de sauvegarde du formulaire
     */
    public void btSave() {
        ArrayList<Observateur> observateurs = new ArrayList<Observateur>();
        ArrayList<Observateur> allObservateurs = new DataObservateur().getAll();
        String[] sObservateurs = fObservateurs.getText().split(",");
        for (String observateur : sObservateurs) {
            Observateur observateur2 = null;
            int i = 0;
            while (observateur2 == null && i < allObservateurs.size()) {
                if (allObservateurs.get(i).getId() == Integer.parseInt(observateur)) observateur2 = allObservateurs.get(i);
                i++;
            }
            observateurs.add(observateur2);
        }
        
        EspeceBatracien IEspece = (EspeceBatracien) cbEspece.getValue();

        Date date = new Date(dpDate.getValue().getYear(), dpDate.getValue().getMonthValue(), dpDate.getValue().getDayOfMonth());
        Time heure = new Time(sHeure.getValue(), sMinutes.getValue(), 00);

        int id = -1;

        try {
            id = SQLQuerys.getLastObs() + 1;
        } catch (Exception e) {
            controlleur.ErrorHandler.show("Une érreur est survenue.", e.getMessage(), e);
        }

        if (id == -1) return;


        String SQL = "";

        SQL += "INSERT INTO Lieu (coord_Lambert_X, coord_Lambert_Y) ";
        SQL += "VALUES ("+fPosX.getText()+", "+fPosY.getText()+");";

        SQL += "INSERT INTO Observation(idObs, dateObs, heureObs, lieu_Lambert_X, lieu_Lambert_Y) ";
        SQL += "VALUES ("+id+", \'"+date+"\', \'"+heure+"\', "+fPosX.getText()+", "+fPosY.getText()+");";

        SQL += "INSERT INTO Obs_Batracien (obsB, espece, nombreAdultes, nombreAmplexus, nombrePonte, nombreTetard, temperature, meteo_ciel, meteo_temp, meteo_vent, meteo_pluie,concerne_ZH,concernes_vege) ";
        SQL += "VALUES ("+id+",\'"+IEspece.getValue()+"\', "+fNbAdultes.getText()+", "+fNbAmplexus.getText()+", "+fNbPontes.getText()+", "+fNbTetards.getText()+", 0, null, null, null, null, 1, 1);";

        // TODO: AObserver

        System.out.println(SQL);
        SQLQuerys.executeSQLScript(SQL);
    }
}
