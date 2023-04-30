import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void deplacement() {
        System.out.println("Test de la méthode déplacement");

        Position [] tabPositions = {new Position(0,0), new Position(5,5), new Position(new Quete("1|(3, 1)|()|2|50|dialoguer avec Alaric le mage noir"))};
        int [] tabReponses = {0, 10, 4, 10, 0, 6, 4, 6, 0};

        for (int i=0; i < tabPositions.length; i++) {
            for (int j=0; j < tabPositions.length; j++) {
                assertTrue(tabPositions[i].deplacement(tabPositions[j]) == tabReponses[i+j]);
            }
        }
    }
}