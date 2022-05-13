package Donnee;

import java.util.ArrayList;

public interface IObs<T> {

    /**
     * @param obs
     */
    void ajouteUneObs(T obs);

    /**
     * @param obs
     */
    void ajoutePlsObs(ArrayList<T> obs);

    void videObs();

    /**
     * @param idObs
     */
    boolean retireObs(int idObs);

    int nbObs();
}