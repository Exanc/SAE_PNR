package modele.donnee;

/*
 * Represents the name of the differents species inside a specie
 */
public enum EspeceBatracien {
    CALAMITE ("calamite"),
    PELODYTE ("pelodyte");

    private String sqlValue;

    private EspeceBatracien (String sql) {
        this.sqlValue = sql;
    }
    public String getValue () {
        return this.sqlValue;
    }
}