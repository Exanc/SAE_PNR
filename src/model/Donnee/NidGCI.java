package Donnee;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

public class NidGCI implements IObs<ObsGCI> {

    private ArrayList<ObsGCI> lesObservations;
    private int idNid;
    private int ndEnvoi;
    private String nomPlage;

    /**
     * @param id
     * @param plage
     */
    public NidGCI(int id, String plage) {
        this.idNid = id;
        this.nomPlage = plage;
    }

    public Date dateDebutObs() {
        // TODO - implement NidGCI.dateDebutObs
        throw new UnsupportedOperationException();
    }

    public Date dateFinObs() {
        // TODO - implement NidGCI.dateFinObs
        throw new UnsupportedOperationException();
    }

    @Override
    public void ajouteUneObs(ObsGCI obs) {
        this.lesObservations.add(obs);
    }

    @Override
    public void ajoutePlsObs(ArrayList<ObsGCI> obs) {
        Iterator<ObsGCI> iter = obs.iterator();

        while (iter.hasNext()) {
            this.lesObservations.add(
                iter.next()
            );
        }
    }

    @Override
    public void videObs() {
        this.lesObservations.clear();
    }

    @Override
    public boolean retireObs(int idObs) {
        // TODO
        throw new UnsupportedOperationException();
        /*Iterator<ObsGCI> iter = this.lesObservations.iterator();

        while (iter.hasNext()) {
            ObsGCI cur = iter.next();
            
        }*/
    }

    @Override
    public int nbObs() {
        return this.lesObservations.size();
    }
}