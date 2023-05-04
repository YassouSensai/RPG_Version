import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Client {
    public static void main (String [] args) {
        File fichierScenario0 = new File("scenarios"+ File.separator+"scenario_0.txt");

        LectureFichierTexte premiereLecture = new LectureFichierTexte();
        Scenario scenario0 = premiereLecture.lecture(fichierScenario0);

        System.out.println("\ntest affichage 1");
        System.out.println(scenario0);

        System.out.println("\ntest affichage 2");
        System.out.println(scenario0.chQuetes.get(0).estQueteFinale());


        System.out.println("\ntest affichage 3");
        System.out.println(new NiveauUNParametre(scenario0));


        System.out.println("\ntest affichage 4");
        System.out.println(new Position(0,0).deplacement(new Position(new Quete("1|(3, 1)|()|2|50|dialoguer avec Alaric le mage noir"))));

        System.out.println("\ntest affichage 6");
        Quete test1 = scenario0.getChQuetes().get(1);
        Quete test2 = scenario0.getChQuetes().get(1);
        System.out.println(test1 == test2);

        System.out.println("\ntest affichage 7");
        NiveauUNParametre niveauUNTest = new NiveauUNParametre(scenario0);
        System.out.println(niveauUNTest.solutionEfficace());


    }
}
