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
}
