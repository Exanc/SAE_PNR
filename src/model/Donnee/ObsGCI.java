package Donnee;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

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

    public ContenuNid getNatureObs () {
        return this.natureObs;
    }

    public int getNombre () {
        return this.nombre;
    }
}