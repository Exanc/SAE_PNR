package controlleur;

import java.io.File;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import vue.EView;

public class Saisie {
    @FXML
    BorderPane bbPane;

    @FXML
    public void disconnectAction () {
        modele.traitement.ConnectionFactory.setProperties("", "", null);
        ViewSwitcher.switchTo(EView.CONNEXION);
    }

    @FXML
    public void btPreference () {
        controlleur.App.openPreferences();
    }

    @FXML
    public void btRetour () {
        ViewSwitcher.switchTo(EView.MENU);
    }

    @FXML
    public void btCarte () {}

    @FXML
    public void btBatracien () {
        Parent root;
        try {
            root = FXMLLoader.load(
                new File(EView.SAISIE_BATRACIEN.getFileName()).toURI().toURL()
            );
            bbPane.setCenter(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*
    @FXML
    public void btChouette () {}

    @FXML
    public void btGCI () {}

    @FXML
    public void btHippocampe () {}

    @FXML
    public void btLoutre () {}

    private Parent getSaisieOf(EView view) {
        Parent ret = null;
        try {
            ret = FXMLLoader.load(new File(view.getFileName()).toURI().toURL());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
*/
}
