package modele.traitement;

import java.sql.Date;

import modele.donnee.EspeceObservee;
import modele.donnee.Lieu;
import modele.donnee.Observation;

public class Sommet {
    
    private int id;
    private Lieu coordLieu;
    private Date date;
    private EspeceObservee espece;

    public Sommet (int id, Lieu coord, Date date, EspeceObservee espece) {

        if (coord == null || date == null)
            throw new IllegalArgumentException();
        
        this.id = id;
        this.date = date;
        this.coordLieu = coord;
        this.espece = espece;
    }

    public Sommet (Observation obs) {

        if (obs == null || obs.getDateObs() == null || obs.getLieuObs() == null)
            throw new IllegalArgumentException(
                "The Observation object or one of it's required component may be null."
            );

        this.id = obs.getIdObs();
        this.date = obs.getDateObs();
        this.coordLieu = obs.getLieuObs();
        this.espece = obs.especeObs();
    }

    public double caculeDist (Sommet som) {

        double dx = som.getCoordLieu().getXCoord() - this.coordLieu.getXCoord();
        double dy = som.getCoordLieu().getYCoord() - this.coordLieu.getYCoord();

        return Math.sqrt(dx*dx - dy*dy);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the coordinates
     */
    public Lieu getCoordLieu() {
        return coordLieu;
    }

    /**
     * Sets the coordinates
     * @param coordLieu coordinates
     */
    public void setCoordLieu(Lieu coordLieu) {

        if (coordLieu == null)
            throw new IllegalArgumentException();

        this.coordLieu = coordLieu;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date
     * @param date date
     */
    public void setDate(Date date) {

        if (date == null)
            throw new IllegalArgumentException();

        this.date = date;
    }

    /**
     * @return the species
     */
    public EspeceObservee getEspece() {
        return espece;
    }

    /**
     * Sets the species
     * @param espece species
     */
    public void setEspece(EspeceObservee espece) {
        this.espece = espece;
    }
}
