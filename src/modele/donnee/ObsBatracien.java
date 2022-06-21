package modele.donnee;

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
     * @param resObs array of nombreAdultes, nombreAmplexus, nombreTetard and nombrePonte
     * @param IEspece batracien species's
     */
    public ObsBatracien(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs,
                        int[] resObs, EspeceBatracien IEspece)
    {
        super(id, date, heure, lieu, observateurs);

        if (resObs != null) {
            this.espece = IEspece;
            this.nombreAdultes = resObs[0];
            this.nombreAmplexus = resObs[1];
            this.nombreTetard = resObs[2];
            this.nombrePonte = resObs[3];
        } 
        else {
            controlleur.ErrorHandler.show(
                "Une érreur est survenue lors de la lecture des données.",
                "L'argument \"resObs\" ne doit pas être null.",
                new IllegalArgumentException("L'argument \"resObs\" ne doit pas être null."));
        }
    }

    @Override
    public EspeceObservee especeObs () {
        return EspeceObservee.BATRACIEN;
    }

    /**
     * @param espece the species of amphibians
     */
    public void setEspeceBatracien (EspeceBatracien espece) {
        this.espece = espece;
    }

    /**
     * @return the species of amphibians
     */
    public EspeceBatracien getEspeceBatracien () {
        return this.espece;
    }

    /**
     * @param nombreAdultes the number of adults
     */
    public void setNombreAdultes (int nombreAdultes) {
        this.nombreAdultes = nombreAdultes;
    }

    /**
     * @return number of adults
     */
    public int getNombreAdultes () {
        return this.nombreAdultes;
    }

    /**
     * @param nombreAmplexus the number of amplexuses
     */
    public void setNombreAmplexus (int nombreAmplexus) {
        this.nombreAmplexus = nombreAmplexus;
    }

    /**
     * @return the number of amplexuses
     */
    public int getNombreAmplexus () {
        return this.nombreAmplexus;
    }

    /**
     * @param nombrePonte the number of pontes
     */
    public void setNombrePonte (int nombrePonte) {
        this.nombrePonte = nombrePonte;
    }

    /**
     * @return the number of pontes
     */
    public int getNombrePontes () {
        return this.nombrePonte;
    }

    /**
     * @param nombreTetard the number of tadpoles
     */
    public void setNombreTetard (int nombreTetard) {
        this.nombreTetard = nombreTetard;
    }

    /**
     * @return the number of tadpoles
     */
    public int getNombreTetard () {
        return this.nombreTetard;
    }
}