package controlleur;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuButton;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;

import java.io.File;
import java.io.IOException;

import controlleur.*;
import vue.EView;

public class AdminUtilisateurs {
    
    @FXML private VBox vbListeUtilisateurs;
    @FXML private TextField fAddMemberUsername, fAddMemberPassword;
    @FXML private MenuButton dpAddMemberRole;
    //@FXML private GridPane defaultUser;

    public void btRechercher () {}

    public void btRetour () {
        ViewSwitcher.switchTo(vue.EView.MENU);
    }

    public void btPreference () {
        App.openPreferences();
    }

    public void btComandes () {
        //ViewSwitcher.switchTo(vue.EView.ADMIN_CONSOLE);
        updateList();
    }

    public void btBDD () {
        ViewSwitcher.switchTo(EView.ADMIN_CONSULTATION);
    }

    public void btDeconnection () {
        controlleur.App.disconnectUser();
    }

    public void updateList () {
        try {
            Parent root = FXMLLoader.load(
                new File(EView.DEFAULT_USER.getFileName()).toURI().toURL()
            );

            vbListeUtilisateurs.getChildren().add(root);

        } catch (IOException e) {
            ErrorHandler.show(
                "Une erreur est survenue lors du chargement d'un fichier .fxml", 
                "Le fichier \""+ EView.DEFAULT_USER.getFileName() +"\" n'a pas pu être chargé correctement.", e);
        }
    }
    
}
