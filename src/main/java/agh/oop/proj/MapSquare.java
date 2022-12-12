package agh.oop.proj;

import java.util.ArrayList;
import java.util.List;

enum SquareType {
    JUNGLE, STEPPE
}

public class MapSquare {
    private final SquareType type;
    private boolean grass;
    private final List<IMapElement> objects;
    private int deathCounter;

    public MapSquare(SquareType type) {
        this.type = type;
        this.grass = false;
        this.objects = new ArrayList<>();
        this.deathCounter = 0;
    }

    public SquareType getType() {
        return type;
    }

    public boolean didGrassGrow() {
        return grass;
    }

    public void growGrass() {
        grass = true;
    }

    public void eatGrass() {
        grass = false;
    }

    public void increaseDeathCounter() {
        deathCounter += 1;
    }

    public int getDeathCounter() {
        return deathCounter;
    }

    public List<IMapElement> getObjects() {
        return objects;
    }

    public void removeObject(IMapElement object) {
        objects.remove(object);
    }

    public void placeObject(IMapElement object) {
        objects.add(object);
    }
}
