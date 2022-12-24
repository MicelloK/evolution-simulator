package agh.oop.proj;

import java.util.Random;

public class PortalMoveAllowed implements IMoveAllowed {
    @Override
    public Vector2d newPosition(Vector2d oldPosition, Vector2d newPosition, Vector2d lowerLeft, Vector2d upperRight) {
        if (onEdge(oldPosition, lowerLeft, upperRight)) {
            return drawNewPosition(upperRight);
        }
        return oldPosition;
    }

    private Vector2d drawNewPosition(Vector2d upperRight) {
        int x = upperRight.x();
        int y = upperRight.y();
        Random random = new Random();
        return new Vector2d(random.nextInt(x), random.nextInt(y));
    }

    @Override
    public boolean canMoveTo(Vector2d position, Vector2d lowerLeft, Vector2d upperRight) {
        return true;
    }

    private boolean onEdge(Vector2d position, Vector2d lowerLeft, Vector2d upperRight) {
        return !(position.follows(lowerLeft) && position.precedes(upperRight));
    }

    @Override
    public int lotsEnergy(Vector2d position, Vector2d lowerLeft, Vector2d upperRight, int energyNeededForReproduction) {
        if (onEdge(position, lowerLeft, upperRight)) {
            return 1 + energyNeededForReproduction;
        } else {
            return 1;
        }
    }
}
