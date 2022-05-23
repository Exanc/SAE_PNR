package vue;

public enum EView {
    
    MAIN("../vue/assets/ui/ui.fxml"),
    OTHER("../vue/assets/ui/other.fxml");

    private String fileName;

    EView (String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
