package agh.oop.proj;

import java.util.*;


public class EquatorsMap extends AbstractWorldMap {
    private final List<Vector2d> preferredPositions = new LinkedList<>();
    private final List<Vector2d> emptyPreferred = new LinkedList<>();
    private final List<Vector2d> emptyNotPreferred = new LinkedList<>();
    private final int mapSize;

    protected EquatorsMap(int width, int height) {
        super(width, height);

        float midY = (height - 1) / (float) 2;
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

    private boolean isEmptySquares() {
        return !emptyPreferred.isEmpty() || !emptyNotPreferred.isEmpty();
    }

    private Vector2d drawPosition() {
        Random random = new Random();
        int preference = random.nextInt() % 10;
        Vector2d position;

        if (preference < 2 && !emptyPreferred.isEmpty()) {
            position = emptyPreferred.get(random.nextInt(emptyPreferred.size()));
        }
        else {
            position = emptyNotPreferred.get(random.nextInt(emptyNotPreferred.size()));
        }
        return position;
    }

    public void growGrass(int grassPerDay) {
        for (int i = 0; i < grassPerDay; i++) {
            if (isEmptySquares()) {
                Vector2d position = drawPosition();
                addGrass(position);

                if (emptyPreferred.contains(position)) {
                    emptyPreferred.remove(position);
                }
                else {
                    emptyNotPreferred.remove(position);
                }
            }
        }
    }
}
