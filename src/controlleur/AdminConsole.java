package controlleur;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.ResultSet;

import controlleur.*;

public class AdminConsole {
    
    /* TODO: Pourquoi pas transformer ça en popup dans 
     * la partie admin_consultation ?
    */

    private Label lConsole, lInfoPosition;
    private TextField fCommandes;

    public void btExecuter () {
        ResultSet rs = modele.traitement.DataGeneral.executeSQL(
            lConsole.getText().trim()
        );
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
