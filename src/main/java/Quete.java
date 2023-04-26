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
                if (firstPrecond.hasNext() == true) {
                    chPreconditions[0][i] = firstPrecond.nextInt();
                }
            }
            for (int i = 0; i < chPreconditions[1].length; i++) {
                if (secondPrecond.hasNext() == true) {
                    chPreconditions[1][i] = secondPrecond.nextInt();
                }
            }

            this.chDuree = scanner.nextInt();
            this.chExperience = scanner.nextInt();
            this.chIntitule = scanner.next();
        }
    }

    public int getChNumero() {
        return chNumero;
    }

    public int getChX() {
        return chX;
    }

    public int getChY() {
        return chY;
    }

    public int getChDuree() {
        return chDuree;
    }

    public int getChExperience() {
        return chExperience;
    }

    public String getChIntitule() {
        return chIntitule;
    }

    public int[][] getChPreconditions() {
        return chPreconditions;
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
