import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NiveauUNParametreTest {

    @Test
    void solutionEfficace() {
        System.out.println("Test de la methode solutionefficace()");
    }

    @Test
    void solutionExhaustive() {
        System.out.println("Test de la methode solutionExhaustive()");
    }

    @Test
    void rechercheQuete() {
        NiveauUNParametre niveauUNTest = new NiveauUNParametre(new LectureFichierTexte().lecture(new File("scenarios"+ File.separator+"scenario_0.txt")));
        ArrayList<Quete> niveauUnQuetesTest = niveauUNTest.scenarioEnCour.getListeQuetes();
        int[] tabNumeroQuete = {1,2,3};
        Quete[] tabReponse = {niveauUnQuetesTest.get(0),niveauUnQuetesTest.get(1),niveauUnQuetesTest.get(2)};

        for (int i=0; i<tabNumeroQuete.length; i++) {
            assertEquals(tabReponse[i], niveauUNTest.rechercheQuete(tabNumeroQuete[i]));
        }
    }
}