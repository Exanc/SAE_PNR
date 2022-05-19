package modele.donnee;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

public class NidGCI implements IObs<ObsGCI> {

    private ArrayList<ObsGCI> lesObservations;
    private int idNid;
    private int ndEnvol;
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
        Date min = null;

        if (this.lesObservations.size() > 0) {
            min = this.lesObservations.get(0).getDateObs();
            Iterator<ObsGCI> iter = this.lesObservations.iterator();
            
            iter.next();
            while (iter.hasNext()) {
                Date cur = iter.next().getDateObs();
                if (cur.before(min))
                    min = cur;
            }
        }

        return min;
    }

    public Date dateFinObs() {
        Date max = null;

        if (this.lesObservations.size() > 0) {
            max = this.lesObservations.get(0).getDateObs();
            Iterator<ObsGCI> iter = this.lesObservations.iterator();
            
            iter.next();
            while (iter.hasNext()) {
                Date cur = iter.next().getDateObs();
                if (cur.after(max))
                    max = cur;
            }
        }

        return max;
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
        Iterator<ObsGCI> iter = this.lesObservations.iterator();
        boolean estSuprimer = false;

        while (iter.hasNext() && !estSuprimer) {
            ObsGCI cur = iter.next();
            if (cur.getIdObs() == idObs) {
                this.lesObservations.remove(cur);
                estSuprimer = true;
            }
        }

        return estSuprimer;
    }

    @Override
    public int nbObs() {
        return this.lesObservations.size();
    }

    public int getIdNid () {
        return this.idNid;
    }

    public int getNbEnvol () {
        return this.ndEnvol;
    }

    public String getNomPlage () {
        return this.nomPlage;
    }
}