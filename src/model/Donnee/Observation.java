package Donnee;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

public abstract class Observation {

    protected ArrayList<Observateur> lesObservateurs;
    protected Lieu lieuObs;
    protected int idObs;
    protected Date dataObs;
    protected Time heureObs;

    /**
     * 
     * @param id
     * @param date
     * @param heure
     * @param lieu
     * @param observateurs
     */
    public Observation(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs) {
        this.idObs = id;
        this.dataObs = date;
        this.heureObs = heure;
        this.lieuObs = lieu;
        this.lesObservateurs = observateurs;
    }

    /**
     * 
     * @param o
     */
    public void ajouteObservateur(Observateur o) {
        this.lesObservateurs.add(o);
    }

    /**
     * 
     * @param idObservateur
     */
    public void retireObservateur(int idObservateur) {
        this.lesObservateurs.remove(idObservateur);
    }

    public abstract EspeceObservee especeObs();

}