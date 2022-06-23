package modele.donnee;

/**
 * Represents the contents of the observed nest
 */
public enum ContenuNid {
    OEUF ("Oeuf"),
    POUSSIN ("Poussin"),
    NID_SEUL ("Nid");

    private String sqlValue;

    private ContenuNid (String sql) {
        this.sqlValue = sql;
    }
    public String getValue () {
        return this.sqlValue;
    }
}