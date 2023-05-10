import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.abs;

public class Quete {

    //

    int chNumero,chDuree,chExperience;
    int precond1,precond2,precond3,precond4;
    Position chPosition;
    int [][] chPreconditions = new int [2][2];
    String chIntitule;
    boolean chRealisee = false;

    /**
     * Constructeur de la classe Quete.
     * Ce constructeur recevra en parametre une chaine de caractere qui contient les differentes informations d'une quete.
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
            pos = pos.replace(" ", "");
            Scanner scanPos = new Scanner(pos).useDelimiter(",");
            int [] tabPosition = new int [2];
            for (int x=0; x < tabPosition.length; x++) {
                tabPosition[x] = scanPos.nextInt();
            }

            chPosition = new Position(tabPosition[0], tabPosition[1]);

            String precond = scanner.next();
            precond = precond.replace("(", "");
            precond = precond.replace(")", " ");
            precond = precond.replace(" ", "");


            Scanner precondition = new Scanner(precond).useDelimiter(",");
            ArrayList<Object> liste = new ArrayList<>();

            while (precondition.hasNext())
                liste.add(precondition.next());

            int[] preconditions = new int[4];
            int lenListe = liste.size();
            for (int i=0; i<lenListe; i++) {
                if (!liste.get(i).toString().equals(""))
                    preconditions[i] = Integer.parseInt(liste.get(i).toString());
            }
            precond1 = preconditions[0];
            precond2 = preconditions[1];
            precond3 = preconditions[2];
            precond4 = preconditions[3];

            chPreconditions[0][0] = precond1;
            chPreconditions[0][1] = precond2;
            chPreconditions[1][0] = precond3;
            chPreconditions[1][1] = precond4;

            this.chDuree = scanner.nextInt();
            this.chExperience = scanner.nextInt();
            this.chIntitule = scanner.next();
        }
    }

    /**
     * Cette methode est un accesseur, elle retourne le numero de la quete appelante.
     *
     * @return chNumero
     */
    public int getChNumero() {
        return chNumero;
    }


    /**
     * Cette methode est un accesseur, elle retourne la duree de la quete appelante.
     *
     * @return chDuree
     */
    public int getChDuree() {
        return chDuree;
    }

    /**
     * Cette methode est un accesseur, elle retourne l'experience qu'apporte la quete appelante
     * (ou bien l'experience necessaire si c'est la quete finale qui appelle cette méthode).
     *
     * @return chExperience
     */
    public int getChExperience() {
        return chExperience;
    }

    /**
     * Cette methode retourne l'intitule de la quete appelante.
     * @return chIntitule
     */
    public String getChIntitule() {
        return chIntitule;
    }

    /**
     * Cette methode est un accesseur, elle retourne les preconditions de la quete appelante.
     *
     * @return chPrecondition
     */
    public int[][] getChPreconditions() {
        return chPreconditions;
    }

    /**
     * Cette methode est un accesseur, elle retourne la position de la quete appelante.
     *
     * @return chPosition
     */
    public Position getChPosition() {return chPosition;}

    /**
     * Methode qui permet de modifier le champ chRealisee d'une quete.
     *
     * @param parametre
     */
    public void setChRealisee(boolean parametre) {chRealisee = parametre;}

    /**
     * Cette methode renvoi true si la quete appelante est la quete finale.
     * Elle renvoi false si non.
     *
     * @return boolean
     */
    public boolean estQueteFinale() {
        return chNumero == 0;
    }


    /**
     * Cette methode renvoi le nombre de preconditions necessaires pour la quete.
     *
     * @return int
     */
    public int nbPreconditions() {
        if (precond1 == 0)
            return 0;
        else if (precond3 == 0)
            return 1;
        else
            return 2;
    }

    /**
     * Cette methode permet de savoir si une quete est realisee ou non.
     *
     * @return boolean
     */
    public boolean estRealisee() {return chRealisee;
    }



    /**
     * La methode deplacement() renvoi le nombre d'unite de temps que necessite
     * le deplacement de la quete appelante à la quete en parametre.
     *
     * (Cette methode existe également dans la classe Position.
     * Aisni, cette methode servira pour le niveau 2)
     *
     * @param parQuete
     * @return int
     */
    public int deplacement(Quete parQuete) {
        Position positionQuete = parQuete.getChPosition();
        return abs(this.chPosition.x - positionQuete.getX()) + abs(this.chPosition.y - positionQuete.getY());
    }

    /**
     * Methode toString de la calsse Quete.
     * permet d'afficher une quête de manière très simple.
     *
     * @return
     */
    public String toString() {
        return("quete " + this.chNumero + " : " + this.chIntitule);
    }
}
