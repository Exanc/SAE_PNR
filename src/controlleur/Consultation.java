package controlleur;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableView;

import controlleur.*;
import modele.traitement.DynamicTable;
import vue.EView;

public class Consultation {
    
    @FXML BorderPane bbPane;
    @FXML AnchorPane tablePane;

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
        this.showTable(
            "SELECT * FROM Observation, Obs_Batracien WHERE idObs = obsB"
        );
    }

    @FXML
    public void btChouette () {
        this.showTable(
            "SELECT * FROM Observation, Obs_Chouette WHERE idObs = numObs"
        );
    }

    @FXML
    public void btGCI () {
        this.showTable(
            "SELECT * FROM Observation, Obs_GCI WHERE idObs = obsG"
        );
    }

    @FXML
    public void btHippocampe () {
        this.showTable(
            "SELECT * FROM Observation, Obs_Hippocampe WHERE idObs = obsH"
        );
    }

    @FXML
    public void btLoutre () {
        this.showTable(
            "SELECT * FROM Observation, Obs_Loutre WHERE idObs = obsL"
        );
    }

    public void showTable (String SQL) {
        this.tablePane.getChildren().clear();

        DynamicTable t = new DynamicTable(SQL);
        TableView table = t.getTable();

        AnchorPane.setTopAnchor(table, 0.0);
        AnchorPane.setBottomAnchor(table, 0.0);
        AnchorPane.setLeftAnchor(table, 0.0);
        AnchorPane.setRightAnchor(table, 0.0);

        this.tablePane.getChildren().add(table);
    }
}