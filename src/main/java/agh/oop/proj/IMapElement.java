package agh.oop.proj;

public interface IMapElement {

    boolean isAnimal();

    Vector2d getPosition();

    MoveDirection getOrientation();

    int getImageIdx();

    void setObserver(IElementChangeObserver observer);

    int getActiveGenome();
}
