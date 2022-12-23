package agh.oop.proj;

public interface IWorldMap {

    boolean canMoveTo(Vector2d position);

    boolean place(IMapElement animal);
}
