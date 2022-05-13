package Donnee;

import java.util.ArrayList;

public interface IObs<T> {

    /**
     * Add an observation of specie
     * @param obs the observation
     */
    void ajouteUneObs(T obs);

    /**
     * Add multiple observations of species
     * @param obs list of observations of species
     */
    void ajoutePlsObs(ArrayList<T> obs);

    /**
     * Clear observation
     */
    void videObs();

    /**
     * Delete an observation of specie
     * @param idObs the id of Observation
     */
    boolean retireObs(int idObs);

    /**
     * Get number of observation
     * @return the number of observation
     */
    int nbObs();
}