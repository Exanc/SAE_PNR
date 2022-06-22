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
import modele.donnee.Lieu;
import modele.donnee.ObsBatracien;
import modele.donnee.Observateur;
import modele.traitement.DataObservateur;
import modele.traitement.SQLQuerys;

/**
 * Formulaire de saise ObsBatracien
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
        if (cbEspece.getValue() != null && !fObservateurs.getText().isEmpty() && dpDate.getValue() != null && sHeure.getValue() != null && sMinutes != null) {
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

            int[] resObs = new int[]{
                Integer.parseInt(fNbAdultes.getText()), 
                Integer.parseInt(fNbAmplexus.getText()), 
                Integer.parseInt(fNbTetards.getText()), 
                Integer.parseInt(fNbPontes.getText())
            };
            Date date = new Date(dpDate.getValue().getYear(), dpDate.getValue().getMonthValue(), dpDate.getValue().getDayOfMonth());
            Lieu lieu = new Lieu(Double.parseDouble(fPosX.getText()), Double.parseDouble(fPosY.getText()));
            Time heure = new Time(sHeure.getValue(), sMinutes.getValue(), 00);

            int id = -1;

            try {
                id = SQLQuerys.getLastObs() + 1;
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (id != -1) {
                ObsBatracien obsBatracien = new ObsBatracien(id, date, heure, lieu, observateurs, resObs, IEspece);
            }
        }
    }
}
