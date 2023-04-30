import java.util.ArrayList;

public class NiveauUN {
    Scenario scenarioEnCour;
    Quete queteFinale;
    ArrayList<Quete> quetesSansPreconditions;
    ArrayList<Quete> quetesAvecPreconditions;
    int experienceAccumulee = 0;
    int experienceNecessaireQueteFinale;

    /**
     * Constructeur de la classe NiveauUN
     * @param parScenario
     */
    public NiveauUN(Scenario parScenario) {
        scenarioEnCour = parScenario;
        queteFinale = parScenario.queteFinale();
        experienceNecessaireQueteFinale = parScenario.queteFinale().getChExperience();
    }

    /**
     * Methode toString de la classe NiveauUN
     * @return String
     */
    public String toString() {
        return "Niveau 1 :\nQuete finale : " + queteFinale.toString() + "\nNombre total de quetes : " + scenarioEnCour.chQuetes.size();
    }
}
