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
     * La méthode ajout de cette classe permet d'ajouter une quête au scenario de la partie.
     * @param quete
     */
    public void ajout(Quete quete) {
        chQuetes.add(quete);
    }

    public ArrayList<Quete> getChQuetes() {
        return chQuetes;
    }

    /**
     * Méthode toString de la classe Scenario.
     * Elle permet d'afficher toutes les quêtes du scenario de la partie.
     * @return
     */
    public String toString() {
        return(chQuetes.size() + " " + chQuetes.toString());
    }
}
