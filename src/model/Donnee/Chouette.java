package Donnee;

import java.util.ArrayList;

public class Chouette implements IObs<ObsChouette> {

    private EspeceChouette espece;
    private Sexe sexe;
    private ObsChouette[] lesObservations;
    private String idChouette;

    /**
     * Constructor of Chouette
     * @param id id of this Chouette
     * @param leSexe sexe of this Chouette
     * @param IEspece espece of this Chouette
     */
    public Chouette(String id, Sexe leSexe, EspeceChouette IEspece) {
        if (id != null) {
            this.idChouette = id;
            this.sexe = leSexe;
            this.espece = IEspece;
        }
    }

    @Override
    public void ajouteUneObs(ObsChouette obs) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void ajoutePlsObs(ArrayList<ObsChouette> obs) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void videObs() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean retireObs(int idObs) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int nbObs() {
        // TODO Auto-generated method stub
        return 0;
    }
}