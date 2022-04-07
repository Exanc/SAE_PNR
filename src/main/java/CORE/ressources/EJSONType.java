package CORE.ressources;

/**
 * Énumérateur des différents types de fichier JSON.
 */
public enum EJSONType {
    
    SQLRequestLibrary ("SQLRequestLibrary");

    private String name;

    EJSONType (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
