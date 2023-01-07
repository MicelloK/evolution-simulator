package agh.oop.proj;

public interface IElementChangeObserver {

    void positionChanged(Vector2d oldPosition, Vector2d newPosition, IMapElement object);

    void animalDies(IMapElement animal);
}
