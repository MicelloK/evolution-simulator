package agh.oop.proj;

import java.util.Objects;

enum SquareType {
    JUNGLE, STEPPE
}

public class MapSquare {
    private final Vector2d position;
    private final SquareType type;
    private boolean grass;

    public MapSquare(Vector2d position, SquareType type) {
        this.position = position;
        this.type = type;
        this.grass = false;
    }

    public Vector2d getPosition() {
        return position;
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

    @Override
    public int hashCode() {
        return Objects.hash(position.x(), position.y());
    }
}
