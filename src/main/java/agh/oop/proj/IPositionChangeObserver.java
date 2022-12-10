package agh.oop.proj;

public interface IPositionChangeObserver {
    boolean positionChanged(Vector2d oldPosition, Vector2d newPosition, IMapElement object);
}
