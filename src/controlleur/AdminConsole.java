package controlleur;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.fxml.FXML;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import controlleur.*;

/**
 * Controlleur de la page admin consolle
 */
public class AdminConsole {
    
    /* TODO: Pourquoi pas transformer ça en popup dans 
     * la partie admin_consultation ?
    */

    @FXML private Label lInfoPosition;
    @FXML private TextArea taConsole, fCommandes;

    /**
     * Exécution du script SQL entrée par l'utilisateur
     */
    public void btExecuter () {
        // TODO: Rafiner umpeut la chose quand le medium de sortie serat mieux
        taConsole.setText(modele.traitement.SQLQuerys.executeSQLScript(fCommandes.getText()));

    }

    /**
     * Exécutio selement le texte slectionner ?
     */
    @Deprecated
    public void btExecuterSelection () {}

    /**
     * Bouton de retour en arriére
     */
    public void btRetour () {
        ViewSwitcher.switchTo(vue.EView.MENU);
    }

    /**
     * Bouton d'ouverture de la popup préférences
     */
    public void btPreference () {
        App.openPreferences();
    }

    /**
     * Bouton pour passer à la page admin utilisateurs
     */
    public void btUtilisateurs () {
        ViewSwitcher.switchTo(vue.EView.ADMIN_UTILISATEUR);
    }

    @Deprecated
    public void btBDD () {
        ViewSwitcher.switchTo(vue.EView.ADMIN_CONSULTATION);
    }

    /**
     * Bouton de déconnection
     */
    public void btDeconnexion () {
        controlleur.App.disconnectUser();
    }
}