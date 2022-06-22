package controlleur;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.fxml.FXML;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import controlleur.*;

public class AdminConsole {
    
    /* TODO: Pourquoi pas transformer ça en popup dans 
     * la partie admin_consultation ?
    */

    @FXML private Label lConsole, lInfoPosition;
    @FXML private TextArea fCommandes;

    public void btExecuter () {
        // TODO: Rafiner umpeut la chose quand le medium de sortie serat mieux
        lConsole.setText(modele.traitement.SQLQuerys.executeSQLScript(fCommandes.getText()));

    }

    public void btExecuterSelection () {
        // TODO: c quoi ?
    }

    public void btRetour () {
        ViewSwitcher.switchTo(vue.EView.MENU);
    }

    public void btPreference () {
        App.openPreferences();
    }

    public void btUtilisateurs () {
        ViewSwitcher.switchTo(vue.EView.ADMIN_UTILISATEUR);
    }

    public void btBDD () {
        ViewSwitcher.switchTo(vue.EView.ADMIN_CONSULTATION);
    }

    public void btDeconnexion () {
        controlleur.App.disconnectUser();
    }
}
