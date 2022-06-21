package controlleur;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.TableView;

import controlleur.*;
import modele.traitement.DynamicTable;
import vue.EView;

public class Consultation {
    
    @FXML BorderPane bbPane;
    @FXML Pane tablePane;

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

        this.tablePane.getChildren().clear();

        DynamicTable t = new DynamicTable(
            "SELECT * FROM Observation, Obs_Batracien WHERE idObs = obsB"
        );     

        this.tablePane.getChildren().add(t.getTable());
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