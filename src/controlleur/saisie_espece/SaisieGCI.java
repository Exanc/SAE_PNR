package controlleur.saisie_espece;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.AccessibleRole;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;
import java.sql.Time;

import controlleur.NumericField;
import controlleur.ViewSwitcher;
import modele.donnee.ContenuNid;
import modele.traitement.SQLQuerys;
import vue.EView;

/**
 * Formulaire de saisie de ObsGCI
 */
public class SaisieGCI {
    
    @FXML TextField fObservateurs, fNombre, fPosX, fPosY;
    @FXML Label lSelectedId, lSelectedPlage;
    @FXML DatePicker dpDate;
    @FXML Spinner<Integer> sHeure, sMinutes;
    @FXML ComboBox cbContenuDuNid;
    @FXML CheckBox cbPresentMaisNonObs;

    public static SaisieGCI Instance;

    public Stage popupStage;

    /**
     * Initiasation du formulaire
     */
    public void initialize () {
        cbContenuDuNid.setItems(FXCollections.observableArrayList(ContenuNid.values()));

        fNombre.textProperty().addListener(NumericField.onlyDigit(fNombre));
        sHeure.setAccessibleRole(AccessibleRole.INCREMENT_BUTTON);

        Instance = this;
    }

    /**
     * Change quel Nid est selectionner
     */
    public void changeSelectedNid (String id, String nomPlage) {
        popupStage.close();
        lSelectedId.setText(id);
        lSelectedPlage.setText(nomPlage);
    }

    /**
     * Selection d'un nid
     */
    public void btPlage () {
        this.popupStage = ViewSwitcher.invokePopup(EView.POPUP_SELECTION_NIDGCI, "SELECT");
    }

    /**
     * Bouton de sauvegarde
     */
    public void btSave () {
        
        int presentMaisNonObs = 0;
        if (cbPresentMaisNonObs.isSelected()) presentMaisNonObs = 1;

        ContenuNid contenuNid = (ContenuNid) cbContenuDuNid.getValue();

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

        SQL += "INSERT INTO Obs_GCI ";
        SQL += "VALUES("+id+", \'"+contenuNid.getValue()+"\', "+fNombre.getText()+", "+presentMaisNonObs+", "+lSelectedId.getText()+");";

        // TODO: AObserver

        System.out.println(SQL);
        SQLQuerys.executeSQLScript(SQL);

    }
}
