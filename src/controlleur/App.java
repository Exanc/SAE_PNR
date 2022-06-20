package controlleur;

import java.io.FileInputStream;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import vue.EView;

public class App extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        Scene scene = new Scene(new Pane());

        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(EView.CONNEXION);
        
        stage.setTitle("Gestion de données - Parc Naturel Regional");
        stage.getIcons().add(new Image(new FileInputStream("src/vue/assets/img/window_icon.png")));
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }
}
