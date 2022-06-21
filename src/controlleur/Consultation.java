package controlleur;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Optional;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import controlleur.*;
import modele.traitement.DynamicTable;
import vue.EView;
import vue.EImage;

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
    public void btRechercher () {

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Requéte SQL");
        dialog.setHeaderText("Veuillez entrez une requéte SQL de type SELECT.");
        
        try {
            String path = new File(EImage.PICTO_EXPERIMENTATION.getFileName()).toURI().toURL().toString();

            ImageView img = new ImageView(path);
            img.setFitHeight(100.0);
            img.setFitWidth(100.0);

            dialog.setGraphic(img);
        } catch (MalformedURLException e) {
            ErrorHandler.show(
                "Une érreur est survenue lors du chargement d'une image",
                e.getMessage(), e);
        }
        
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);

        // Enable/Disable login button depending on whether a text was entered.
        Node confirmButton = dialog.getDialogPane().lookupButton(ButtonType.APPLY);
        confirmButton.setDisable(true);
        TextArea text = new TextArea();

        // Do some validation
        text.textProperty().addListener((observable, oldValue, newValue) -> {
            confirmButton.setDisable(newValue.trim().isEmpty());
        });
        
        dialog.getDialogPane().setContent(text);

        // Request focus on the username field by default.
        Platform.runLater(() -> text.requestFocus());
        
        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.APPLY) {
                return text.getText();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent())
            this.showTable(result.get());
    }

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