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
import lib.DynamicTable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import controlleur.*;
import vue.EView;
import vue.EImage;

/**
 * Controlleur de la page consultation
 */
public class Consultation {
    
    @FXML BorderPane bbPane;
    @FXML AnchorPane tablePane;

    /**
     * Bouton de déconnection
     */
    public void btDeconnection () {
        App.disconnectUser();
    }

    /**
     * Bouton pour ouvrir le préférences
     */
    public void btPreference () {
        controlleur.App.openPreferences();
    }

    /**
     * Bouton de retour en arriére
     */
    public void btRetour () {
        ViewSwitcher.switchTo(EView.MENU);
    }

    /**
     * Bouton de recherche avancée
     */
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

    /**
     * Affichage de la table Observation+Obs_Batracien
     */
    public void btBatracien () {
        this.showTable(
            "SELECT * FROM Observation, Obs_Batracien WHERE idObs = obsB"
        );
    }

    /**
     * Affichage de la table Observation+Obs_Chouette
     */
    public void btChouette () {
        this.showTable(
            "SELECT * FROM Observation, Obs_Chouette WHERE idObs = numObs"
        );
    }

    /**
     * Affichage de la table Observation+Obs_GCI
     */
    public void btGCI () {
        this.showTable(
            "SELECT * FROM Observation, Obs_GCI WHERE idObs = obsG"
        );
    }

    /**
     * Affichage de la table Observation+Obs_Hippocampe
     */
    public void btHippocampe () {
        this.showTable(
            "SELECT * FROM Observation, Obs_Hippocampe WHERE idObs = obsH"
        );
    }

    /**
     * Affichage de la table Observation+Obs_Loutre
     */
    public void btLoutre () {
        this.showTable(
            "SELECT * FROM Observation, Obs_Loutre WHERE idObs = obsL"
        );
    }

    /**
     * Méthode d'affichage du résultat d'une requéte SQL
     * @param SQL la requéte SQL
     */
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