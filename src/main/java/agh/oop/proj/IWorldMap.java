package agh.oop.proj;

import java.util.List;

public interface IWorldMap {
    boolean canMoveTo(Vector2d position);

    boolean place(IMapElement animal);

    boolean isOccupied(Vector2d position);

    List<IMapElement> objectsAt(Vector2d position);
}
