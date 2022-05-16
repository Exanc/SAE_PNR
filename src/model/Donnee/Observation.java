package Donnee;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

public abstract class Observation
{
    protected ArrayList<Observateur> lesObservateurs;
    protected Lieu lieuObs;
    protected int idObs;
    protected Date dateObs;
    protected Time heureObs;

    /**
     * Create an Observation for an specie will all its parameters
     * @param id  the id of the Observation
     * @param date the date of the Observation
     * @param heure the our of the Observation
     * @param lieu the town/city of the Observation
     * @param observateurs ArrayList of all the Observators on this Observation
     */
    public Observation(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs) {
        if (id > 0 && date != null && heure != null && lieu != null && observateurs != null) {
            this.idObs = id;
            this.dateObs = date;
            this.heureObs = heure;
            this.lieuObs = lieu;
            this.lesObservateurs = observateurs;
        }
    }

    /**
     * Add an Observator o to the end of the ArrayList lesObservateurs
     * Can't have two Observators with the same id
     * @param o an Observator
     */
    public void ajouteObservateur(Observateur o) {
        boolean bool = true; 

        for (Observateur observateur : this.lesObservateurs) {
            if (observateur.getId() == o.getId()) {
                bool = false;
            }
        }

        if (o != null && bool == true) {
            this.lesObservateurs.add(o);
        }
    }

    /**
     * Will remove an Observator at index idObservateur in the ArrayList lesObservateurs
     * @param idObservateur index of the Observator
     */
    public void retireObservateur(int idObservateur) {
        if (idObservateur >= 0 && idObservateur <= this.lesObservateurs.size()) {
            this.lesObservateurs.remove(idObservateur);
        }
    }

    public abstract EspeceObservee especeObs();

    /**
     * @return the date of the sighting
     */
    public Date getDateObs () {
        return this.dateObs;
    }

    /**
     * Get the id of the Observator
     * @return int idObs
     */
    public int getIdObs() {
        return this.idObs;
    }
}