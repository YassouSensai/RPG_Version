import java.util.Scanner;

public class Quete {

    //
    int parNumero;
    int [] parPosition =new int [2];
    int parDuree;
    int parExperience;
    int [][] parPreconditions = new int [2][2];
    String parIntitule;

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
            this.parNumero = scanner.nextInt();

            String pos = scanner.next();
            pos = pos.replace("(", "");
            pos = pos.replace(")", "");
            Scanner scanPos = new Scanner(pos).useDelimiter(", ");
            for (int x=0; x < parPosition.length; x++) {
                parPosition[x] = scanPos.nextInt();
            }

            Scanner preconditions = new Scanner(scanner.next()).useDelimiter(",");
            String premierePrecondition = "";
            String deuxiemePrecondition = "";

            if (preconditions.hasNext() == true) {
                premierePrecondition = preconditions.next();
            }
            if (preconditions.hasNext() == true) {
                deuxiemePrecondition = preconditions.next();
            }


            premierePrecondition = premierePrecondition.replace("(", "");
            premierePrecondition = premierePrecondition.replace(")", "");

            deuxiemePrecondition = deuxiemePrecondition.replace("(", "");
            deuxiemePrecondition = deuxiemePrecondition.replace(")", "");

            Scanner firstPrecond = new Scanner(premierePrecondition).useDelimiter(",");
            Scanner secondPrecond = new Scanner(premierePrecondition).useDelimiter(",");

            for (int i=0; i < parPreconditions[0].length; i++) {
                if (firstPrecond.hasNext() == true) {
                    parPreconditions[0][i] = firstPrecond.nextInt();
                }
            }
            for (int i=0; i < parPreconditions[1].length; i++) {
                if (secondPrecond.hasNext() == true) {
                    parPreconditions[1][i] = secondPrecond.nextInt();
                }
            }

            this.parDuree = scanner.nextInt();
            this.parExperience = scanner.nextInt();
            this.parIntitule = scanner.next();
        }
    }

    /**
     * Méthode toString de la calsse Quete.
     * permet d'afficher une quête de manière très simple.
     * @return
     */
    public String toString() {
        return("quete " + this.parNumero + " : " + this.parIntitule);
    }
}
