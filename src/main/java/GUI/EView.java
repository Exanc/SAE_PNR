package GUI;

public enum EView {
    
    MAIN("Views/ui.fxml"),
    OTHER("Views/other.fxml");

    private String fileName;

    EView (String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
