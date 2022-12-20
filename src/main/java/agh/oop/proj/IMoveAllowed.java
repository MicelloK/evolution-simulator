package agh.oop.proj;

public interface IMoveAllowed {

    Vector2d newPosition(Vector2d position, Vector2d lowerLeft, Vector2d upperRight);

    boolean canMoveTo(Vector2d position, Vector2d lowerLeft, Vector2d upperRight);

    int lotsEnergy(Vector2d position, Vector2d lowerLeft, Vector2d upperRight, int energyNeededForReproduction);
}
