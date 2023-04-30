import static java.lang.Math.abs;

public class Position {
    int x;
    int y;

    /**
     * Constructeur de la classe Position avec les coordonees en parametre
     * @param parX
     * @param parY
     */
    public Position(int parX, int parY) {
        x = parX;
        y = parY;
    }

    /**
     * Constructeur de la classe Position à partir d'une quete
     * @param parQuete
     */
    public Position(Quete parQuete) {
        x = parQuete.getChX();
        y = parQuete.getChY();
    }

    /**
     * accesseur du champ x
     * @return int
     */
    public int getX() {return x;}

    /**
     * accesseur du champs y
     * @return int
     */
    public int getY() {return y;}

    /**
     * la methode deplacement() renvoi le nombre d'unite de temps que necessite
     * le deplacement de la position appelante à la position en parametre
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
