import java.io.File;

public class Client {
    public static void main (String [] args) {
        File fichierScenario0 = new File("scenarios"+ File.separator+"scenario_0.txt");

        LectureFichierTexte premiereLecture = new LectureFichierTexte();
        Scenario scenario0 = premiereLecture.lecture(fichierScenario0);

        System.out.println(scenario0);

    }
}
