package controlleur.saisie_espece;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

<<<<<<< HEAD
import controlleur.NumericField;
import controlleur.ViewSwitcher;
=======
>>>>>>> a4290478da195e2a6fbdd3504c420acd26e94570
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.donnee.EspeceBatracien;
import modele.donnee.Lieu;
import modele.donnee.ObsBatracien;
import modele.donnee.Observateur;
import modele.donnee.TypeObservation;
import modele.traitement.DataObservateur;
import modele.traitement.SQLQuerys;
import vue.EView;

/**
 * Formulaire de saise ObsChouette
 */
public class SaisieChouette {

    @FXML TextField fObservateurs, fPosX, fPosY;
    @FXML Label lSelecId, lSelecEspèce, lSelecSexe;
    @FXML DatePicker dpDate;
    @FXML Spinner<Integer> sHeure, sMinutes;
    @FXML CheckBox chbProtocole;
    @FXML ComboBox cbTypeObservation;

    public static SaisieChouette Instance;
    private Stage popupStage;

    /**
     * Initialisation du formulaire
     */
    public void initialize() {
        cbTypeObservation.setItems(FXCollections.observableArrayList(TypeObservation.values()));
        Instance = this;
    }

    /**
     * Change quel Nid est selectionner
     */
    public void changeSelectedInd (String id, String espece, String sexe) {
        popupStage.close();
        lSelecId.setText(id);
        lSelecSexe.setText(sexe);
        lSelecEspèce.setText(espece);
    }
    
    /**
<<<<<<< HEAD
     * Selection d'un individu
     */
    public void btSelect () {
        this.popupStage = ViewSwitcher.invokePopup(EView.POPUP_SELECTION_CHOUETTE, "SELECT");
    }

    /**
     * Method de sauvegarde du formulaire
=======
     * Methode de sauvegarde du formulaire
>>>>>>> a4290478da195e2a6fbdd3504c420acd26e94570
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
            

        Date date = new Date(dpDate.getValue().getYear(), dpDate.getValue().getMonthValue(), dpDate.getValue().getDayOfMonth());
        Time heure = new Time(sHeure.getValue(), sMinutes.getValue(), 00);

        int id = -1;

        try {
            id = SQLQuerys.getLastObs() + 1;
        } catch (Exception e) {
            controlleur.ErrorHandler.show("Une érreur est survenue.", e.getMessage(), e);
        }

        if (id == -1) return;

        TypeObservation ItypeObs = (TypeObservation) cbTypeObservation.getValue();

        String SQL = "";

        SQL += "INSERT INTO Lieu (coord_Lambert_X, coord_Lambert_Y) ";
        SQL += "VALUES ("+fPosX.getText()+", "+fPosY.getText()+");";

        SQL += "INSERT INTO Observation(idObs, dateObs, heureObs, lieu_Lambert_X, lieu_Lambert_Y) ";
        SQL += "VALUES ("+id+", \'"+date+"\', \'"+heure+"\', "+fPosX.getText()+", "+fPosY.getText()+");";

        SQL += "INSERT INTO Obs_chouette (protocoole, typeObs, leNumIndividu, id) ";
        SQL += "VALUES ("+ (chbProtocole.isSelected() ? 1 : 0) + ", " + ItypeObs.getValue() + ", " + 0 + ", " + id + ");";

        // TODO: AObserver

        System.out.println(SQL);
        SQLQuerys.executeSQLScript(SQL);
    }
}
