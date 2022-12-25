package agh.oop.proj;

import java.util.Random;

public class PortalMoveAllowed implements IMoveAllowed {
    @Override
    public Vector2d newPosition(Vector2d oldPosition, Vector2d newPosition, Vector2d lowerLeft, Vector2d upperRight) {
        if (onEdge(newPosition, lowerLeft, upperRight)) {
            return drawNewPosition(upperRight);
        }
        return newPosition;
    }

    private Vector2d drawNewPosition(Vector2d upperRight) {
        Random random = new Random();
        int x = random.nextInt(upperRight.x());
        int y = random.nextInt(upperRight.y());

        return new Vector2d(x, y);
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
