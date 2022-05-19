package modele.donnee;

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
     * Sets the type of phising
     * @param typePeche type
     */
    public void setTypePeche(Peche typePeche) {
        this.typePeche = typePeche;
    }

    /**
     * @return the type of fishing
     */
    public Peche getTypePeche () {
        return this.typePeche;
    }
    
    /**
     * Sets the species
     * @param espece species
     */
    public void setEspece(EspeceHippocampe espece) {
        this.espece = espece;
    }

    /**
     * @return the species of the sighted seahorse
     */
    public EspeceHippocampe getEspece () {
        return this.espece;
    }

    /**
     * Sets the sex
     * @param sexe sex
     */
    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    /**
     * @return the sex of the sighted seahorse
     */
    public Sexe getSexe () {
        return this.sexe;
    }

    /**
     * Sets the size
     * @param taille size
     */
    public void setTaille(double taille) {
        this.taille = taille;
    }

    /**
     * @return the size of the sighted seahorse
     */
    public double getTaille () {
        return this.taille;
    }

    /**
     * Sets estGestant, true if the individual is pregnant
     * @param estGestant isPregnant
     */
    public void setEstGestant(boolean estGestant) {
        this.estGestant = estGestant;
    }

    /**
     * @return true, if the sighted seahorse was pregnant
     */
    public boolean estGestant () {
        return this.estGestant;
    }
}