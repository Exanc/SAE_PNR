package controlleur.saisie_espece;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.AccessibleRole;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import controlleur.NumericField;

import modele.donnee.ContenuNid;

/**
 * Formulaire de saisie de ObsGCI
 */
public class SaisieGCI {
    
    @FXML TextField fObservateurs, fNombre;
    @FXML DatePicker dpDate;
    @FXML Spinner<Integer> sHeure, sMinutes;
    @FXML ComboBox cbContenuDuNid;

    /**
     * Initiasation du formulaire
     */
    public void initialize () {
        cbContenuDuNid.setItems(FXCollections.observableArrayList(ContenuNid.values()));

        fNombre.textProperty().addListener(NumericField.onlyDigit(fNombre));
        sHeure.setAccessibleRole(AccessibleRole.INCREMENT_BUTTON);
    }
}
