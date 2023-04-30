import java.util.ArrayList;

public class NiveauUNParametre {
    Scenario scenarioEnCour;
    Quete queteFinale;
    ArrayList<Quete> quetesSansPreconditions;
    ArrayList<Quete> quetesAvecPreconditions;
    int experienceAccumulee = 0;
    int experienceNecessaireQueteFinale;
    Position positionDepart = new Position(0,0);
    Position positionSuivante;

    /**
     * Constructeur de la classe NiveauUN
     * @param parScenario
     */
    public NiveauUNParametre(Scenario parScenario) {
        scenarioEnCour = parScenario;
        queteFinale = parScenario.queteFinale();
        experienceNecessaireQueteFinale = parScenario.queteFinale().getChExperience();

        for (Quete quete : parScenario.getChQuetes()) {
            if (quete.possedePreconditions()) {
                if (!quete.estQueteFinale()) {
                    quetesAvecPreconditions.add(quete);
                }
            }
            quetesSansPreconditions.add(quete);
        }
    }

    /**
     * Methode toString de la classe NiveauUN
     * @return String
     */
    public String toString() {
        return "Niveau 1 :\nQuete finale : " + queteFinale.toString() + "\nNombre total de quetes : " + scenarioEnCour.chQuetes.size();
    }
}
