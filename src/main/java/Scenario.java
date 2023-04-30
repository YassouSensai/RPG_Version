import java.util.ArrayList;

public class Scenario {
    ArrayList<Quete> chQuetes;

    /**
     * Constructeur de la classe Scenario.
     */
    public Scenario(){
        chQuetes = new ArrayList<Quete>();
    }

    /**
     * La methode ajout de cette classe permet d'ajouter une quête au scenario de la partie.
     * @param quete
     */
    public void ajout(Quete quete) {
        chQuetes.add(quete);
    }

    /**
     * retourne la liste des quetes du scenario appelant
     * @return chQuetes
     */
    public ArrayList<Quete> getChQuetes() {
        return chQuetes;
    }

    /**
     * La methode queteFinale() renvoi la quete finale du scenario
     * c'est à dire la quete qui possède le numéro 0
     * @return Quete
     */
    public Quete queteFinale() {
        Quete queteZero = null;
        for (Quete quete : chQuetes) {
            if (quete.chNumero == 0) {
                queteZero = quete;
            }
        }
        return queteZero;
    }

    /**
     * Méthode toString de la classe Scenario.
     * Elle permet d'afficher toutes les quêtes du scenario de la partie.
     * @return String
     */
    public String toString() {
        return(chQuetes.size() + " " + chQuetes.toString());
    }
}
