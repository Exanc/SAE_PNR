package vue;

public enum EView {

    ADMIN_CONSOLE(           "ui/admin_console.fxml"),
    ADMIN_CONSULTATION(      "ui/admin_consultation.fxml"),
    ADMIN_UTILISATEUR(       "ui/admin_utilisateurs.fxml"),
    CONNEXION(               "ui/connexion.fxml"),
    CONSULTATION(            "ui/consultation.fxml"),
    DEFAULT_USER(            "ui/default_user.fxml"),
    IMPORTATION(             "ui/importation.fxml"),
    MENU(                    "ui/menu.fxml"),
    POPUP_GENERIC(           "ui/popup_generic.fxml"),
    POPUP_PREFERENCES(       "ui/popup_preferences.fxml"),
    POPUP_SELECTION_CHOUETTE("ui/popup_selection_chouette.fxml"),
    SAISIE(                  "ui/saisie.fxml"),
    SAISIE_CHOUETTE(         "ui/saisie_espece/saisie_chouette.fxml"),
    SAISIE_LOUTRE(           "ui/saisie_espece/saisie_loutre.fxml"),
    SAISIE_HIPPOCAMPE(       "ui/saisie_espece/saisie_hippocampe.fxml"),
    SAISIE_GCI(              "ui/saisie_espece/saisie_gci.fxml"),
    SAISIE_BATRACIEN(        "ui/saisie_espece/saisie_batracien.fxml");


    private String fileName;

    EView (String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
