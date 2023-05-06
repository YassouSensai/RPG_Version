import java.util.Scanner;

public class Quete {

    //
    int chNumero;
    Position chPosition;
    int chDuree;
    int chExperience;
    int [][] chPreconditions = new int [2][2];
    String chIntitule;
    boolean chRealisee = false;

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

            chPosition = new Position(tabPosition[0], tabPosition[1]);

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
        return chNumero == 0;
    }

    /**
     * Cette methode permet de savoir si une quete possede des preconditions ou non
     * @return boolean
     */
    public boolean possedePreconditions() {
        boolean reponse = false;
        for (int i=0; i < chPreconditions.length; i++) {
            for (int j=0; j < chPreconditions[i].length; j++) {
                if (chPreconditions[i][j] != 0){
                    reponse = true;
                    break;
                }
            }
        }
        return reponse;
    }

    /**
     * Cette methode renvoi le nombre de preconditions necessaires pour la quete
     * @return int
     */
    public int nbPreconditions() {
        if (chPreconditions[0][0] != 0)
            return 1;
        if (chPreconditions[1][0] != 0)
            return 2;
        else
            return 0;
    }


    public boolean estRealisee(Quete[] parTabQuetes) {
        return false;
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
