package controlleur;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import modele.traitement.*;
import vue.EView;
import modele.donnee.*;

/**
 * Controlleur de la page Connexion
 */
public class Connexion
{
    @FXML
    private TextField fUsername, fPassword, fAddress;

    @FXML
    private Label lError;

    @FXML
    public void connectAction () {

        String user = fUsername.getText();
        String password = fPassword.getText();
        String url = fAddress.getText();
        Boolean valid = true;

        modele.traitement.ConnectionFactory.setProperties(user, password, null);

        try {
            System.out.println(ConnectionFactory.getConnectionFactory().getConnection());
        } catch (SQLException e) {
            lError.setText("La connexion à échouée.");
            valid = false;
        }
        if (!valid) return;

        // TODO: verif des droits pour pas tous afficher
        ViewSwitcher.switchTo(EView.MENU);
    }
}