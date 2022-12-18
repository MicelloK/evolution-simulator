package agh.oop.proj;

import java.util.*;

abstract public class AbstractWorldMap implements IWorldMap, IElementChangeObserver {
    protected final Map<Vector2d, MapSquare> elements;
    private int animalsNumber;
    private int grassNumber;
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;

    protected AbstractWorldMap(int width, int height) {
        elements = new HashMap<>();
        lowerLeft = new Vector2d(0, 0);
        upperRight = new Vector2d(width, height);
        animalsNumber = 0;
        grassNumber = 0;
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
    public boolean animalDie(IMapElement animal) {
        Vector2d position = animal.getPosition();
        if (elements.get(position).getObjects().contains(animal)) {
            MapSquare square = elements.get(position);
            square.animalDie(animal);
            return true;
        }
        return false;
    }

    @Override // to bedzie zmieinione
    public boolean canMoveTo(Vector2d position) {
        return lowerLeft.follows(position) && upperRight.precedes(position);
    }

    @Override
    public boolean place(IMapElement object) {
        Vector2d position = object.getPosition();
        if (canMoveTo(position)) {
            elements.get(position).placeObject(object);
            animalsNumber += 1;
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

    public int getAnimalsNumber() {
        return animalsNumber;
    }

    public int getGrassNumber() {
        return grassNumber;
    }

    private void deleteGrass(Vector2d position) {
        elements.get(position).eatGrass();
        grassNumber -= 1;
    }

    private boolean isGrass(Vector2d position) {
        return elements.get(position).didGrassGrow();
    }

    public boolean eatGrass(Vector2d position) {
        if (isGrass(position)) {
            deleteGrass(position);
            return true;
        }
        return false;
    }
}
