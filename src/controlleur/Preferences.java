package controlleur;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import javafx.fxml.FXML;

import java.util.Optional;

import lib.INIFile;

/**
 * Controlleur de la popup préférences
 */
public class Preferences
{
    /* --- Singleton managment --- */

    public static boolean isActive = false;
    public static Preferences Instance;

    public Preferences () {
        Preferences.Instance = this;
        Platform.runLater(new Runnable() {
            public void run () {
                controlleur.Preferences.Instance.init();
            }
        });
    }

    /**
     * Initialisation de l'interface
     */
    public void init () {
        fAddress.setPromptText(getDefaultUrl());
        cbConfirmationRequete.setSelected(getConfirmOnSqlRequest());
    }

    /**
     * Event: Fermeture du popup
     */
    public static final EventHandler<WindowEvent> CLOSER = new EventHandler<WindowEvent>() {
        public void handle(WindowEvent event) {
            
            if (Preferences.Instance.hasChanges()) {
                    
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Souhaitez vous sauvegardez les changements apportés ?");

                ButtonType buttonTypeYes = new ButtonType("OUI");
                ButtonType buttonTypeNo = new ButtonType("NON", ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == buttonTypeYes){
                    Preferences.Instance.btSauvegarder();
                }
            }
            Preferences.isActive = false;
        }
    };

    /**
     * @return true, si il y à eu des changments
     */
    public boolean hasChanges () {
        return   (this.cbConfirmationRequete.isSelected() != getConfirmOnSqlRequest())
              || (!this.fAddress.getText().trim().isEmpty() && !this.fAddress.getText().trim().equals(getDefaultUrl()));
    }

    /* --- FXML PART --- */

    @FXML private TextField fAddress;
    @FXML private CheckBox cbConfirmationRequete;

    /**
     * Bouton de sauvegarde
     */
    public void btSauvegarder () {
        if (!fAddress.getText().trim().isEmpty())
            Preferences.setDefaultUrl(fAddress.getText().trim());
        Preferences.setConfirmOnSqlRequest(cbConfirmationRequete.isSelected());
    }

    /* --- File R/W --- */

    private static final String file = "src/vue/assets/conf.ini";

    /**
     * Récupére l'option "confirm_on_sql_request"
     * @return true, s'il faut demander confirmation avant un requéte SQL
     */
    public static boolean getConfirmOnSqlRequest () {
        INIFile conf = new INIFile (file);
        return conf.getBooleanProperty("user", "confirm_on_sql_request");
    }

    /**
     * Définit l'option "confirm_on_sql_request"
     * @param confirm_on_sql_request true, s'il faut demander confirmation avant un requéte SQL
     */
    public static void setConfirmOnSqlRequest (boolean confirm_on_sql_request) {
        INIFile conf = new INIFile (file);
        conf.setBooleanProperty("user", "confirm_on_sql_request", confirm_on_sql_request, null);
        conf.save();
    }

    /**
     * Récupére l'option "default_url"
     * @return l'url de connection par défaut
     */
    public static String getDefaultUrl () {
        INIFile conf = new INIFile (file);
        return conf.getStringProperty("database", "default_url");
    }

    /**
     * Définit l'option "default_url"
     * @param default_url l'url de connection par défaut
     */
    public static void setDefaultUrl (String default_url) {
        INIFile conf = new INIFile (file);
        conf.setStringProperty("database", "default_url", default_url, null);
        conf.save();
    }
}
