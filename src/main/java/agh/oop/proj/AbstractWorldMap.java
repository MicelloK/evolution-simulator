package agh.oop.proj;

import java.util.HashMap;

abstract public class AbstractWorldMap implements IWorldMap {
//    private final Map<MapSquare, IMapElement> animals = new HashMap<>();
    private final int WIDTH;
    private final int HEIGHT;

    protected AbstractWorldMap(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
    }

//    private initMap(int width, int height) {
//        for (int i = 0; i < width; i++) {
//
//        }
//    }

    @Override
    public boolean canMoveTo(MapSquare square) {
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        return false;
    }

    @Override
    public boolean isOccupied(MapSquare square) {
        return false;
    }

    @Override
    public Object objectAt(MapSquare square) {
        return null;
    }
}
