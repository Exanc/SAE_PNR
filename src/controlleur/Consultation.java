package controlleur;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Optional;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser.ExtensionFilter;
import lib.DynamicTable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import vue.EView;
import vue.EImage;

/**
 * Controlleur de la page consultation
 */
public class Consultation {
    
    @FXML BorderPane bbPane;
    @FXML AnchorPane tablePane;

    private String lastSearch;

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

        Node confirmButton = dialog.getDialogPane().lookupButton(ButtonType.APPLY);
        confirmButton.setDisable(true);
        TextArea text = new TextArea();

        text.textProperty().addListener((observable, oldValue, newValue) -> {
            confirmButton.setDisable(newValue.trim().isEmpty());
        });
        
        dialog.getDialogPane().setContent(text);

        Platform.runLater(() -> text.requestFocus());
        
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
     * Bouton d'exportation
     */
    public void btExporter () {

        if (this.tablePane.getChildren().isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez faire une recherche avant d'exporter.");

            alert.showAndWait();
            return;
        }

        File f = controlleur.ViewSwitcher.invokeFileChooser(new ExtensionFilter("Fichier CSV", "*.csv"));
        
        try {
            if (f.exists())
                    f.delete();

            f.createNewFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            ResultSet result = modele.traitement.SQLQuerys.executeSQL(this.lastSearch);
            
            final String separator = ";";
            ResultSetMetaData metaData = result.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            String headerLine = "";
            
            for (int i = 2; i <= numberOfColumns; i++) {
                String columnName = metaData.getColumnName(i);
                headerLine = headerLine.concat(columnName).concat(separator);
            }
         
            writer.write(headerLine.substring(0, headerLine.length() - 1));
            

            while (result.next()) {
                String line = "";
                 
                for (int i = 2; i <= numberOfColumns; i++) {
                    Object valueObject = result.getObject(i);
                    String valueString = "";
                     
                    if (valueObject != null) valueString = valueObject.toString();
                     
                    if (valueObject instanceof String) {
                        valueString = "\"" + valueString.replaceAll("\"", "\"\"") + "\"";
                    }
                     
                    line = line.concat(valueString);
                     
                    if (i != numberOfColumns) {
                        line = line.concat(separator);
                    }
                }
                 
                writer.newLine();
                writer.write(line);            
            }
            
            writer.close();

        } catch (IOException e) {
            controlleur.ErrorHandler.show("Une érreur est survenue lors de l'écriture du fichier.", e.getMessage(), e);

        } catch (SecurityException e) {
            controlleur.ErrorHandler.show("Erreur",
            "Le programme n'a pas l'autorité sufisante pour remplacer ce fichier.", e);

        } catch (SQLException e) {
            controlleur.ErrorHandler.show("Erreur",
            "La récupération des données a échouée.", e);
        }
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
        table.setId("table");

        this.lastSearch = SQL;

        AnchorPane.setTopAnchor(table, 0.0);
        AnchorPane.setBottomAnchor(table, 0.0);
        AnchorPane.setLeftAnchor(table, 0.0);
        AnchorPane.setRightAnchor(table, 0.0);

        this.tablePane.getChildren().add(table);
    }
}