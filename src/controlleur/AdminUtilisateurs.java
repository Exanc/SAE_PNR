package controlleur;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class AdminUtilisateurs {
    
    private VBox vbListeUtilisateurs;
    private GridPane defaultUser;

    public void btRetour () {
        ViewSwitcher.switchTo(vue.EView.MENU);
    }

    public void btPreference () {
        App.openPreferences();
    }

    public void btComandes () {
        ViewSwitcher.switchTo(vue.EView.ADMIN_CONSOLE);
    }

    public void btBDD () {
        ViewSwitcher.switchTo(vue.EView.ADMIN_CONSULTATION);
    }

    public void btDeconnexion () {
        controlleur.App.disconnectUser();
    }

    /**
     * TODO
     * Parent root = FXMLLoader.load(
            new File(view.getFileName()).toURI().toURL()
        );

        scene.setRoot(root);

        vbListeUtilisateurs.getChildren().add(root);
     */
}
