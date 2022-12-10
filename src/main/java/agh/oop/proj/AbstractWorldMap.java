package agh.oop.proj;

import java.util.HashMap;
import java.util.Map;

abstract public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    private final Map<Vector2d, MapSquare> elements;
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;

    protected AbstractWorldMap(int width, int height) {
        elements = new HashMap<>();
        lowerLeft = new Vector2d(0, 0);
        upperRight = new Vector2d(width, height);
        initMap(width, height);
    }

    private void initMap(int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Vector2d position = new Vector2d(i, j);
                SquareType type = SquareType.STEPPE;
                MapSquare square = new MapSquare(type);
                elements.put(position, square);
            }
        }
    }

    @Override
    public boolean positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        if (canMoveTo(newPosition)) {
            IMapElement object = objectAt(oldPosition);

            elements.get(oldPosition).removeObject();
            elements.get(newPosition).placeObject(object);
            return true;
        }
        return false;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position) && lowerLeft.follows(position) && upperRight.precedes(position);
    }

    @Override
    public void place(IMapElement object) {
        Vector2d position = object.getPosition();
        MapSquare square = elements.get(position);
        square.placeObject(object);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        MapSquare square = elements.get(position);
        return square.getObject() != null;
    }

    @Override
    public IMapElement objectAt(Vector2d position) {
        MapSquare square = elements.get(position);
        return square.getObject();
    }
}
