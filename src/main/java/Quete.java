import java.util.Scanner;

public class Quete {

    //
    int chNumero;
    int chX;
    int chY;
    int chDuree;
    int chExperience;
    int [][] chPreconditions = new int [2][2];
    String chIntitule;

    /**
     * Constructeur de la classe Quete.
     * Ce constructeur recevra en paramètre une chaîne de caractère qui contient les différentes informations d'une quête.
     * Ainsi, le constructeur construira un objet Quete en fonctions de ces informations.
     *
     * @param ligne
     */
    public Quete(String ligne) {
        Scanner scanner = new Scanner(ligne).useDelimiter("\\|");
        while (scanner.hasNext()) {
            this.chNumero = scanner.nextInt();

            String pos = scanner.next();
            pos = pos.replace("(", "");
            pos = pos.replace(")", "");
            Scanner scanPos = new Scanner(pos).useDelimiter(", ");
            int [] tabPosition = new int [2];
            for (int x=0; x < tabPosition.length; x++) {
                tabPosition[x] = scanPos.nextInt();
            }

            chX = tabPosition[0];
            chY = tabPosition[1];

            Scanner preconditions = new Scanner(scanner.next()).useDelimiter(",");
            String premierePrecondition = "";
            String deuxiemePrecondition = "";

            if (preconditions.hasNext()) {
                premierePrecondition = preconditions.next();
            }
            if (preconditions.hasNext()) {
                deuxiemePrecondition = preconditions.next();
            }


            premierePrecondition = premierePrecondition.replace("(", "");
            premierePrecondition = premierePrecondition.replace(")", "");

            deuxiemePrecondition = deuxiemePrecondition.replace("(", "");
            deuxiemePrecondition = deuxiemePrecondition.replace(")", "");

            Scanner firstPrecond = new Scanner(premierePrecondition).useDelimiter(",");
            Scanner secondPrecond = new Scanner(premierePrecondition).useDelimiter(",");

            for (int i = 0; i < chPreconditions[0].length; i++) {
                if (firstPrecond.hasNext()) {
                    chPreconditions[0][i] = firstPrecond.nextInt();
                }
            }
            for (int i = 0; i < chPreconditions[1].length; i++) {
                if (secondPrecond.hasNext()) {
                    chPreconditions[1][i] = secondPrecond.nextInt();
                }
            }

            this.chDuree = scanner.nextInt();
            this.chExperience = scanner.nextInt();
            this.chIntitule = scanner.next();
        }
    }

    /**
     * retourne le numero de la quete appelante
     * @return chNumero
     */
    public int getChNumero() {
        return chNumero;
    }

    /**
     * retourne la position x de la quete appelante
     * @return chX
     */
    public int getChX() {
        return chX;
    }

    /**
     * retourne la position y de la quete appelante
     * @return chY
     */
    public int getChY() {
        return chY;
    }

    /**
     * retourne la duree de la quete appelante
     * @return chDuree
     */
    public int getChDuree() {
        return chDuree;
    }

    /**
     * retourne l'experience qu'apporte la quete appelante
     * (ou bien l'experience necessaire si c'est la quete finale qui appelle cette méthode)
     * @return chExperience
     */
    public int getChExperience() {
        return chExperience;
    }

    /**
     * retourne l'intitule de la quete appelante
     * @return chIntitule
     */
    public String getChIntitule() {
        return chIntitule;
    }

    /**
     * retourne les preconditions de la quete appelante
     * @return chPrecondition
     */
    public int[][] getChPreconditions() {
        return chPreconditions;
    }

    /**
     * Cette methode renvoi true si la quete appelante est la quete finale.
     * renvoi false si non.
     * @return boolean
     */
    public boolean estQueteFinale() {
        return this.chNumero == 0;
    }

    /**
     * Cette methode permet de savoir si une quete possede des preconditions ou non
     * @return boolean
     */
    public boolean possedePreconditions() {

    }

    /**
     * Méthode toString de la calsse Quete.
     * permet d'afficher une quête de manière très simple.
     * @return
     */
    public String toString() {
        return("quete " + this.chNumero + " : " + this.chIntitule);
    }
}
