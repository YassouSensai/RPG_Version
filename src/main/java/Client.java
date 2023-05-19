import java.io.File;

public class Client {
    public static void main (String [] args) {


        File fichierScenario0 = new File("scenarios"+ File.separator+"scenario_8.txt");

        LectureFichierTexte premiereLecture = new LectureFichierTexte();
        Scenario scenario0 = LectureFichierTexte.lecture(fichierScenario0);


        System.out.println("\ntest affichage 7");
        NiveauUNParametre niveauUNTest = new NiveauUNParametre(scenario0);
        System.out.println(niveauUNTest.solutionEfficace());
        System.out.println(niveauUNTest.solutionExhaustive());



    }
}
