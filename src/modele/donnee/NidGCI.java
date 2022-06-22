package modele.donnee;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

public class NidGCI implements IObs<ObsGCI> {

    private ArrayList<ObsGCI> lesObservations;
    private int idNid;
    private int nbEnvol;
    private String nomPlage;

    /**
     * @param id
     * @param plage
     */
    public NidGCI(int id, String plage) {
        this.idNid = id;
        this.nomPlage = plage;
    }

    /**
     * Get the observation start date
     * @return this date
     */
    public Date dateDebutObs() {
        Date min = null;

        if (this.lesObservations.size() > 0) {
            min = this.lesObservations.get(0).getDateObs();
            Iterator<ObsGCI> iter = this.lesObservations.iterator();
            
            iter.next();
            while (iter.hasNext()) {
                Date cur = iter.next().getDateObs();
                if (cur.before(min))
                    min = cur;
            }
        }

        return min;
    }

    /**
     * Get the observation end date
     * @return this date 
     */
    public Date dateFinObs() {
        Date max = null;

        if (this.lesObservations.size() > 0) {
            max = this.lesObservations.get(0).getDateObs();
            Iterator<ObsGCI> iter = this.lesObservations.iterator();
            
            iter.next();
            while (iter.hasNext()) {
                Date cur = iter.next().getDateObs();
                if (cur.after(max))
                    max = cur;
            }
        }

        return max;
    }

    /**
     * Add an observation
     * @param obs an Observation
     */
    @Override
    public void ajouteUneObs(ObsGCI obs) {
        this.lesObservations.add(obs);
    }

    /**
     * Add a list of observation
     * @param obs ArrayList containing several observations
     */
    @Override
    public void ajoutePlsObs(ArrayList<ObsGCI> obs) {
        Iterator<ObsGCI> iter = obs.iterator();

        while (iter.hasNext()) {
            this.lesObservations.add(
                iter.next()
            );
        }
    }

    /**
     * Clear the list of observations
     */
    @Override
    public void videObs() {
        this.lesObservations.clear();
    }

    /*
     * Remove an observation
     * @param idObs index of the observation wanted to be removed
     */
    @Override
    public boolean retireObs(int idObs) {
        Iterator<ObsGCI> iter = this.lesObservations.iterator();
        boolean estSuprimer = false;

        while (iter.hasNext() && !estSuprimer) {
            ObsGCI cur = iter.next();
            if (cur.getIdObs() == idObs) {
                this.lesObservations.remove(cur);
                estSuprimer = true;
            }
        }

        return estSuprimer;
    }

    /**
     * Size of the observation list
     * @return a integer 
     */
    @Override
    public int nbObs() {
        return this.lesObservations.size();
    }

    /**
     * @return the list of sightings
     */
    public ArrayList<ObsGCI> getLesObservations() {
        return lesObservations;
    }

    /**
     * Sets the list of sightings
     * @param lesObservations list of sightings
     */
    public void setLesObservations(ArrayList<ObsGCI> lesObservations) {

        if (lesObservations == null)
            throw new IllegalArgumentException();

        this.lesObservations = lesObservations;
    }

    /**
     * @return the id of the nest
     */
    public int getIdNid() {
        return idNid;
    }

    /**
     * Sets the id of the nest
     * @param idNid id
     */
    public void setIdNid(int idNid) {
        this.idNid = idNid;
    }

    /**
     * @return the number of flights
     */
    public int getNbEnvol() {
        return nbEnvol;
    }

    /**
     * Sets the number of flights
     * @param ndEnvol number of flights
     */
    public void setNbEnvol(int ndEnvol) {

        if (nbEnvol < 0)
            throw new IllegalArgumentException();

        this.nbEnvol = ndEnvol;
    }

    /**
     * @return the name of the beach
     */
    public String getNomPlage() {
        return nomPlage;
    }

    /**
     * Sets the name of the beach
     * @param nomPlage name
     */
    public void setNomPlage(String nomPlage) {

        if (nomPlage == null)
            throw new IllegalArgumentException();

        this.nomPlage = nomPlage;
    }
}