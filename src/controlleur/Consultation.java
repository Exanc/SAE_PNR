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

public class Consultation {
    
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
    public void btCarte () {}

    @FXML
    public void btBatracien () {

    }

    @FXML
    public void btChouette () {}

    @FXML
    public void btGCI () {}

    @FXML
    public void btHippocampe () {}

    @FXML
    public void btLoutre () {}

    //TODO: tables...
}
