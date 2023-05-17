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

}
