package modele.donnee;

public class Lieu {

    private double xCoord;
    private double yCoord;

    /**
     * Constructor of Lieu
     * @param x x position
     * @param y y position
     */
    public Lieu(double x, double y) {
        this.xCoord = x;
        this.yCoord = y;
    }

    public double getXCoord () {
        return this.xCoord;
    }

    public double getYCoord () {
        return this.yCoord;
    } 
}