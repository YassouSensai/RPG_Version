import org.junit.jupiter.api.Test;

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

    @org.junit.jupiter.api.Test
    void possedePreconditions() {
        System.out.println("Test de la methode possedePrecondition");

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
        int [] tabreponses = {0,1};

        for (int i=0; i < tabQuetes.length; i++) {
            assertEquals(tabQuetes[i].nbPreconditions(), tabreponses[i]);
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
}