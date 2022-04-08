package GUI;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Permet de passer d'une vue a l'autre.
 */
public class ViewSwitcher {
    
    private static Scene scene;

    /**
     * Change la scene qui serat affecté par la méthode switchTo
     * @param scene la scene
     */
    public static void setScene(Scene scene) {
        ViewSwitcher.scene = scene;
    }

    /**
     * Change le vue de la scene
     * @param view
     */
    public static void switchTo (EView view) {

        if (scene == null) {
            System.out.println("ViewSwitcher: No scene was set.");
            return;
        }

        try {
            Parent root = FXMLLoader.load(
                ViewSwitcher.class.getResource(view.getFileName())
            );

            scene.setRoot(root);

        } catch (IOException e) {
            CORE.ErrorHandler.show("Erreur FXML", e.getStackTrace().toString());
        }        
    }
}
