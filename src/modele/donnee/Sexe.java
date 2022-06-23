package modele.donnee;

/**
 * Represents an individual's sex.
 */
public enum Sexe {
    MALE ("male"),
    FEMELLE ("femelle"),
    INCONNU ("inconnu");

    private String sqlValue;

    private Sexe (String sql) {
        this.sqlValue = sql;
    }
    public String getValue () {
        return this.sqlValue;
    }
}