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
        modele.traitement.ConnectionFactory.setProperties("", "", null);
        ViewSwitcher.switchTo(EView.CONNEXION);
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
    }

    @FXML
    public void btChouetteAction () {
        setSaisieOf(EView.SAISIE_CHOUETTE);
    }

    @FXML
    public void btGCIAction () {
        setSaisieOf(EView.SAISIE_GCI);
    }

    @FXML
    public void btHippocampeAction () {
        setSaisieOf(EView.SAISIE_HIPPOCAMPE);
    }

    @FXML
    public void btLoutreAction () {
        setSaisieOf(EView.SAISIE_LOUTRE);
    }

    private void setSaisieOf(EView view) {
        try {
            Parent root = FXMLLoader.load(new File(view.getFileName()).toURI().toURL());
            bbPane.setCenter(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
