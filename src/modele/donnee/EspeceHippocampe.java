package modele.donnee;

/*
 * Represents the name of the differents species inside a specie
 */
public enum EspeceHippocampe {
    SYNGNATHUS_ACUS ("synganthus_acus"),
    HIPPOCAMPE_GUTTULATUS ("hippocampe_guttulatus"),
    HIPPOCAMPE_HIPPOCAMPUS ("hippocampe_hippocampus"),
    ENTERURUS_AEQUOREUS ("enterurus_aequoreus");

    private String sqlValue;

    private EspeceHippocampe (String sql) {
        this.sqlValue = sql;
    }
    public String getValue () {
        return this.sqlValue;
    }
}