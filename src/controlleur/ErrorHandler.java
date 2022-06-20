package controlleur;

import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Gére les érreurs
 */
public class ErrorHandler {
    
    
    /*public static void show (String entête, Exception e) {

        Alert alert = new Alert(AlertType.ERROR);
        StackTraceElement[] traces = e.getStackTrace();
        String message = e.toString();

        for (int i = 0; i < traces.length && i < 10; i++)
        {
            message += "\n   at " + traces[i].getClassName();
        }
        
        alert.setTitle("ERREUR");
        alert.setHeaderText(entête);
        alert.setContentText(message 
            + "\n\nVeulliez copiez le contenu de ce message et le transmétre à votre administrateur/équipe technique.");
        
        alert.show();
    }*/
    
    /**
     * Affiche une érreur à l'écran
     * @param entête
     * @param description
     * @param e
     */
    public static void show (String entête, String description, Exception e) {

        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(entête);
        alert.setContentText(description);

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("Le trace de l'exception:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }

}
