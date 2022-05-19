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

    /**
     * Sets the xCoord
     * @param x
     */
    public void setXCoord (double x) {
        this.xCoord = x;
    }

    /**
     * @return the x coordinate
     */
    public double getXCoord () {
        return this.xCoord;
    }

    /**
     * Sets the yCoord
     * @param y
     */
    public void setYCoord (double y) {
        this.yCoord = y;
    }

    /**
     * @return the y coordinate
     */
    public double getYCoord () {
        return this.yCoord;
    } 
}