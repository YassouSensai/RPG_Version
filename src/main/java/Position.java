import static java.lang.Math.abs;

public class Position {
    int x, y;


    /**
     * Constructeur de la classe Position a partir des coordonnées x et y.
     *
     * @param parX
     * @param parY
     */
    public Position(int parX, int parY) {
        x = parX;
        y = parY;
    }

    /**
     * Cette methode est un accesseur, elle retourne le champ x.
     *
     * @return int
     */
    public int getX() {return x;}

    /**
     * Cette methode est un accesseur, elle retourne le champ y.
     *
     * @return int
     */
    public int getY() {return y;}

    /**
     * La methode deplacement() renvoi le nombre d'unite de temps que necessite
     * le deplacement de la position appelante a la position en parametre.
     *
     * (Cette methode est utilisée dans le niveau 1)
     *
     * @param parPosition
     * @return int
     */
    public int deplacement(Position parPosition) {
        return abs(this.x - parPosition.getX()) + abs(this.y - parPosition.getY());
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
