package controlleur;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import vue.EView;

public class Saisie {
    @FXML
    BorderPane bbPane;

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
    public void btBatracien () {
        setSaisieOf(EView.SAISIE_BATRACIEN);
    }
/*
    @FXML
    public void btChouette () {}

    @FXML
    public void btGCI () {}

    @FXML
    public void btHippocampe () {}

    @FXML
    public void btLoutre () {}*/

    private void setSaisieOf(EView view) {
        try {
            Parent root = FXMLLoader.load(new File(view.getFileName()).toURI().toURL());
            bbPane.setCenter(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
