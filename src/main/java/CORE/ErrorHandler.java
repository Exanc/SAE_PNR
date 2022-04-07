package CORE;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorHandler {
    
    public static void show (String entête, String message) {

        Alert alert = new Alert(AlertType.ERROR);

        alert.setTitle("ERREUR");
        alert.setHeaderText(entête);
        alert.setContentText(message);
        alert.show();
    }

}
