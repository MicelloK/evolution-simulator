package agh.oop.proj;
//trawa się nie rozmnarza,
public class Grass implements IMapElement{
    private final Vector2d position;

    public Grass(Vector2d position) {
        this.position = position;
    }

    @Override
    public boolean isAnimal() {
        return false;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public MoveDirection getOrientation() {
        return null;
    }

    @Override
    public int getImage() {
        return 0;
    }

    @Override
    public String toString() {
        return "*";
    }
}

