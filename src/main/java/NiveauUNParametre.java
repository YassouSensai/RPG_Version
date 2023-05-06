import java.util.ArrayList;
import java.util.Arrays;

public class NiveauUNParametre {
    Scenario scenarioEnCour;
    ArrayList<Quete> quetesScenario;
    Quete queteFinale;
    int experienceAccumulee = 0;
    int experienceNecessaireQueteFinale;
    ArrayList<Integer> quetesRealisee = new ArrayList<>();
    Position positionDepart = new Position(0,0);
    int dureeAccumulee = 0;

    /**
     * Constructeur de la classe NiveauUN
     * @param parScenario
     */
    public NiveauUNParametre(Scenario parScenario) {
        scenarioEnCour = parScenario;
        queteFinale = parScenario.queteFinale();
        experienceNecessaireQueteFinale = parScenario.queteFinale().getChExperience();
        quetesScenario = parScenario.getChQuetes();
    }

    /**
     * cette méthode sert à mettre à jour les differents champs afin de pouvoir afficher une solution efficace et/ou exhaustive sans probleme
     */
    private void miseAJour() {
        for (Quete quete : quetesScenario) {
            if (quete.chRealisee)
                quete.chRealisee = true;
        }
        quetesRealisee = new ArrayList<>();
    }


    /**
     * permet de retourner une quete a partir de son numero (lorsque celle-ci se trouve dans les preconditions d'une autre quete par exemple)
     * @param quetePrecondition
     * @return
     */
    public Quete rechercheQuete(int quetePrecondition) {
        for (Quete quete : quetesScenario) {
            if (quete.getChNumero() == quetePrecondition) {
                return quete;
            }
        }
        return null;
    }

    public Quete queteLaPlusProche() {
        Quete plusProches = null;
        int deplacementMin = 15000;
        for (Quete quete : quetesScenario) {
            if (!estRealisee(quete.getChNumero())) {
                int deplacementEnCour = positionDepart.deplacement(quete.chPosition);
                if (deplacementEnCour <= deplacementMin)
                    plusProches = quete;
            }
        }
        return plusProches;
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
        int nbPreconditions = queteEnCour.nbPreconditions();

        if (nbPreconditions == 2) {
            return (estRealisee(preconditions[0][0]) || estRealisee(preconditions[0][1])) && (estRealisee(preconditions[1][0]) || estRealisee(preconditions[1][1]));
        } else if (nbPreconditions == 1) {
            return (estRealisee(preconditions[0][0]) || estRealisee(preconditions[0][1]));
        }
        else {
            return true;
        }
    }

    private void siUnePreconditionNonValidee(ArrayList<Quete> solution, int[][] preconditions) {
        Quete quete1,quete2;
        quete1 = rechercheQuete(preconditions[0][0]);
        if (preconditions[0][1] == 0)
            realisonLaQuete(quete1,solution);

        quete2 = rechercheQuete(preconditions[0][1]);
        if (positionDepart.deplacement(quete1.chPosition) <= positionDepart.deplacement(quete2.chPosition)) {
            realisonLaQuete(quete1, solution);
        }
        else {
            realisonLaQuete(quete2, solution);
        }
    }
    private void siDeuxPreconditionsNonValidees(ArrayList<Quete> solution, int[][] preconditions) {
        Quete quete1,quete2,quete3,quete4;
        quete1 = rechercheQuete(preconditions[0][0]);
        quete3 = rechercheQuete(preconditions[1][0]);
        if (preconditions[0][1] == 0 && preconditions[1][1] == 0) {
            realisonLaQuete(quete1, solution);
            realisonLaQuete(quete3, solution);

        }
        if (preconditions[1][1] == 0) {
            quete2 = rechercheQuete(preconditions[0][1]);
            if (positionDepart.deplacement(quete1.chPosition) <= positionDepart.deplacement(quete2.chPosition))
                realisonLaQuete(quete1, solution);
            else
                realisonLaQuete(quete2, solution);
            realisonLaQuete(quete3, solution);
        }
        else {
            quete2 = rechercheQuete(preconditions[0][1]);
            quete4 = rechercheQuete(preconditions[1][1]);
            if (positionDepart.deplacement(quete1.chPosition) <= positionDepart.deplacement(quete2.chPosition))
                realisonLaQuete(quete1, solution);
            else
                realisonLaQuete(quete2, solution);
            if (positionDepart.deplacement(quete3.chPosition) <= positionDepart.deplacement(quete4.chPosition))
                realisonLaQuete(quete3, solution);
            else
                realisonLaQuete(quete4, solution);
        }
    }


    /**
     * la methode realisonLaQuete() permet de faire les differents ajouts (solution, experience, duree, ...)
     * @param queteEnCour
     * @param solution
     */
    private void realisonLaQuete(Quete queteEnCour, ArrayList<Quete> solution) {
        if (!estRealisee(queteEnCour.getChNumero())) {
            System.out.println("\n quete en cours : "+queteEnCour+"\n");
            if (preconditionsValidee(queteEnCour)) {
                quetesRealisee.add(queteEnCour.getChNumero());
                solution.add(queteEnCour);
                positionDepart = queteEnCour.getChPosition();

            }
            else {
                int nbPreconditions = queteEnCour.nbPreconditions();
                int[][] preconditions = queteEnCour.getChPreconditions();
                if (nbPreconditions == 1)
                    siUnePreconditionNonValidee(solution, preconditions);
                if (nbPreconditions == 2)
                    siDeuxPreconditionsNonValidees(solution, preconditions);
            }
            queteEnCour.chRealisee = true;
        }
        if (queteEnCour != queteFinale)
            experienceAccumulee += queteEnCour.getChExperience();


    }




    /**
     * la methode solutionEfficace() renvoie un tableau avec les quetes dans l'ordre pour une solution dite efficace
     * @return ArrayList<Quete>
     */
    public ArrayList<Quete> solutionEfficace() {
        miseAJour();
        ArrayList<Quete> solution = new ArrayList<>();

        while (experienceAccumulee < experienceNecessaireQueteFinale) {
            realisonLaQuete(queteFinale, solution);
            if (experienceAccumulee < experienceNecessaireQueteFinale) {
                realisonLaQuete(queteLaPlusProche(), solution);
            }
        }

        System.out.println("experience totale : " + experienceAccumulee);
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
