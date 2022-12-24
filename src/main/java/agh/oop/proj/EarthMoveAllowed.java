package agh.oop.proj;

public class EarthMoveAllowed implements IMoveAllowed {
    @Override
    public Vector2d newPosition(Vector2d oldPosition, Vector2d newPosition,  Vector2d lowerLeft, Vector2d upperRight) {
        if (onRightSide(newPosition, lowerLeft, upperRight)) {
            newPosition = new Vector2d(lowerLeft.x(), newPosition.y());
        } else if (onLeftSide(newPosition, lowerLeft, upperRight)) {
            newPosition = new Vector2d(upperRight.x() - 1, newPosition.y());
        } else if (!canMoveTo(newPosition, lowerLeft, upperRight)) {
            newPosition = oldPosition;
        }
        return newPosition;
    }

    @Override
    public boolean canMoveTo(Vector2d position, Vector2d lowerLeft, Vector2d upperRight) {
        return position.y() < upperRight.y() && position.y() >= lowerLeft.y();
    }

    @Override
    public int lotsEnergy(Vector2d position, Vector2d lowerLeft, Vector2d upperRight, int EnergyNeededToReproduction) {
        return 1;
    }

    private boolean onRightSide(Vector2d position, Vector2d lowerLeft, Vector2d upperRight) {
        return canMoveTo(position, lowerLeft, upperRight) && position.x() >= upperRight.x();
    }

    private boolean onLeftSide(Vector2d position, Vector2d lowerLeft, Vector2d upperRight) {
        return canMoveTo(position, lowerLeft, upperRight) && position.x() < lowerLeft.x();
    }
}
