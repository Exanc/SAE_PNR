package controlleur;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import modele.traitement.SQLQuerys;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import controlleur.*;
import vue.ERole;
import vue.EView;

/**
 * Controlleur de la page admin utilisateurs
 */
public class AdminUtilisateurs {
    
    @FXML private VBox vbListeUtilisateurs;
    @FXML private TextField fAddMemberUsername, fAddMemberPassword;
    @FXML private ComboBox cbAddMemberRole;

    /**
     * Initialisation de la page
     */
    public void initialize() {
        cbAddMemberRole.setItems(FXCollections.observableArrayList(ERole.values()));
        cbAddMemberRole.setPromptText("Role");
        cbAddMemberRole.setButtonCell(new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty) ;
                if (empty || item == null) setText("Role");
                else setText(item);
            }
        });
        updateList();
    }

    /**
     * Bouton pour ajouter un utilisateur
     */
    public void btAjouter () {
        if (!fAddMemberUsername.getText().trim().isEmpty() && !fAddMemberPassword.getText().trim().isEmpty() && cbAddMemberRole.getValue() != null) {
            SQLQuerys.addUser(fAddMemberUsername.getText(), fAddMemberPassword.getText(), (ERole) cbAddMemberRole.getValue());
            fAddMemberUsername.setText("");
            fAddMemberPassword.setText("");
            cbAddMemberRole.getSelectionModel().clearSelection();
            cbAddMemberRole.setValue(null);
            cbAddMemberRole.setPromptText("Role");
            updateList();
            // TODO: POPUP L'UTILISATEUR A ETE AJOUTE
        } else {
            ErrorHandler.show("L'utilisateur n'a pas pu être ajouter", 
            "Les paramètres sont invalide", null);
        }
    }

    /**
     * Bouton de retour en arriére
     */
    public void btRetour () {
        ViewSwitcher.switchTo(vue.EView.MENU);
    }

    /**
     * Bouton pour ouvrir la popup préférences
     */
    public void btPreference () {
        App.openPreferences();
    }

    /**
     * Bouton pour accéder à la page admin console
     */
    public void btComandes () {
        ViewSwitcher.switchTo(vue.EView.ADMIN_CONSOLE);
    }

    /**
     * Bouton de déconnection
     */
    public void btDeconnection () {
        controlleur.App.disconnectUser();
    }
    
    /**
     * Méthode d'affichage des utilisateurs sur l'interface
     */
    public void updateList () {
        try {
            vbListeUtilisateurs.getChildren().clear();
            ResultSet rs = SQLQuerys.executeSQL("SELECT from_user, to_user FROM mysql.role_edges WHERE from_user IN ('administrator', 'field_man', 'observer');");
            while (rs.next()) {
                FXMLLoader loader = new FXMLLoader(new File(EView.DEFAULT_USER.getFileName()).toURI().toURL());
                Parent root = loader.load();
                DefaultUser c = loader.getController();
                ERole role = null;

                String username2 = rs.getString(2);
                String role2 = rs.getString(1);

                switch (role2) {
                    case "administrator":
                        role = ERole.ADMINISTRATEUR;
                        break;
                    case "field_man":
                        role = ERole.HOMME_DE_TERRAIN;
                        break;
                    case "observer":
                        role = ERole.CONSULTANT;
                        break;
                }
                c.setUser(username2, role, this);
                vbListeUtilisateurs.getChildren().add(root);
            }
        } catch (IOException e) {
            ErrorHandler.show(
                "Une erreur est survenue lors du chargement d'un fichier .fxml", 
                "Le fichier \""+ EView.DEFAULT_USER.getFileName() +"\" n'a pas pu être chargé correctement.", e);
        } catch (SQLException e) {
            ErrorHandler.show(
                "Une erreur est survenue lors du traitement de la requête", 
                "Le résultat de la requête n'a pas pu être traité correctement.", e);
        }
    }
}
