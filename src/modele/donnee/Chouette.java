package modele.donnee;

import java.util.ArrayList;
import java.util.Iterator;

public class Chouette implements IObs<ObsChouette> {

    private EspeceChouette espece;
    private Sexe sexe;
    private ArrayList<ObsChouette> lesObservations;
    private String idChouette;

    /**
     * Constructor of Chouette
     * @param id id of this Chouette
     * @param leSexe sexe of this Chouette
     * @param IEspece espece of this Chouette
     */
    public Chouette(String id, Sexe leSexe, EspeceChouette IEspece) {
        if (id != null) {
            this.idChouette = id;
            this.sexe = leSexe;
            this.espece = IEspece;
            this.lesObservations = new ArrayList<ObsChouette>();
        }
    }

    @Override
    /**
     * Add an ObsChouette
     * @param obs the observation
     */
    public void ajouteUneObs(ObsChouette obs) {
        if (obs != null) {
            this.lesObservations.add(obs);
        } else {
            System.out.println("Chouette : ajouteUneObs() : obs = null");
        }
    }

    @Override
    /**
     * Add multiple ObsChouette
     * @param obs list of ObsChouette
     */
    public void ajoutePlsObs(ArrayList<ObsChouette> obs) {
        if (obs != null) {
            for (ObsChouette obsChouette : obs) {
                this.lesObservations.add(obsChouette);
            }
        } else {
            System.out.println("Chouette : ajoutePlsObs() : obs = null");
        }
    }

    @Override
    /**
     * Clear observations
     */
    public void videObs() {
        this.lesObservations.removeAll(this.lesObservations);
    }

    @Override
    /**
     * Delete an ObsChouette
     * @param idObs the id of Observation
     */
    public boolean retireObs(int idObs) {
        Iterator<ObsChouette> iter = this.lesObservations.iterator();
        boolean estSuprimer = false;

        while (iter.hasNext() && !estSuprimer) {
            ObsChouette cur = iter.next();
            if (cur.getIdObs() == idObs) {
                this.lesObservations.remove(cur);
                estSuprimer = true;
            }
        }

        return estSuprimer;
    }

    @Override
    /**
     * Get number of ObsChouette
     * @return the number of ObsChouette
     */
    public int nbObs() {
        return this.lesObservations.size();
    }

    /**
     * @return the species of the owl
     */
    public EspeceChouette getEspece() {
        return espece;
    }

    /**
     * Sets the species of the owl
     * @param espece the species
     */
    public void setEspece(EspeceChouette espece) {
        this.espece = espece;
    }

    /**
     * @return return the sex of the owl
     */
    public Sexe getSexe() {
        return sexe;
    }

    /**
     * Sets the sex of the owl
     * @param sexe sex
     */
    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    /**
     * @return return the list of sightings
     */
    public ArrayList<ObsChouette> getLesObservations() {
        return lesObservations;
    }

    /**
     * Sets the list of sightings
     * @param lesObservations list of sightings
     */
    public void setLesObservations(ArrayList<ObsChouette> lesObservations) {

        if (lesObservations == null)
            throw new IllegalArgumentException();

        this.lesObservations = lesObservations;
    }

    /**
     * @return the id of the owl
     */
    public String getIdChouette() {
        return idChouette;
    }

    /**
     * Sets the id of the owl
     * @param idChouette id
     */
    public void setIdChouette(String idChouette) {

        if (idChouette == null)
            throw new IllegalArgumentException();

        this.idChouette = idChouette;
    }
}