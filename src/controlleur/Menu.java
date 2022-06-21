package controlleur;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import modele.traitement.SQLQuerys;
import vue.EView;

/**
 * Controlleur de la page Menu
 */
public class Menu
{
    @FXML
    Button btConsulter, btSaisir, btAdministrer, btImporter;

    public void initialize() {
        int level = 0;

        try {
            level = SQLQuerys.getRole();
        } catch (NumberFormatException e) {
            ErrorHandler.show("Erreur", "Erreur lors de la vérification des rôles", e);
            e.printStackTrace();
        } catch (SQLException e) {
            ErrorHandler.show("Erreur", "Erreur lors de la vérification des rôles", e);
            e.printStackTrace();
        }

        if (level <= 2) {
            btAdministrer.setDisable(true);
        }
        if (level <= 1) {
            btSaisir.setDisable(true);
            btImporter.setDisable(true);
        }
        if (level <= 0) {
            btConsulter.setDisable(true);
        }
    }

    @FXML
    public void deconnexion () {
        App.disconnectUser();
    }

    @FXML
    public void switchToConsult () {
        ViewSwitcher.switchTo(EView.CONSULTATION);
    }

    @FXML
    public void switchToSaisir () {
        ViewSwitcher.switchTo(EView.SAISIE);
    }

    @FXML
    public void switchToAdmin () {
        ViewSwitcher.switchTo(EView.ADMIN_CONSOLE);
    }

    @FXML
    public void switchToImport () {
        ViewSwitcher.switchTo(EView.IMPORTATION);
    }
}
