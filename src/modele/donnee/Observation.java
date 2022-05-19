package modele.donnee;

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
     * Sets the list of observers
     * @param lesObservateurs list of observers
     */
    public void setLesObservateurs (ArrayList<Observateur> lesObservateurs) {

        if (lesObservateurs == null)
            throw new IllegalArgumentException();

        this.lesObservateurs = lesObservateurs;
    }

    /**
     * @return the list of observers
     */
    public ArrayList<Observateur> getLesObservateurs() {
        return this.lesObservateurs;
    }

    /**
     * Sets the id
     * @param idObs id
     */
    public void setIdObs(int idObs) {
        this.idObs = idObs;
    }

    /**
     * @return the id
     */
    public int getIdObs() {
        return this.idObs;
    }

    /**
     * Sets the location of the sighting
     * @param lieuObs location
     */
    public void setLieuObs(Lieu lieuObs) {

        if (lieuObs == null)
            throw new IllegalArgumentException();
        
        this.lieuObs = lieuObs;
    }

    /**
     * @return the locations of the sighting
     */
    public Lieu getLieuObs() {
        return this.lieuObs;
    }

    /**
     * Sets the date of the sighting
     * @param dateObs date
     */
    public void setDateObs(Date dateObs) {
        
        if (dateObs == null)
            throw new IllegalArgumentException();

        this.dateObs = dateObs;
    }

    /**
     * @return date of the sighting
     */
    public Date getDateObs() {
        return this.dateObs;
    }

    /**
     * Sets the time of the sighting
     * @param heureObs time of sighting
     */
    public void setHeureObs(Time heureObs) {

        if (heureObs == null)
            throw new IllegalArgumentException();

        this.heureObs = heureObs;
    }

    /**
     * @return the time of the sighting
     */
    public Time getHeureObs() {
        return this.heureObs;
    }
}