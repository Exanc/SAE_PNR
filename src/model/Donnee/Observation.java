package Donnee;

public abstract class Observation {

    protected Observateur[] lesObservateurs;
    protected Lieu lieuObs;
    protected int idObs;
    protected java.sql.Date dataObs;
    protected java.sql.Time heureObs;

    /**
     * 
     * @param id
     * @param date
     * @param heure
     * @param lieu
     * @param observateurs
     */
    public Observation(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs) {
        // TODO - implement Observation.Observation
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param o
     */
    public void ajouteObservateur(Observateur o) {
        // TODO - implement Observation.ajouteObservateur
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param idObservateur
     */
    public void retireObservateur(int idObservateur) {
        // TODO - implement Observation.retireObservateur
        throw new UnsupportedOperationException();
    }

    public abstract EspeceObservee especeObs();

}