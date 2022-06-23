package controlleur;

import java.io.File;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import vue.EView;

public class Saisie {

    @FXML BorderPane bbPane;
    @FXML Button btBatracien, btChouette, btGCI, btHippocampe, btLoutre;

    /**
     * Bouton de déconnection
     */
    public void disconnectAction () {
        controlleur.App.disconnectUser();
    }

    /**
     * Bouton préférences
     */
    public void btPreference () {
        controlleur.App.openPreferences();
    }

    /**
     * Bouton retour
     */
    public void btRetour () {
        ViewSwitcher.switchTo(EView.MENU);
    }

    /**
     * Bouton de saisie de Obs_Batracien
     */
    public void btBatracienAction () {
        setSaisieOf(EView.SAISIE_BATRACIEN);
        resetCSSId();
        this.btBatracien.setId("selected_left_button");
        this.btBatracien.setDisable(true);
    }

    /**
     * Bouton de saisie de Obs_Chouette
     */
    public void btChouetteAction () {
        setSaisieOf(EView.SAISIE_CHOUETTE);
        resetCSSId();
        this.btChouette.setId("selected_left_button");
        this.btChouette.setDisable(true);
    }

    /**
     * Bouton de saisie de Obs_GCI
     */
    public void btGCIAction () {
        setSaisieOf(EView.SAISIE_GCI);
        resetCSSId();
        this.btGCI.setId("selected_left_button");
        this.btGCI.setDisable(true);
    }

    /**
     * Bouton de saisie de Obs_Hippocampe
     */
    public void btHippocampeAction () {
        setSaisieOf(EView.SAISIE_HIPPOCAMPE);
        resetCSSId();
        this.btHippocampe.setId("selected_left_button");
        this.btHippocampe.setDisable(true);
    }

    /**
     * Bouton de saisie de Obs_Loutre
     */
    public void btLoutreAction () {
        setSaisieOf(EView.SAISIE_LOUTRE);
        resetCSSId();
        this.btLoutre.setId("selected_left_button");
        this.btLoutre.setDisable(true);
    }

    /**
     * Définit quel serat le formulaire de saisie afficher
     * @param view la vue à afficher
     */
    private void setSaisieOf(EView view) {
        try {
            Parent root = FXMLLoader.load(new File(view.getFileName()).toURI().toURL());
            bbPane.setCenter(root);
        } catch (Exception e) {
            controlleur.ErrorHandler.show("Une erreur c'est produite.", e.getMessage(), e);
        }
    }

    /**
     * Réinitialise les identifiants CSS
     */
    private void resetCSSId() {
        this.btBatracien.setId("left_button");
        this.btBatracien.setDisable(false);
        this.btChouette.setId("left_button");
        this.btChouette.setDisable(false);
        this.btGCI.setId("left_button");
        this.btGCI.setDisable(false);
        this.btHippocampe.setId("left_button");
        this.btHippocampe.setDisable(false);
        this.btLoutre.setId("left_button");
        this.btLoutre.setDisable(false);
    }
}
