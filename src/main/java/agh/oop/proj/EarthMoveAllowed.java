package agh.oop.proj;

public class EarthMoveAllowed implements IMoveAllowed{
    @Override
    public Vector2d newPosition(Vector2d position, Vector2d lowerLeft, Vector2d upperRight) {
        Vector2d newPosition = position;
        if (onRightSide(position, lowerLeft, upperRight)) {
            newPosition = new Vector2d(lowerLeft.x(), position.y());
        }
        else if (onLeftSide(position, lowerLeft, upperRight)) {
            newPosition = new Vector2d(upperRight.x(), position.y());
        }
        return newPosition;
    }

    @Override
    public boolean canMoveTo(Vector2d position, Vector2d lowerLeft, Vector2d upperRight) {
        return position.y() <= upperRight.y() && position.y() >= lowerLeft.y();
    }

    @Override
    public int lotsEnergy(Vector2d position, Vector2d lowerLeft, Vector2d upperRight, int EnergyNeededToReproduction) {
        return 1;
    }

    private boolean onRightSide(Vector2d position, Vector2d lowerLeft, Vector2d upperRight) {
        return canMoveTo(position, lowerLeft, upperRight) && position.x() > upperRight.x();
    }

    private boolean onLeftSide(Vector2d position, Vector2d lowerLeft, Vector2d upperRight) {
        return canMoveTo(position, lowerLeft, upperRight) && position.x() < lowerLeft.x();
    }

}
