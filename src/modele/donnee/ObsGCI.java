package modele.donnee;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

/**
 * Represents the sighting of a gravelot à collier interrompu.
 */
public class ObsGCI extends Observation {

    private ContenuNid natureObs;
    private int nombre;

    /**
     * @param id
     * @param date
     * @param heure
     * @param lieu
     * @param observateurs
     * @param nature
     * @param leNombre
     */
    public ObsGCI(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs,
                  ContenuNid nature, int leNombre) {
        super(id, date, heure, lieu, observateurs);
        
        this.natureObs = nature;
        this.nombre = leNombre;
    }

    @Override
    public EspeceObservee especeObs() {
        return EspeceObservee.GCI;
    }

    /**
     * Sets the type of the sighting
     * @param natureObs type
     */
    public void setNatureObs (ContenuNid natureObs) {
        this.natureObs = natureObs;
    } 

    /**
     * @return the type of sighting
     */
    public ContenuNid getNatureObs () {
        return this.natureObs;
    }

    /**
     * Sets the number of individuals
     * @param nombre number
     */
    public void setNombre (int nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the number of individuals sighted
     */
    public int getNombre () {
        return this.nombre;
    }
}