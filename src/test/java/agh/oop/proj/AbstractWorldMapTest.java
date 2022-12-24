package agh.oop.proj;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractWorldMapTest {
    private final AbstractWorldMap e1Map = new EquatorsMap(10, 5, new EarthMoveAllowed(), 5);
    private final AbstractWorldMap e2Map = new EquatorsMap(10, 5, new PortalMoveAllowed(), 5);
    private final AbstractWorldMap c1Map = new CorpsesMap(10, 5, new EarthMoveAllowed(), 5);
    private final AbstractWorldMap c2Map = new CorpsesMap(10, 5, new PortalMoveAllowed(), 5);

    @Test
    void positionChanged() {
        Vector2d firstPosition = new Vector2d(1, 1);
        Vector2d newPosition = new Vector2d(1, 2);
//        new Animal()

    }

    @Test
    void getElements() {
    }

    @Test
    void getPreferred() {
    }

    @Test
    void getNotPreferred() {
    }

    @Test
    void isEmptySquares() {
    }

    @Test
    void drawPosition() {
    }

    @Test
    void animalDies() {
    }

    @Test
    void canMoveTo() {
    }

    @Test
    void newPosition() {
    }

    @Test
    void moveEnergyLost() {
    }

    @Test
    void place() {
    }

    @Test
    void getAnimalsNumber() {
    }

    @Test
    void getGrassNumber() {
    }

    @Test
    void eatGrass() {
    }
}