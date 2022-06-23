package modele.donnee;

/*
 * Represents if an indication of a passage of an otter has been observed
 */
public enum IndiceLoutre {
    POSITIF ("positif"),
    NEGATIF ("negatif"),
    NON_PROSPECTION ("non_prospection");

    private String sqlValue;

    private IndiceLoutre (String sql) {
        this.sqlValue = sql;
    }
    public String getValue () {
        return this.sqlValue;
    }
}