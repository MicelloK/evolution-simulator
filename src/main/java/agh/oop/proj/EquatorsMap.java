package agh.oop.proj;

import java.util.*;


public class EquatorsMap extends AbstractWorldMap {
    private final List<Vector2d> preferredPositions = new ArrayList<>();
    private final float midY;
    private final int mapSize;

    protected EquatorsMap(int width, int height, int grassPerDay) {
        super(width, height, grassPerDay);

        midY = (height - 1) / (float) 2;
        mapSize = width * height;

        initMap(width, height);
        preferredPositions.sort((o1, o2) -> Float.compare(Math.abs(o1.y() - midY), Math.abs(o2.y() - midY)));
    }

    private void initMap(int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Vector2d position = new Vector2d(i, j);
                MapSquare square = new MapSquare();
                elements.put(position, square);
                preferredPositions.add(position);
            }
        }
    }

    private List<Vector2d> getPreferred() {
        return preferredPositions.subList(0, (int) Math.round(0.2 * mapSize));
    }

    private List<Vector2d> getNotPreferred() {
        return preferredPositions.subList((int) Math.round(0.2 * mapSize), preferredPositions.size());
    }

    public void growGrass(int grassQuantity) {

    }
}
