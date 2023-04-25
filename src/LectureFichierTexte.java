import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LectureFichierTexte {

    public LectureFichierTexte(){
    }

    /**
     * Méthode lecture ce la classe LectureFichierTexte.
     * Cette méthode permet de créer un scenario à partir d'un fichier texte qui contient plusieurs lignes qui correspondent à des quêtes.
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
