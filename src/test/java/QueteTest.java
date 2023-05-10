import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QueteTest {

    @org.junit.jupiter.api.Test
    void estQueteFinale() {
        System.out.println("Test de la methode estQuesteFinale");

        Quete [] tabQuetes = {new Quete("1|(4, 3)|()|2|100|explorer pic de Bhanborim"), new Quete("0|(1, 1)|((3,4),)|4|350|vaincre Araignée lunaire")};
        boolean [] tabReponses = {false, true};

        for (int i=0; i < tabQuetes.length; i++) {
            assertTrue(tabQuetes[i].estQueteFinale() == tabReponses[i]);
        }
    }


    @Test
    void nbPreconditions() {
        System.out.println("Test de la methode nbPreconditions");

        Quete [] tabQuetes = {new Quete("1|(4, 3)|()|2|100|explorer pic de Bhanborim"), new Quete("0|(1, 1)|((3,4),)|4|350|vaincre Araignée lunaire")};
        int [] tabReponses = {0,1};

        ArrayList<Quete> tabQuetesV2 = new LectureFichierTexte().lecture(new File("scenarios"+ File.separator+"scenario_1.txt")).getListeQuetes();
        int[] tabReponsesV2 = {1,2,2,0,0,1};

        for (int i=0; i < tabQuetes.length; i++) {
            assertEquals(tabReponses[i], tabQuetes[i].nbPreconditions());
        }

        for (int i=0; i<tabReponsesV2.length; i++) {
            assertEquals(tabReponsesV2[i], tabQuetesV2.get(i).nbPreconditions());
        }
    }

    @Test
    void estRealisee() {
        System.out.println("Test de la methode estRealisee()");

        Quete [] tabQuetes = {new Quete("1|(4, 3)|()|2|100|explorer pic de Bhanborim"), new Quete("0|(1, 1)|((3,4),)|4|350|vaincre Araignée lunaire")};
        tabQuetes[0].chRealisee = true;
        boolean [] tabreponses = {true, false};

        for (int i=0; i < tabQuetes.length; i++) {
            assertEquals(tabQuetes[i].chRealisee, tabreponses[i]);
        }

    }

    @Test
    void deplacement() {
        System.out.println("Test de la methode deplacement()");

        Quete queteAppelante = new Quete("9|(0, 0)|()|2|100|explorer pic de Bhanborim");
        Quete [] tabQuetes = {new Quete("1|(4, 3)|()|2|100|explorer pic de Bhanborim"), new Quete("0|(1, 1)|((3,4),)|4|350|vaincre Araignée lunaire")};
        int [] tabreponses = {7, 2};

        for (int i=0; i < tabQuetes.length; i++) {
            assertEquals(queteAppelante.deplacement(tabQuetes[i]), tabreponses[i]);
        }

    }

    @Test
    void testEstRealisee() {
        System.out.println("Test de la methode estRelisee()");

        Quete [] tabQuetes = {new Quete("1|(4, 3)|()|2|100|explorer pic de Bhanborim"), new Quete("0|(1, 1)|((3,4),)|4|350|vaincre Araignée lunaire")};
        tabQuetes[0].setChRealisee(true);
        boolean [] tabReponses = {true, false};

        for (int i=0; i < tabQuetes.length; i++) {
            assertEquals(tabQuetes[i].estRealisee(), tabReponses[i]);
        }
    }
}