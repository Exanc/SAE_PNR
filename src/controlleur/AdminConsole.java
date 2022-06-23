package controlleur;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.fxml.FXML;

/**
 * Controlleur de la page admin consolle
 */
public class AdminConsole {
    @FXML private Label lInfoPosition;
    @FXML private TextArea taConsole, fCommandes;

    /**
     * Exécution du script SQL entrée par l'utilisateur
     */
    public void btExecuter () {
        taConsole.setText(modele.traitement.SQLQuerys.executeSQLScript(fCommandes.getText()));

    }
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

    /**
     * Bouton de déconnection
     */
    public void btDeconnexion () {
        controlleur.App.disconnectUser();
    }
}