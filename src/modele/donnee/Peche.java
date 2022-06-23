package modele.donnee;

/**
 * Represents the different ways a seahorse can be fished.
 */
public enum Peche {
    CASIER_CREVETTES ("casier_crevettes"),
    CASIER_MORGATES ("casier_morgates"),
    PETIT_FILET ("petit_filet"),
    VERVEUX_ANGUILLES ("verveux_anguilles"),
    NON_RENSEIGNE ("non_renseigne");

    private String sqlValue;

    private Peche (String sql) {
        this.sqlValue = sql;
    }
    public String getValue () {
        return this.sqlValue;
    }
}