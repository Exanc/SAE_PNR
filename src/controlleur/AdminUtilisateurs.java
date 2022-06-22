package controlleur;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import modele.traitement.SQLQuerys;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

import java.io.File;
import java.io.IOException;

import controlleur.*;
import vue.ERole;
import vue.EView;

public class AdminUtilisateurs {
    
    @FXML private VBox vbListeUtilisateurs;
    @FXML private TextField fAddMemberUsername, fAddMemberPassword;
    @FXML private ComboBox cbAddMemberRole;
    //@FXML private GridPane defaultUser;

    public void initialize() {
        cbAddMemberRole.setItems(FXCollections.observableArrayList(ERole.values()));
    }

    public void btAjouter () {
        if (!fAddMemberUsername.getText().trim().isEmpty() && !fAddMemberPassword.getText().trim().isEmpty() && cbAddMemberRole.getValue() != null) {
            SQLQuerys.addUser(fAddMemberUsername.getText(), fAddMemberPassword.getText(), (ERole) cbAddMemberRole.getValue());
        } else {
            // TODO: Erreur
        }
    }

    public void btRetour () {
        ViewSwitcher.switchTo(vue.EView.MENU);
    }

    public void btPreference () {
        App.openPreferences();
    }

    public void btComandes () {
        //ViewSwitcher.switchTo(vue.EView.ADMIN_CONSOLE);
        //updateList();
    }

    public void btBDD () {
        ViewSwitcher.switchTo(EView.ADMIN_CONSULTATION);
    }

    public void btDeconnection () {
        controlleur.App.disconnectUser();
    }

    public void addUser (String username, String password, int role) {
        try {
            Parent root = FXMLLoader.load(
                new File(EView.DEFAULT_USER.getFileName()).toURI().toURL()
            );
            
            ((Label) root.lookup("#text")).setText(username);



            vbListeUtilisateurs.getChildren().add(root);

        } catch (IOException e) {
            ErrorHandler.show(
                "Une erreur est survenue lors du chargement d'un fichier .fxml", 
                "Le fichier \""+ EView.DEFAULT_USER.getFileName() +"\" n'a pas pu être chargé correctement.", e);
        }
    }
    
}
