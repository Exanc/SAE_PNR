package controlleur;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import modele.traitement.*;
import modele.donnee.*;

/**
 * Controlleur de la page Connexion
 */
public class Connexion
{
    @FXML
    TextField fUsername, fPassword, fAddress;

    @FXML
    public void connectAction () {

        String user = fUsername.getText();
        String password = fPassword.getText();
        String url = fAddress.getText();

        modele.traitement.ConnectionFactory.setProperties(user, password, null);

        try {
            System.out.println(ConnectionFactory.getConnectionFactory().getConnection());
            System.out.println("Connecter à la BDD");

            ArrayList<Lieu> listLieu = new DataLieu().getAll();
            for (Lieu lieu : listLieu) {
                System.out.println(lieu.getXCoord() + " , " + lieu.getYCoord());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}