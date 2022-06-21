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
import controlleur.*;
import vue.EView;

public class App extends Application {
    
    // TODO: Trouver tous les endroit ou un érreur n'est pas envoyer via ErrorHandler

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        Scene scene = new Scene(new Pane());

        ViewSwitcher.setScene(scene);
        ViewSwitcher.setStage(stage);
        ViewSwitcher.switchTo(EView.CONNEXION);
        
        stage.setTitle("Gestion de données - Parc Naturel Regional");
        stage.getIcons().add(new Image(new FileInputStream("src/vue/assets/img/window_icon.png")));
        stage.setMaximized(true);
        stage.setScene(scene);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle (WindowEvent event) {
                // TODO: Si jamais on veut vérifier 2-3 truc avant de fermer l'app.
            }
        });

        stage.show();
    }

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

    public static void disconnectUser () {
        ConnectionFactory.setProperties("", "", null);
        ViewSwitcher.switchTo(EView.CONNEXION);
    }
}
