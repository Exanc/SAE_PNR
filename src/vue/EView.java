package vue;

public enum EView {
    
    MAIN("src/vue/assets/ui/ui.fxml"),
    OTHER("src/vue/assets/ui/other.fxml");

    private String fileName;

    EView (String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
