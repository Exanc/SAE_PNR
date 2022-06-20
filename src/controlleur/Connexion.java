package controlleur;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import modele.traitement.*;
import vue.EView;

/**
 * Controlleur de la page Connexion
 */
public class Connexion
{
    @FXML
    private TextField fUsername, fAddress;

    @FXML
    private PasswordField fPassword;

    @FXML
    private Label fErrorField;

    @FXML
    public void connectAction () {

        String user = fUsername.getText();
        String password = fPassword.getText();
        String url = fAddress.getText();
        Boolean valid = true;

        if (!user.trim().isEmpty() && !password.trim().isEmpty()) {
            modele.traitement.ConnectionFactory.setProperties(user, password, (url.trim().isEmpty() ? null : url));

        try {
            System.out.println(ConnectionFactory.getConnectionFactory().getConnection());
            System.out.println("Connecter à la BDD");

            ViewSwitcher.switchTo(EView.MENU);

        } catch (SQLException e) {
                
            e.printStackTrace();

            if (e.getMessage().contains("The driver has not received any packets from the server.")) fErrorField.setText("Database not exist or is turned off");
            else if (e.getMessage().contains("Access denied for user")) fErrorField.setText("User not exist or he don't have access to the database");
            else fErrorField.setText(e.getMessage());

        }

        } else if (user.trim().isEmpty()) {
            fErrorField.setText("Username field is empty");
        } else if (password.trim().isEmpty()) {
            fErrorField.setText("Password field is empty");
        }
        if (!valid) return;

        // TODO: verif des droits pour pas tous afficher
        ViewSwitcher.switchTo(EView.MENU);
    }
}