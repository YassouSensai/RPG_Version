import java.util.ArrayList;

public class NiveauUNParametre {
    Scenario scenarioEnCour;
    Quete queteFinale;
    Quete queteEnCour;
    ArrayList<Quete> quetesSansPreconditions = new ArrayList<>();
    ArrayList<Quete> quetesAvecPreconditions = new ArrayList<>();
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
            else {
                quetesSansPreconditions.add(quete);
            }
        }
    }

    /**
     * la methode solutionEfficace() renvoie un tableau avec les quetes dans l'ordre pour une solution dite efficace
     * @return Quete[]
     */
    public Quete[] solutionEfficace() {
        return new Quete[]{queteFinale};
    }

    /**
     * la methode solutionexhaustive() renvoie un tableau avec les quetes dans l'ordre pour une solution dite exhaustive
     * @return Quete[]
     */
    public Quete[] solutionExhaustive() {
        return new Quete[]{queteFinale};
    }

    /**
     * Methode toString de la classe NiveauUN
     * @return String
     */
    public String toString() {
        return "Niveau 1 :\nQuete finale : " + queteFinale.toString() + "\nNombre total de quetes : " + scenarioEnCour.chQuetes.size();
    }
}
