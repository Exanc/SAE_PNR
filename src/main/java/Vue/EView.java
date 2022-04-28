package Vue;

public enum EView {
    
    MAIN("../ui/ui.fxml"),
    OTHER("../ui/other.fxml");

    private String fileName;

    EView (String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
