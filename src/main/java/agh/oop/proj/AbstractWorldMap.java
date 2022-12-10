package agh.oop.proj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    private final Map<Vector2d, MapSquare> elements;
    private int animalsNumber;
    private int grassNumber;
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;

    protected AbstractWorldMap(int width, int height) {
        elements = new HashMap<>();
        animalsNumber = 0;
        grassNumber = 0;
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
    public boolean positionChanged(Vector2d oldPosition, Vector2d newPosition, IMapElement object) {
        if (canMoveTo(newPosition)) {
            elements.get(oldPosition).removeObject(object);
            elements.get(newPosition).placeObject(object);
            return true;
        }
        return false;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return lowerLeft.follows(position) && upperRight.precedes(position);
    }

    @Override
    public boolean place(IMapElement object) {
        Vector2d position = object.getPosition();
        elements.get(position).placeObject(object);
        animalsNumber += 1;
        return true; //?
    }

    public boolean deleteObject(IMapElement object) {
        Vector2d position = object.getPosition();
        if (elements.get(position).getObjects().contains(object)) {
            elements.get(position).removeObject(object);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        MapSquare square = elements.get(position);
        return !square.getObjects().isEmpty();
    }

    @Override
    public List<IMapElement> objectsAt(Vector2d position) {
        MapSquare square = elements.get(position);
        return square.getObjects();
    }

    public boolean addGrass(Vector2d position) {
        MapSquare square = elements.get(position);
        if (!square.didGrassGrow()) {
            square.growGrass();
            grassNumber += 1;
            return true;
        }
        return false;
    }

    public boolean deleteGrass(Vector2d position) {
        MapSquare square = elements.get(position);
        if (square.didGrassGrow()) {
            square.eatGrass();
            grassNumber -= 1;
            return true;
        }
        return false;
    }

    public int getAnimalsNumber() {
        return animalsNumber;
    }

    public int getGrassNumber() {
        return grassNumber;
    }
}
