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
    Button fAdministrer, fSaisir;

    @FXML
    public void deconnexion () {
        ViewSwitcher.switchTo(EView.CONNEXION);
    }

    public void switchToConsult () {
        ViewSwitcher.switchTo(EView.CONSULTATION);
    }

    public void switchToSaisir () {
        ViewSwitcher.switchTo(EView.SAISIE);
    }

    public void switchToAdmin () {
        //ViewSwitcher.switchTo(EView.Ad);
    }

    public void switchToImport () {
        ViewSwitcher.switchTo(EView.IMPORTATION);
    }
}