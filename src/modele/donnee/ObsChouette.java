package modele.donnee;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class ObsChouette extends Observation {

    private TypeObservation typeObs;

    /**
     * Constructor of ObsChouette
     * @param id id of Observation
     * @param date date of Observation
     * @param heure hours of Observation
     * @param lieu place of Observation
     * @param observateurs liste of Observateurs for this Observation
     * @param type typeObservation
     */
    public ObsChouette(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs, TypeObservation type) {
        super(id, date, heure, lieu, observateurs);
        this.typeObs = type;
    }

    @Override
    public EspeceObservee especeObs() {
        return EspeceObservee.CHOUETTE;
    }

    /**
     * Sets the type of observation
     * @param typeObs type
     */
    public void setTypeObs(TypeObservation typeObs) {
        this.typeObs = typeObs;
    }

    /**
     * @return the type of observation
     */
    public TypeObservation getTypeObs() {
        return this.typeObs;
    }
}