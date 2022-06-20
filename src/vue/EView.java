package vue;

import javafx.stage.Popup;

public enum EView {
    
    ADMIN_CONSOLE(           "src/vue/assets/ui/admin_console.fxml"),
    CONNEXION(               "src/vue/assets/ui/connexion.fxml"),
    CONSULTATION(            "src/vue/assets/ui/consultation.fxml"),
    IMPORTATION(             "src/vue/assets/ui/importation.fxml"),
    MENU(                    "src/vue/assets/ui/menu.fxml"),
    POPUP_PREFERENCES(       "src/vue/assets/ui/popup_preferences.fxml"),
    POPUP_SELECTION_CHOUETTE("src/vue/assets/ui/popup_selection_chouette.fxml"),
    SAISIE(                  "src/vue/assets/ui/saisie.fxml"),
    SAISIE_CHOUETTE(         "src/vue/assets/ui/saisie_espece/saisie_chouette.fxml"),
    SAISIE_LOUTRE(           "src/vue/assets/ui/saisie_espece/saisie_loutre.fxml"),
    SAISIE_HIPPOCAMPE(         "src/vue/assets/ui/saisie_espece/saisie_hyppocampe.fxml"),
    SAISIE_GCI(           "src/vue/assets/ui/saisie_espece/saisie_gci.fxml"),
    SAISIE_BATRACIEN(           "src/vue/assets/ui/saisie_espece/saisie_batracien.fxml");

    private String fileName;

    EView (String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
