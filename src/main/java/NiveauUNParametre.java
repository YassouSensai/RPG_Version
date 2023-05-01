import java.util.ArrayList;
import java.util.Arrays;

public class NiveauUNParametre {
    Scenario scenarioEnCour;
    Quete queteFinale;
    int experienceAccumulee = 0;
    int experienceNecessaireQueteFinale;
    int[] quetesRealisee = {1};
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
        quetesRealisee = new int[scenarioEnCour.getChQuetes().size()];
    }

    /**
     * cette methode permer de verifier qu'une quete a bien été réalisée
     * @param precondition
     * @return boolean
     */
    private boolean estRealisee(int precondition) {
        return Arrays.asList(quetesRealisee).contains(precondition);
    }

    /**
     * cette methode permet d'ajouter une quete à la solution et de réaliser
     * les manipulations supplémentaires nécessaires
     * @param indice
     */
    private void realisonLaQuete(Quete queteEnCour, int indice, Quete[] solution, ArrayList<Quete> scenarioListe) {
        solution[indice] = queteEnCour;
        quetesRealisee[indice] = queteEnCour.getChNumero();
        scenarioListe.remove(queteEnCour);
        experienceAccumulee += queteEnCour.chExperience;
    }

    /**
     * Cette methode permet de faire les étapes nécessaires si la prochaine quete à réaliser possede une seule precondition
     * @param preconditions
     * @param queteEnCour
     * @param indice
     * @param solution
     * @param scenarioListe
     */
    private void realisationLaQueteSiUnePrecondition(int [][] preconditions, Quete queteEnCour, int indice, Quete[] solution, ArrayList<Quete> scenarioListe) {
        if (preconditions[0][1] == 0 && estRealisee(preconditions[0][0])) {
            realisonLaQuete(queteEnCour, indice, solution, scenarioListe);
        }
        if (estRealisee(preconditions[0][1]) || estRealisee(preconditions[0][0])) {
            realisonLaQuete(queteEnCour, indice, solution, scenarioListe);
        }
    }

    /**
     * Cette methode permet de faire les étapes nécessaires si la prochaine quete à réaliser possede deux preconditions
     * @param preconditions
     * @param queteEnCour
     * @param indice
     * @param solution
     * @param scenarioListe
     */
    private void realisationLaQueteSiDeuxPreconditions(int [][] preconditions, Quete queteEnCour, int indice, Quete[] solution, ArrayList<Quete> scenarioListe) {
        if (estRealisee(preconditions[0][0]) && estRealisee(preconditions[1][0])) {
            realisonLaQuete(queteEnCour, indice, solution, scenarioListe);
        }
        if (estRealisee(preconditions[0][0]) && (estRealisee(preconditions[1][1]) || estRealisee(preconditions[1][0]))) {
            realisonLaQuete(queteEnCour, indice, solution, scenarioListe);
        }
        if ((estRealisee(preconditions[0][1]) || estRealisee(preconditions[0][0])) && estRealisee(preconditions[1][0])) {
            realisonLaQuete(queteEnCour, indice, solution, scenarioListe);
        }
        if ((estRealisee(preconditions[0][1]) || estRealisee(preconditions[0][0])) && (estRealisee(preconditions[1][1]) || estRealisee(preconditions[1][0]))) {
            realisonLaQuete(queteEnCour, indice, solution, scenarioListe);
        }
    }

    /**
     * la methode solutionEfficace() renvoie un tableau avec les quetes dans l'ordre pour une solution dite efficace
     * @return Quete[]
     */
    public Quete[] solutionEfficace() {
        ArrayList<Quete> scenarioListe = scenarioEnCour.getChQuetes();
        Quete[] solution = new Quete[scenarioListe.size()];

        while (experienceAccumulee < experienceNecessaireQueteFinale || !estRealisee(0)) {
            for (int i=0; i < scenarioListe.size(); i++) {
                Quete queteEnCour = scenarioListe.get(i);

                if (!queteEnCour.possedePreconditions()) {
                    realisonLaQuete(queteEnCour, i, solution, scenarioListe);
                    System.out.println(queteEnCour+"\nOk");
                    Arrays.toString(quetesRealisee);
                    System.out.println(experienceAccumulee);
                }
                if (queteEnCour.possedePreconditions()) {
                    int nbPreconditions = queteEnCour.nbPreconditions();
                    int [][] preconditions = queteEnCour.getChPreconditions();
                    if (nbPreconditions == 1) {
                        realisationLaQueteSiUnePrecondition(preconditions, queteEnCour, i, solution, scenarioListe);
                        System.out.println(queteEnCour+"\nOk");
                        Arrays.toString(quetesRealisee);
                        System.out.println(experienceAccumulee);
                    }
                    if (nbPreconditions == 2) {
                        realisationLaQueteSiDeuxPreconditions(preconditions, queteEnCour, i, solution, scenarioListe);
                        System.out.println(queteEnCour+"\nOk");
                        Arrays.toString(quetesRealisee);
                        System.out.println(experienceAccumulee);
                    }
                }
            }
        }
        if (!Arrays.asList(quetesRealisee).contains(queteFinale)) {
            solution[solution.length-1] = queteFinale;
            quetesRealisee[quetesRealisee.length-1] = queteFinale.getChNumero();
            scenarioListe.remove(queteFinale);
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
