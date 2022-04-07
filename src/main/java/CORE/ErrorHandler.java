package CORE;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Gére les érreurs
 */
public class ErrorHandler {
    
    /**
     * Affiche une érreur à l'écran
     * @param entête
     * @param message
     */
    public static void show (String entête, String message) {

        Alert alert = new Alert(AlertType.ERROR);

        alert.setTitle("ERREUR");
        alert.setHeaderText(entête);
        alert.setContentText(message 
            + "\n\nVeulliez copiez le contenu de ce message et le transmétre à votre administrateur/équipe technique.");
        
        alert.show();
    }

}
