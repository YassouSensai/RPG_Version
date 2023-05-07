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
     * cette methode permet de verifier qu'une quete a bien été réalisée
     * @param precondition
     * @return boolean
     */
    private boolean estRealisee(int precondition) {
        return quetesRealisee.contains(precondition);
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


    /**
     * Cette quete permet de retourner la quete la plus proche qui n'a pas encore été effectué
     * @return Quete
     */
    private Quete queteLaPlusProche() {
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
     * Cette methode permet de dire si les preconditions d'une quete sont validee (ici on parle des preconditions de quete)
     * Les verifications se font donc sur le champs chPreconditions d'une quete
     * @param parQuete
     * @return boolean
     */
    private boolean quetePreconditionsValidee(Quete parQuete) {
        int nbPrecondition = parQuete.nbPreconditions();
        int[][] preconditions = parQuete.getChPreconditions();

        if (nbPrecondition == 2) {
            return ((estRealisee(preconditions[0][0]) || estRealisee(preconditions[0][1])) && (estRealisee(preconditions[1][0]) || estRealisee(preconditions[1][1])));
        }
        else if (nbPrecondition == 1) {
            return (estRealisee(preconditions[0][0]) || estRealisee(preconditions[0][1]));
        }
        else {
            return true;
        }
    }

    /**
     * cette methode permet de dire si les preconditions de n'importe quelle quete sont validee ou non
     * (quete normale ou quete finale)
     * @param parQuete
     * @return boolean
     */
    private boolean preconditionsValidee(Quete parQuete) {
        if (parQuete.estQueteFinale()) {
            return (experienceAccumulee >= experienceNecessaireQueteFinale && quetePreconditionsValidee(parQuete));
        }
        else {
            return quetePreconditionsValidee(parQuete);
        }
    }







    /**
     * la methode solutionEfficace() renvoie un tableau avec les quetes dans l'ordre pour une solution dite efficace
     * @return ArrayList<Quete>
     */
    public ArrayList<Quete> solutionEfficace() {
        miseAJour();
        ArrayList<Quete> solution = new ArrayList<>();

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
