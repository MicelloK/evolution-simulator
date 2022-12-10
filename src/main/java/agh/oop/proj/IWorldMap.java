package agh.oop.proj;

public interface IWorldMap {
    boolean canMoveTo(Vector2d position);

    void place(IMapElement animal);

    boolean isOccupied(Vector2d position);

    Object objectAt(Vector2d position);
}
