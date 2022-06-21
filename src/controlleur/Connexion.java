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
                ConnectionFactory.getConnectionFactory().getConnection();
                ViewSwitcher.switchTo(EView.MENU);
                

            } catch (SQLException e) {
                    
                e.printStackTrace();

                if (e.getMessage().contains("The driver has not received any packets from the server.")) {
                    fErrorField.setText("Le serveur n'existe pas ou est injoignable.");
                    ErrorHandler.show("Le serveur n'existe pas ou est injoignable", 
                        "Le serveur à l'addresse renseigné, n'existe pas ou est hors ligne", e);

                } else if (e.getMessage().contains("Access denied for user")) {
                    fErrorField.setText("Accès refusé");
                    ErrorHandler.show("Erreur", "Accès refusé", e);

                } else {
                    fErrorField.setText(e.getMessage());
                    ErrorHandler.show("Erreur", "", e);
                }
            }

        } else if (user.trim().isEmpty() || password.trim().isEmpty())
            fErrorField.setText("Mot de passe ou nom d'utilisateur non renseigner.");
            ErrorHandler.show("Mot de passe ou nom d'utilisateur non renseigner", 
                "Le nom d'utilisateur ou le mots de passe est vide ou non renseigné", null);
        
        if (!valid) return;
    }
}