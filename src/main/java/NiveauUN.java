import java.util.ArrayList;

public class NiveauUN {
    Scenario scenarioEnCour;
    Quete queteFinale;
    ArrayList<Quete> quetesSansPreconditions;
    ArrayList<Quete> quetesAvecPreconditions;
    int experienceAccumulee = 0;
    int experienceNecessaireQueteFinale;

    public NiveauUN(Scenario parScenario) {
        scenarioEnCour = parScenario;
        queteFinale = parScenario.queteFinale();
        experienceNecessaireQueteFinale = parScenario.queteFinale().getChExperience();
    }
}
