import java.util.ArrayList;
import java.util.Arrays;

public class NiveauUNParametre {
    Scenario scenarioEnCour;
    Quete queteFinale;
    int experienceAccumulee = 0;
    int experienceNecessaireQueteFinale;
    ArrayList<Integer> quetesRealisee = new ArrayList<>();
    Position positionDepart = new Position(0,0);
    Position positionSuivante;
    int dureeAccumulee = 0;

    /**
     * Constructeur de la classe NiveauUN
     * @param parScenario
     */
    public NiveauUNParametre(Scenario parScenario) {
        scenarioEnCour = parScenario;
        queteFinale = parScenario.queteFinale();
        experienceNecessaireQueteFinale = parScenario.queteFinale().getChExperience();
    }

    /**
     * cette methode permet de verifier qu'une quete a bien été réalisée
     * @param precondition
     * @return boolean
     */
    private boolean estRealisee(int precondition) {
        return quetesRealisee.contains(precondition);
    }


    /**
     * Cette methode permet de verifier que les preconditions d'une quete ont bien été vérifié
     * @param queteEnCour
     * @return boolean
     */
    private boolean preconditionsValidee(Quete queteEnCour) {
        int[][] preconditions = queteEnCour.getChPreconditions();


    }


    /**
     * la methode realisonLaQuete() permet de faire les differents ajouts (solution, experience, duree, ...)
     * @param queteEnCour
     * @param solution
     */
    private void realisonLaQuete(Quete queteEnCour, ArrayList<Quete> solution) {
        quetesRealisee.add(queteEnCour.getChNumero());
        solution.add(queteEnCour);

        if (queteEnCour != queteFinale) {
            experienceAccumulee += queteEnCour.getChExperience();
        }
    }




    /**
     * la methode solutionEfficace() renvoie un tableau avec les quetes dans l'ordre pour une solution dite efficace
     * @return ArrayList<Quete>
     */
    public ArrayList<Quete> solutionEfficace() {
        quetesRealisee = new ArrayList<>();
        ArrayList<Quete> solution = new ArrayList<>();

        while (!estRealisee(0)) {
            for (Quete queteEnCour : scenarioEnCour.getChQuetes()) {

                // traitement de la quete finale
                if (queteEnCour == queteFinale) {
                    if (experienceAccumulee >= experienceNecessaireQueteFinale) {
                        realisonLaQuete(queteEnCour, solution);
                    }
                }
            }
        }

        return solution;

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
