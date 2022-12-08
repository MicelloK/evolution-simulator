package agh.oop.proj;

public interface IWorldMap {
    boolean canMoveTo(MapSquare square);

    boolean place(Animal animal);

    boolean isOccupied(MapSquare square);

    Object objectAt(MapSquare square);
}
