package controlleur;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TableView;

import controlleur.*;
import vue.EView;

public class Consultation {
    
    @FXML BorderPane bbPane;
    @FXML TableView tableConsultation;

    @FXML
    public void btDeconnection () {
        App.disconnectUser();
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
