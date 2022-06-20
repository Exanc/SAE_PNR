package vue;

public enum EView {
    
    CONNEXION("src/vue/assets/ui/connexion.fxml"),
    MENU("src/vue/assets/ui/menu.fxml"),
    CONSULTATION("src/vue/assets/ui/consultation.fxml"),
    IMPORTATION("src/vue/assets/ui/importation.fxml"),
    SAISIE("src/vue/assets/ui/saisie.fxml");

    private String fileName;

    EView (String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
