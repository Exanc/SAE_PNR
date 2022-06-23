package controlleur;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import modele.traitement.ConnectionFactory;
import vue.EImage;
import vue.EView;

/**
 * Méthode principal de l'application
 */
public class App extends Application {
    
    public static void main (String[] args) {
        launch(args);
    }

    /**
     * Méthode d'initialisation de l'application
     */
    @Override
    public void start (Stage stage) throws Exception {
        
        Scene scene = new Scene(new Pane());

        ViewSwitcher.setScene(scene);
        ViewSwitcher.setStage(stage);
        ViewSwitcher.switchTo(EView.CONNEXION);
        
        stage.setTitle("Gestion de données - Parc Naturel Regional");
        stage.getIcons().add(new Image(new FileInputStream(EImage.WINDOW_ICON.getFileName())));
        stage.setMaximized(true);
        stage.setScene(scene);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle (WindowEvent event) {
                // TODO: Si jamais on veut vérifier 2-3 truc avant de fermer l'app.
            }
        });

        stage.show();
    }

    /**
     * Méthode d'ouverture et de gestion du popup préférences
     */
    public static void openPreferences () {
        
        if (!Preferences.isActive) {
            
            Stage stage = ViewSwitcher.invokePopup(EView.POPUP_PREFERENCES, "Préférences");
            stage.setOnCloseRequest(Preferences.CLOSER);

            try {
                stage.getIcons().add(new Image(new FileInputStream("src/vue/assets/img/window_icon.png")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Méthode de déconnection de l'utilisateur
     */
    public static void disconnectUser () {
        ConnectionFactory.setProperties("", "", null);
        ViewSwitcher.switchTo(EView.CONNEXION);
    }
}
