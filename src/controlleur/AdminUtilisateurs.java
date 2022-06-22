package controlleur;

import javafx.scene.Node;
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
    //@FXML private GridPane defaultUser;

    /**
     * Initialisation de la page
     */
    public void initialize() {
        cbAddMemberRole.setItems(FXCollections.observableArrayList(ERole.values()));
        updateList();
    }

    /**
     * Bouton pour ajouter un utilisateur
     */
    public void btAjouter () {
        if (!fAddMemberUsername.getText().trim().isEmpty() && !fAddMemberPassword.getText().trim().isEmpty() && cbAddMemberRole.getValue() != null) {
            SQLQuerys.addUser(fAddMemberUsername.getText(), fAddMemberPassword.getText(), (ERole) cbAddMemberRole.getValue());
        } else {
            // TODO: Afficher une érreur
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
        //ViewSwitcher.switchTo(vue.EView.ADMIN_CONSOLE);
        //updateList();
    }

    /**
     * Bouton de déconnection
     */
    public void btDeconnection () {
        controlleur.App.disconnectUser();
    }

    /**
     * Ajoute un utilisateur sur la base de données
     * @param username nom
     * @param password mot de passe
     * @param role role dans la base de données
     */
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
    
    /**
     * Méthode d'affichage des utilisateurs sur l'interface
     */
    public void updateList () {
        try {
            ResultSet rs = SQLQuerys.executeSQL("SELECT from_user, to_user FROM mysql.role_edges WHERE from_user IN ('administrator', 'field_man', 'observer');");

            while (rs.next()) {
                FXMLLoader loader = new FXMLLoader(new File(EView.DEFAULT_USER.getFileName()).toURI().toURL());
                Parent root = loader.load();
                DefaultUser c = loader.getController();
                ERole role = null;
                switch (rs.getString(1)) {
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
                c.setUser(rs.getString(2), role);
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
