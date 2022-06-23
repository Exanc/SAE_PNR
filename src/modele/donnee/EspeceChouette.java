package modele.donnee;

/*
 * Represents the name of the differents species inside a specie
 */
public enum EspeceChouette {
    EFFRAIE ("EFFRAIE"),
    CHEVECHE ("CHEVECHE"),
    HULOTTE ("HULOTTE");

    private String sqlValue;

    private EspeceChouette (String sql) {
        this.sqlValue = sql;
    }
    public String getValue () {
        return this.sqlValue;
    }
}