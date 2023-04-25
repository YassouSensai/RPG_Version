import java.util.ArrayList;

public class Scenario {
    ArrayList<Quete> parQuetes;

    /**
     * Constructeur de la classe Scenario.
     */
    public Scenario(){
        parQuetes = new ArrayList<Quete>();
    }

    /**
     * La méthode ajout de cette classe permet d'ajouter une quête au scenario de la partie.
     * @param quete
     */
    public void ajout(Quete quete) {
        parQuetes.add(quete);
    }

    /**
     * Méthode toString de la classe Scenario.
     * Elle permet d'afficher toutes les quêtes du scenario de la partie.
     * @return
     */
    public String toString() {
        return(parQuetes.size() + " " + parQuetes.toString());
    }
}
