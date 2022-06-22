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
import modele.donnee.EspeceHippocampe;
import modele.donnee.Lieu;
import modele.donnee.Observateur;
import modele.donnee.Peche;
import modele.donnee.Sexe;
import modele.traitement.DataObservateur;
import modele.traitement.SQLQuerys;

public class SaisieHippocampe {
    
    @FXML TextField fObservateurs, taille, fPosX, fPosY;
    @FXML DatePicker dpDate;
    @FXML Spinner<Integer> sHeure, sMinutes;
    @FXML ComboBox type_peche, cbEspece, sexe;
    
    /**
     * Initialisation du formulaire
     */
    public void initialize() {
        taille.textProperty().addListener(NumericField.onlyDigit(taille));

        type_peche.setItems(FXCollections.observableArrayList(Peche.values()));
        cbEspece.setItems(FXCollections.observableArrayList(EspeceHippocampe.values()));
        sexe.setItems(FXCollections.observableArrayList(Sexe.values()));


        fPosX.textProperty().addListener(NumericField.onlyDigit(fPosX));
        fPosY.textProperty().addListener(NumericField.onlyDigit(fPosY));
    }

    /**
     * Method de sauvegarde du formulaire
     */
    public void btSave() {
        
        //if (cbEspece.getValue() != null && !fObservateurs.getText().isEmpty() && dpDate.getValue() != null && sHeure.getValue() != null && sMinutes != null) {
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
            
            String laTaille = taille.getText();
            EspeceHippocampe IEspece = (EspeceHippocampe) cbEspece.getValue();
            Peche leTypePeche = (Peche) type_peche.getValue();
            Sexe leSexe = (Sexe) sexe.getValue();
            
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

            SQL += "INSERT INTO Obs_Hippocampe (obsH, espece, sexe, temperatureEau, typePeche, taille, gestant) ";
            SQL += "VALUES ("+id+",\'"+IEspece.getValue()+"\', \'"+leSexe.getValue()+"\', null, \'"+leTypePeche.getValue()+"\', "+taille.getText()+", null);";

            // TODO: AObserver

            System.out.println(SQL);
            SQLQuerys.executeSQLScript(SQL);
    }
}
