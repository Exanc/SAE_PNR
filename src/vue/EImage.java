package vue;

public enum EImage {

    PICTO_EXPERIMENTATION("src/vue/assets/img/picto/experimentation.png"),
    PICTO_FAUNE(          "src/vue/assets/img/picto/faune.png"),
    PICTO_PARC(           "src/vue/assets/img/picto/parc.png"),
    PICTO_PATRIMOINE(     "src/vue/assets/img/picto/patrimoine.png"),
    PICTO_TOURISME(       "src/vue/assets/img/picto/tourisme.png"),
    
    LOGO_PNR_TRANSPARENT("src/vue/assets/img/logo_pnr_transparent.png"),
    LOGO_PNR(            "src/vue/assets/img/logo_pnr.png"),
    WINDOW_ICON(         "src/vue/assets/img/window_icon.png");

    private String fileName;

    EImage (String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
