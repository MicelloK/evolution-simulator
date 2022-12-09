package agh.oop.proj;

import java.util.Objects;

enum SquareType {
    JUNGLE, STEPPE
}

public class MapSquare {
    private final SquareType type;
    private boolean grass;
    private IMapElement object;

    public MapSquare(SquareType type) {
        this.type = type;
        this.grass = false;
        this.object = null;
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

    public IMapElement getObject() {
        return object;
    }

    public void removeObject() {
        object = null;
    }

    public void placeObject(IMapElement object) {
        this.object = object;
    }
}
