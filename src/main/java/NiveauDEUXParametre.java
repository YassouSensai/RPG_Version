import java.util.ArrayList;

public class NiveauDEUXParametre {
    Scenario scenarioEnCour;
    ArrayList<Quete> quetesScenario;
    Quete queteFinale;
    int experienceAccumulee = 0;
    int experienceNecessaireQueteFinale;
    ArrayList<Integer> quetesRealisee = new ArrayList<>();
    Position positionDepart = new Position(0,0);
    int dureeAccumulee = 0;

    /**
     * Constructeur de la classe NiveauUN a partir d'un scenario
     *
     * @param parScenario
     */
    public NiveauDEUXParametre(Scenario parScenario) {
        scenarioEnCour = parScenario;
        queteFinale = parScenario.queteFinale();
        experienceNecessaireQueteFinale = parScenario.queteFinale().getChExperience();
        quetesScenario = parScenario.getListeQuetes();
    }

    /**
     * Cette methode sert a mettre a jour les differents champs afin de pouvoir
     * afficher une solution efficace et/ou exhaustive sans probleme.
     *
     * c'est à dire à partir du même objet de la classe NiveauUNParametre.
     */
    private void miseAJour() {
        for (Quete quete : quetesScenario) {
            if (quete.chRealisee)
                quete.setChRealisee(false);
        }
        quetesRealisee = new ArrayList<>();
        dureeAccumulee = 0;
        experienceAccumulee = 0;
        positionDepart = new Position(0,0);
    }

    /**
     * Cette methode permet de verifier qu'une quete a bien ete realisee.
     * Plus precisement, elle verifie que le numero d'une quete se trouve bien dans la liste queteRealisee.
     *
     * @param precondition
     * @return boolean
     */
    private boolean estRealisee(int precondition) {
        return quetesRealisee.contains(precondition);
    }


    /**
     * Cette methode permet de retourner une quete a partir de son numero
     * (lorsque celle-ci se trouve dans les preconditions d'une autre quete par exemple).
     *
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
     * Cette methode permet de dire si les preconditions d'une quete sont validees ou non.
     *
     * Ici on parle des preconditions de quete, les verifications se font donc sur les champs
     * precond1,precond2,precond3 et precond4 du parametre parQuete.
     *
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
     * Cette methode permet de dire si les preconditions de n'importe quelle quete sont validee ou non
     * (quete normale ou quete finale).
     *
     * Cette methode utilisera la methode quetePreconditionsValidee et comparera l'experience accumulee à l'experience nécessaire.
     *
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
     * Cette methode permet de realiser toutes les manipulations necessaires a la realisation d'une quete.
     * Cette methode va également renvoyer une chaîne de caractère qui representera l'historique des déplacement et des quetes realisée.
     *
     * @param parQuete
     * @param solution
     * @return String
     */
    private String realisonLaQuete(Quete parQuete, ArrayList<Quete> solution, String solutionString) {

        // ex : (+7 : deplacement de (0,0) a (4,3))
        String ligneDeplacement = "";

        // ex : (+2 : quete 1 (total xp : 100))
        String ligneQuete = "";

        if (preconditionsValidee(parQuete)) {

            // Ce bloc sert à mettre à jour la durée totale, mettre à jour la prochaine position de depart
            // ainsi que construire la chaîne de caractère ligneDeplacement
            int dureeDeplacement = positionDepart.deplacement(parQuete.chPosition);
            dureeAccumulee += dureeDeplacement + parQuete.getChDuree();
            Position positionDeplacement = parQuete.getChPosition();
            ligneDeplacement += "\n+" + dureeDeplacement + " : deplacement de " + positionDepart + " a " + positionDeplacement;
            positionDepart = positionDeplacement;

            // Dans ce bloc, on ajoute la quete à la solution et on déclare la solution comme étant réalisée
            solution.add(parQuete);
            quetesRealisee.add(parQuete.getChNumero());
            parQuete.setChRealisee(true);

            if (!parQuete.estQueteFinale())
                experienceAccumulee += parQuete.getChExperience();

            // Construction de la chaîne de caractère ligneQuete
            ligneQuete += "\n+" + parQuete.getChDuree() + " : quete " + parQuete.getChNumero() + " (total xp : " + experienceAccumulee + ")";
        }
        else {
            int nbPreconditions = parQuete.nbPreconditions();
            Quete quete1 = rechercheQuete(parQuete.precond1);
            Quete quete2, quete3, quete4;

            if (nbPreconditions == 1) {
                if (parQuete.precond3 != 0) {
                    quete3 = rechercheQuete(parQuete.precond3);

                    if (positionDepart.deplacement(quete1.chPosition) <= positionDepart.deplacement(quete3.chPosition))
                        realisonLaQuete(quete3, solution, solutionString);
                    else
                        realisonLaQuete(quete1, solution, solutionString);                }
            } else if (nbPreconditions == 2) {
                quete3 = rechercheQuete(parQuete.precond3);
                if (parQuete.precond2 != 0) {
                    quete2 = rechercheQuete(parQuete.precond2);
                }

                else if (parQuete.precond4 != 0) {
                    quete4 = rechercheQuete(parQuete.precond4);
                }
                else {
                    quete2 = rechercheQuete(parQuete.precond2);
                    quete4 = rechercheQuete(parQuete.precond4);
                }



            }
        }

        solutionString += ligneDeplacement + ligneQuete;
        return solutionString;
    }





    /**
     * La methode solutionEfficace() renvoie une ArrayList avec les quetes dans l'ordre pour une solution efficace ("gloutonne").
     *
     * @return ArrayList<Quete>
     */
    public ArrayList<Quete> solutionEfficace() {
        System.out.println("\n\nSolution efficace 'glouton' pour le scenario ! " );

        miseAJour();
        ArrayList<Quete> solution = new ArrayList<>();
        String solutionString = "";


        System.out.println(solutionString += "\n\nRapport : duree totale = " + dureeAccumulee + " et experience totale = " + experienceAccumulee + " et nombre de quetes realisees = " + solution.size()+"/"+quetesScenario.size());
        return solution;

    }

    /**
     * Methode toString de la classe NiveauUN.
     *
     * @return String
     */
    public String toString() {
        return "Niveau 1 :\nQuete finale : " + queteFinale.toString() + "\nNombre total de quetes : " + scenarioEnCour.listeQuetes.size();
    }

}
