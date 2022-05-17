package model.donnee;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

/**
 * Represents the sighting of an owl.
 */
public class ObsLoutre extends Observation {

    private IndiceLoutre indice;

    /**
     * @param id
     * @param date
     * @param heure
     * @param lieu
     * @param observateurs
     * @param iIndice
     */
    public ObsLoutre(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs, IndiceLoutre iIndice) {
        super(id, date, heure, lieu, observateurs);
        this.indice = iIndice;
    }

    @Override
    public EspeceObservee especeObs() {
        return EspeceObservee.LOUTRE;
    }

    /**
     * @return the clue of the sighting
     */
    public IndiceLoutre getIndice () {
        return this.indice;
    }
}