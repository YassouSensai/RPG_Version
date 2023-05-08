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
     * cette méthode sert à mettre à jour les differents champs afin de pouvoir
     * afficher une solution efficace et/ou exhaustive sans probleme
     * c'est à dire à partir du même objet de la classe NiveauUNParametre.java.
     */
    private void miseAJour() {
        for (Quete quete : quetesScenario) {
            if (quete.chRealisee)
                quete.setChRealisee(true);
        }
        quetesRealisee = new ArrayList<>();
        dureeAccumulee = 0;
        experienceAccumulee = 0;
        positionDepart = new Position(0,0);
    }

    /**
     * cette methode permet de verifier qu'une quete a bien été réalisée.
     * Plus precisement, elle vérifie que le numéro d'une quête se trouve bien dans la liste queteRealisee.
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
     * Cette quete permet de retourner la quete la plus proche qui n'a pas encore été effectué et dont les preconditions sont validée
     * @return Quete
     */
    private Quete queteLaPlusProche() {
        Quete plusProches = queteFinale;
        int deplacementMin = 15000;
        for (Quete quete : quetesScenario) {
            if (!quete.estRealisee() && preconditionsValidee(quete)) {
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
            return ((estRealisee(parQuete.precond1) || estRealisee(parQuete.precond2)) && (estRealisee(parQuete.precond3) || estRealisee(parQuete.precond4)));
        }
        else if (nbPrecondition == 1) {
            return ((estRealisee(parQuete.precond1) || estRealisee(parQuete.precond2)));
        }
        else {
            return true;
        }
    }

    /**
     * cette methode permet de dire si les preconditions de n'importe quelle quete sont validee ou non
     * (quete normale ou quete finale)
     *
     * elle va utiliser la methode quetePreconditionsValidee et comparer l'experience accumulée à l'experience nécessaire
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
     * Cette methode permet de realiser toutes les manipulations necessaires à la realisation d'une quete
     * Cette methode va également renvoyer une chaîne de caractère qui representera l'historique des déplacement et des quetes realisée
     * @param parQuete
     * @param solution
     * @return String
     */
    private String realisonLaQuete(Quete parQuete, ArrayList<Quete> solution, String solutionString) {

        // ex : (+7 : déplacement de (0,0) à (4,3))
        String ligneDeplacement = "";

        // ex : (+2 : quête 1 (total xp : 100))
        String ligneQuete = "";

        if (preconditionsValidee(parQuete)) {

            // Ce bloc sert à mettre à jour la durée totale, mettre à jour la prochaine position de depart
            // ainsi que construire la chaîne de caractère ligneDeplacement
            int dureeDeplacement = positionDepart.deplacement(parQuete.chPosition);
            dureeAccumulee += dureeDeplacement + parQuete.getChDuree();
            Position positionDeplacement = parQuete.getChPosition();
            ligneDeplacement += "\n+" + dureeDeplacement + " : déplacement de " + positionDepart + " à " + positionDeplacement;
            positionDepart = positionDeplacement;

            // Dans ce bloc, on ajoute la quete à la solution et on déclare la solution comme étant réalisée
            solution.add(parQuete);
            quetesRealisee.add(parQuete.getChNumero());
            parQuete.setChRealisee(true);

            if (!parQuete.estQueteFinale())
                experienceAccumulee += parQuete.getChExperience();

            // Construction de la chaîne de caractère ligneQuete
            ligneQuete += "\n+" + parQuete.getChDuree() + " : quête " + parQuete.getChNumero() + " (total xp : " + experienceAccumulee + ")";
        }

        solutionString += ligneDeplacement + ligneQuete;
        return solutionString;
    }





    /**
     * la methode solutionEfficace() renvoie un tableau avec les quetes dans l'ordre pour une solution dite efficace
     * @return ArrayList<Quete>
     */
    public ArrayList<Quete> solutionEfficace() {
        miseAJour();
        ArrayList<Quete> solution = new ArrayList<>();
        String solutionString = "";

        while (!queteFinale.estRealisee()) {
            Quete queteARealiser = queteLaPlusProche();

            if (preconditionsValidee(queteFinale))
                solutionString = realisonLaQuete(queteFinale, solution, solutionString);
            else
                solutionString = realisonLaQuete(queteARealiser, solution, solutionString);
        }

        System.out.println(solutionString += "\n\nRapport : durée totale = " + dureeAccumulee + " et experience totale = " + experienceAccumulee);
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
