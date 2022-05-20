package controlleur;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import vue.EView;

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
                new File(view.getFileName()).toURI().toURL()
            );

            scene.setRoot(root);

        } catch (IOException e) {
            modele.ErrorHandler.show("Erreur FXML", e);
        }        
    }
}
