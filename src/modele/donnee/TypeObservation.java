package modele.donnee;

/**
 * Represents the types of owl sightings.
 */
public enum TypeObservation {
    SONORE("SONORE"),
    VISUELLE("VISUELLE"),
    SONORE_VISUELLE("SONORE ET VISUELLE");

    private String sqlValue;

    private TypeObservation (String sql) {
        this.sqlValue = sql;
    }
    public String getValue () {
        return this.sqlValue;
    }
}