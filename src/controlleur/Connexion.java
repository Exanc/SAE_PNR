package controlleur;

import java.sql.SQLException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import modele.traitement.*;
import vue.EView;

import lib.INIFile;

/**
 * Controlleur de la page Connexion
 */
public class Connexion
{   
    @FXML private TextField fUsername, fAddress;
    @FXML private PasswordField fPassword;
    @FXML private Label fErrorField;
    @FXML private CheckBox cbRememberMe;

    public static Connexion Instance;

    public Connexion () {
        Instance = this;
        Platform.runLater(new Runnable() {
            public void run () {
                controlleur.Connexion.Instance.init();
            }
        });
    }

    /**
     * Initialisation de l'interface
     */
    public void init() {
        cbRememberMe.setSelected(getRememberMe());
        if (!getUsername().isEmpty())
            fUsername.setText(getUsername());
    }

    /**
     * Bouton de connexion
     */
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

                setRememberMe(cbRememberMe.isSelected());

                if (cbRememberMe.isSelected())
                    setUsername(fUsername.getText());
                else
                    setUsername("");

                ViewSwitcher.switchTo(EView.MENU);

            } catch (SQLException e) {

                if (e.getMessage().contains("The driver has not received any packets from the server.")) {
                    fErrorField.setText("Le serveur n'existe pas ou est injoignable.");
                    ErrorHandler.show("Le serveur n'existe pas ou est injoignable", 
                        "Le serveur à l'addresse renseigné, n'existe pas ou est hors ligne", e);

                } else if (e.getMessage().contains("Access denied for user")) {
                    fErrorField.setText("Accès refusé");
                    ErrorHandler.show("Accès refusé", 
                        "L'accès à la base de donnée à été réfusé, verfiez vos permissions auprès d'un administrateur", e);

                } else {
                    fErrorField.setText(e.getMessage());
                    ErrorHandler.show("Erreur", 
                        e.getMessage(), e);
                }
            }

        } else if (user.trim().isEmpty() || password.trim().isEmpty())
            fErrorField.setText("Mot de passe ou nom d'utilisateur non renseigner.");
            ErrorHandler.show("Mot de passe ou nom d'utilisateur non renseigner", 
                "Le nom d'utilisateur ou le mots de passe est vide ou non renseigné", new IllegalArgumentException());
        
        if (!valid) return;
    }

    /* --- Gestion des préférences --- */

    private static final String file = "src/vue/assets/conf.ini";

    /**
     * Récuppére l'option "ce souvenir de moi"
     * @return true, s'il faut ce souvenir de l'utilisateur
     */
    public static boolean getRememberMe () {
        INIFile conf = new INIFile (file);
        return conf.getBooleanProperty("user", "remember_me");
    }

    /**
     * Définit l'option "ce souvenir de moi"
     * @param remember_me true, s'il faut ce souvenir de l'utilisateur
     */
    public static void setRememberMe (boolean remember_me) {
        INIFile conf = new INIFile (file);
        conf.setBooleanProperty("user", "remember_me", remember_me, null);
        conf.save();
    }

    /**
     * Récupére le nom du dernier utilisateur
     * @return nom
     */
    public static String getUsername () {
        INIFile conf = new INIFile (file);
        return conf.getStringProperty("user", "username");
    }

    /**
     * Définit le nom du dernier utilisateur
     * @param username nom
     */
    public static void setUsername (String username) {
        INIFile conf = new INIFile (file);
        conf.setStringProperty("user", "username", username, null);
        conf.save();
    }
}