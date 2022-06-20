package vue;

public enum EView {
    
    CONNEXION("src/vue/assets/ui/connexion.fxml"),
    MENU("src/vue/assets/ui/menu.fxml"),
    CONSULTATION("src/vue/assets/ui/consultation.fxml"),
    IMPORTATION("src/vue/assets/ui/importation.fxml"),
    SAISIE("src/vue/assets/ui/saisie.fxml"),
    ADMIN_CONSOLE("src/vue/assets/ui/admin_console.fxml"),
    ADMIN_CONSULTATION("src/vue/assets/ui/admin_consultation.fxml"),
    ADMIN_UTILISATEURS("src/vue/assets/ui/admin_utilisateurs.fxml"),
    GENERIC_POPUP("src/vue/assets/ui/popup_generic.fxml"),
    POPUP_PREFERENCES("src/vue/assets/ui/popup_preferences.fxml"),
    POPUP_CHOUETTE("src/vue/assets/ui/popup_selection_chouette.fxml");

    private String fileName;

    EView (String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
