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
import modele.donnee.IndiceLoutre;
import modele.donnee.Lieu;
import modele.donnee.Observateur;
import modele.traitement.DataObservateur;
import modele.traitement.SQLQuerys;

public class SaisieLoutre {
    
    @FXML TextField fObservateurs, fPosX, fPosY;
    @FXML DatePicker dpDate;
    @FXML Spinner<Integer> sHeure, sMinutes;
    @FXML ComboBox cbIndice;
    
    /**
     * Initialisation du formulaire
     */
    public void initialize() {
        cbIndice.setItems(FXCollections.observableArrayList(IndiceLoutre.values()));

        fPosX.textProperty().addListener(NumericField.onlyDigit(fPosX));
        fPosY.textProperty().addListener(NumericField.onlyDigit(fPosY));
    }

    /**
     * Methode de sauvegarde du formulaire
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

        IndiceLoutre IEspece = (IndiceLoutre) cbIndice.getValue();

        Date date = new Date(dpDate.getValue().getYear(), dpDate.getValue().getMonthValue(), dpDate.getValue().getDayOfMonth());
        Lieu lieu = new Lieu(Double.parseDouble(fPosX.getText()), Double.parseDouble(fPosY.getText()));
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

        SQL += "INSERT INTO Obs_Loutre (ObsL, commune, lieuDit, indice) ";
        SQL += "VALUES ("+id+", null, null, \'"+IEspece.getValue()+"\' );";

        // TODO: AObserver

        System.out.println(SQL);
        SQLQuerys.executeSQLScript(SQL);
    }
}