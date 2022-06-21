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
        bbPane.setCenter(getSaisieOf(EView.SAISIE_BATRACIEN));
    }

    @FXML
    public void btChouette () {
        bbPane.setCenter(getSaisieOf(EView.SAISIE_CHOUETTE));
    }

    @FXML
    public void btGCI () {
        bbPane.setCenter(getSaisieOf(EView.SAISIE_GCI));
    }

    @FXML
    public void btHippocampe () {
        bbPane.setCenter(getSaisieOf(EView.SAISIE_HIPPOCAMPE));
    }

    @FXML
    public void btLoutre () {
        bbPane.setCenter(getSaisieOf(EView.SAISIE_LOUTRE));
    }

    private Parent getSaisieOf(EView view) {
        Parent ret = null;
        try {
            ret = FXMLLoader.load(new File(view.getFileName()).toURI().toURL());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}
