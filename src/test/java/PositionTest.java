import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void deplacement() {
        System.out.println("Test de la méthode déplacement");

        Position [] tabPositions = {new Position(0,0), new Position(5,5), new Position(3, 1)};
        int [] tabReponses = {0, 10, 4, 10, 0, 6, 4, 6, 0};

        int incrementation = 0;
        for (int i=0; i < tabPositions.length; i++) {
            for (int j=0; j < tabPositions.length; j++) {
                assertEquals(tabPositions[i].deplacement(tabPositions[j]), tabReponses[incrementation]);
                incrementation++;
            }
        }
    }

    @Test
    void premiereQueteARealiser() {
        System.out.println("Test de la méthode premiereQueteARealiser");

        Position posAppelante = new Position(0,0);
        Quete[][] tabQuetes = {{new Quete("1|(4, 3)|()|2|100|explorer pic de Bhanborim"), new Quete("0|(1, 1)|((3,4),)|4|350|vaincre Araignée lunaire"), new Quete("0|(4, 4)|((3,4),)|4|350|vaincre Araignée lunaire")},
                {new Quete("1|(0, 0)|()|2|100|explorer pic de Bhanborim"), new Quete("0|(1, 1)|((3,4),)|4|350|vaincre Araignée lunaire"), new Quete("0|(4, 4)|((3,4),)|4|350|vaincre Araignée lunaire")}};
        Quete[] tabReponses = {tabQuetes[0][1], tabQuetes[1][0]};

        for (int i=0; i<tabQuetes.length; i++) {
            assertEquals(posAppelante.premièreQueteARealiser(tabQuetes[i]), tabReponses[i]);
        }
    }
}