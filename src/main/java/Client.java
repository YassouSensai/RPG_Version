import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Client {
    public static void main (String [] args) {


        File fichierScenario0 = new File("scenarios"+ File.separator+"scenario_0.txt");

        LectureFichierTexte premiereLecture = new LectureFichierTexte();
        Scenario scenario0 = LectureFichierTexte.lecture(fichierScenario0);

        System.out.println("\ntest affichage 1");
        System.out.println(scenario0);

        System.out.println("\ntest affichage 2");
        System.out.println(scenario0.chQuetes.get(0).estQueteFinale());


        System.out.println("\ntest affichage 3");
        System.out.println(new NiveauUNParametre(scenario0));



        System.out.println("\ntest affichage 6");
        Quete test1 = scenario0.getChQuetes().get(1);
        Quete test2 = scenario0.getChQuetes().get(1);
        System.out.println(test1 == test2);


        System.out.println("\ntest affichage 7");
        NiveauUNParametre niveauUNTest = new NiveauUNParametre(scenario0);
        System.out.println(niveauUNTest.solutionEfficace());
        System.out.println(niveauUNTest.solutionExhaustive());
/*
        System.out.println("\ntest perso");
        ArrayList<Quete> test = scenario0.getChQuetes();
        Quete quete = test.get(0);
        quete.chNumero = 2;
        System.out.println(quete);
        System.out.println(test.get(0));
*/
        System.out.println("\n");
        System.out.println(new Quete("3|(1, 0)|()|7|100|dialoguer avec Morrigan la déesse de la mort").nbPreconditions());

    }
}
