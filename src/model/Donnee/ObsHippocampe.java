package Donnee;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

/**
 * Represents the sighting of a seahorse.
 */
public class ObsHippocampe extends Observation {

    private Peche typePeche;
    private EspeceHippocampe espece;
    private Sexe sexe;
    private double taille;
    private boolean estGestant;

    /**
     * @param id
     * @param date
     * @param heure
     * @param lieu
     * @param observateurs
     * @param laTaille
     * @param leTypePeche
     * @param IEspece
     * @param leSexe
     */
    public ObsHippocampe(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs,
                         double laTaille, Peche leTypePeche, EspeceHippocampe IEspece, Sexe leSexe) {
        super(id, date, heure, lieu, observateurs);
        
        this.estGestant = false;
        this.taille = laTaille;
        this.typePeche = leTypePeche;
        this.espece = IEspece;
        this.sexe = leSexe;
    }

    @Override
    public EspeceObservee especeObs() {
        return EspeceObservee.HIPPOCAMPE;
    }

    /**
     * @return the type of fishing
     */
    public Peche getTypePeche () {
        return this.typePeche;
    }

    /**
     * @return the species of the sighted seahorse
     */
    public EspeceHippocampe getEspece () {
        return this.espece;
    }

    /**
     * @return the sex of the sighted seahorse
     */
    public Sexe getSexe () {
        return this.sexe;
    }

    /**
     * @return the size of the sighted seahorse
     */
    public double getTaille () {
        return this.taille;
    }

    /**
     * @return true, if the sighted seahorse is pregnant
     */
    public boolean estGestant () {
        return this.estGestant;
    }
}