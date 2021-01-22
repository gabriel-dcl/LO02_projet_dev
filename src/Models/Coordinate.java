package Models;

/**
 * Class permettant de d�finir des coordonn�es
 * @version  1.0
 * @author Gabriel Duciel, Nicolas Felixine
 */
public class Coordinate {
    private int x;
    private int y;


    public Coordinate(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Permet de comparer 2 coordonn�es
     *
     * @param obj   Les coordonn�es avec lesquelles comparer l'instance actuelle
     * @return True si les Coordonn�es sont �quivalentes, False sinon
     */
    public boolean equals(Coordinate obj) {
       if(this.x == obj.getX() && this.y == obj.getY())
           return true;
       return false;
    }

    public void setY(int y) {
        this.y = y;
    }


}