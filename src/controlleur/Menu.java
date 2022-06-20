package controlleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import vue.EView;

/**
 * Controlleur de la page Menu
 */
public class Menu
{
    @FXML
    public void deconnexion () {
        App.disconnectUser();
    }

    public void switchToConsult () {
        ViewSwitcher.switchTo(EView.CONSULTATION);
    }

    public void switchToSaisir () {
        ViewSwitcher.switchTo(EView.SAISIE);
    }

    public void switchToAdmin () {
        ViewSwitcher.switchTo(EView.ADMIN_CONSOLE);
    }

    public void switchToImport () {
        ViewSwitcher.switchTo(EView.IMPORTATION);
    }
}
