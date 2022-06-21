package controlleur;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import vue.EView;

public class Saisie {
    @FXML
    BorderPane bbPane;

    @FXML
    Button btBatracien, btChouette, btGCI, btHippocampe, btLoutre;

    @FXML
    public void disconnectAction () {
        controlleur.App.disconnectUser();
    }

    @FXML
    public void btPreference () {
        controlleur.App.openPreferences();
    }

    @FXML
    public void btRetour () {
        ViewSwitcher.switchTo(EView.MENU);
    }

    @FXML
    public void btBatracienAction () {
        setSaisieOf(EView.SAISIE_BATRACIEN);
        resetCSSId();
        this.btBatracien.setId("selected_left_button");
        this.btBatracien.setDisable(true);
    }

    @FXML
    public void btChouetteAction () {
        setSaisieOf(EView.SAISIE_CHOUETTE);
        resetCSSId();
        this.btChouette.setId("selected_left_button");
        this.btChouette.setDisable(true);
    }

    @FXML
    public void btGCIAction () {
        setSaisieOf(EView.SAISIE_GCI);
        resetCSSId();
        this.btGCI.setId("selected_left_button");
        this.btGCI.setDisable(true);
    }

    @FXML
    public void btHippocampeAction () {
        setSaisieOf(EView.SAISIE_HIPPOCAMPE);
        resetCSSId();
        this.btHippocampe.setId("selected_left_button");
        this.btHippocampe.setDisable(true);
    }

    @FXML
    public void btLoutreAction () {
        setSaisieOf(EView.SAISIE_LOUTRE);
        resetCSSId();
        this.btLoutre.setId("selected_left_button");
        this.btLoutre.setDisable(true);
    }

    private void setSaisieOf(EView view) {
        try {
            Parent root = FXMLLoader.load(new File(view.getFileName()).toURI().toURL());
            bbPane.setCenter(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resetCSSId() {
        this.btBatracien.setId("left_button");
        this.btBatracien.setDisable(false);
        this.btChouette.setId("left_button");
        this.btChouette.setDisable(false);
        this.btGCI.setId("left_button");
        this.btGCI.setDisable(false);
        this.btHippocampe.setId("left_button");
        this.btHippocampe.setDisable(false);
        this.btLoutre.setId("left_button");
        this.btLoutre.setDisable(false);
    }
}
