package agh.oop.proj;

public interface IMapElement {

    boolean isAnimal();

    Vector2d getPosition();

    MoveDirection getOrientation();

    int getImage();

    void addObserver(IElementChangeObserver observer);
}
