import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LectureFichierTexte {


    /**
     * Methode lecture ce la classe LectureFichierTexte.
     * Cette methode permet de creer un scenario a partir d'un fichier texte.
     * Ce fichier texte contient plusieurs lignes qui correspondent chacune a des quetes.
     *
     * @param fichier
     * @return
     */
    public static Scenario lecture(File fichier) {
        Scenario scenario = new Scenario();
        try {
            Scanner scanner = new Scanner(fichier);
            while (scanner.hasNext()) {
                scenario.ajout(new Quete(scanner.nextLine()));
            }
        }
        catch (FileNotFoundException error) {
            System.err.println(error.getMessage());
        }
        return scenario;
    }
}
