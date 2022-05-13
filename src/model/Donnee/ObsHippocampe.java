package Donnee;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

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

    public Peche getTypePeche () {
        return this.typePeche;
    }

    public EspeceHippocampe getEspece () {
        return this.espece;
    }

    public Sexe getSexe () {
        return this.sexe;
    }

    public double getTaille () {
        return this.taille;
    }

    public boolean estGestant () {
        return this.estGestant;
    }
}