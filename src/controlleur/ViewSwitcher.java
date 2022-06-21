package controlleur;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vue.EView;

/**
 * Permet de passer d'une vue a l'autre.
 */
public class ViewSwitcher {
    
    private static Scene scene;
    private static Stage stage;

    /**
     * Change la scene qui serat affecté par la méthode switchTo
     * @param scene la scene
     */
    public static void setScene (Scene scene) {
        ViewSwitcher.scene = scene;
    }

    public static void setStage (Stage stage) {
        ViewSwitcher.stage = stage;
    }

    /**
     * Change le vue de la scene
     * @param view
     */
    public static void switchTo (EView view) {

        if (scene == null) {
            System.out.println("ViewSwitcher: Aucune scene n'a été précisée.");
            return;
        }

        try {
            Parent root = FXMLLoader.load(
                new File(view.getFileName()).toURI().toURL()
            );

            scene.setRoot(root);

        } catch (IOException e) {
            controlleur.ErrorHandler.show(
                "Une erreur est survenue lors du chargement d'un fichier .fxml", 
                "Le fichier \""+ view.getFileName() +"\" n'a pas pu être chargé correctement.", e);
        }        
    }

    /**
     * Ouvrir une popup avec la vue.
     * @param view
     */
    public static Stage invokePopup (EView view, String title) {
        
        if (stage == null) {
            System.out.println("ViewSwitcher: Aucun stage n'a été précisée.");
            return null;
        }

        try {

            Parent root = FXMLLoader.load(
                new File(view.getFileName()).toURI().toURL()
            );

            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle(title);
            stage.setScene(new Scene(root));  
            stage.show();

            return stage;

        } catch (IOException e) {
            controlleur.ErrorHandler.show(
                "Une erreur est survenue lors du chargement d'un fichier .fxml", 
                "Le fichier \""+ view.getFileName() +"\" n'a pas pu être chargé correctement.", e);
        }
        return null;
    }
}
