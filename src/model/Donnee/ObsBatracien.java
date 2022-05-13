package Donnee;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class ObsBatracien extends Observation {

    private EspeceBatracien espece;
    private int nombreAdultes;
    private int nombreAmplexus;
    private int nombreTetard;
    private int nombrePonte;

    /**
     * Constructor of ObsBatracien
     * @param id id of Observation
     * @param date date of Observation
     * @param heure hours of Observation
     * @param lieu place of Observation
     * @param observateurs liste of Observateurs for this Observation
     * @param resObs TODO : ?
     * @param IEspece batracien species's
     */
    public ObsBatracien(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs,
                        int[] resObs, EspeceBatracien IEspece)
    {
        super(id, date, heure, lieu, observateurs);

        if (resObs != null) {
            this.espece = IEspece;
        } else {
            System.out.println("ObsBatracien : Constructor : resObs = null");
        }
    }

    @Override
    public EspeceObservee especeObs() {
        return EspeceObservee.BATRACIEN;
    }
}