package agh.oop.proj;

public interface IElementChangeObserver {
    boolean positionChanged(Vector2d oldPosition, Vector2d newPosition, IMapElement object);

    boolean animalDie(IMapElement animal);
}
