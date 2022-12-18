package agh.oop.proj;

import java.util.*;

public class CorpsesMap extends AbstractWorldMap {
    private final List<Vector2d> preferredPositions = new LinkedList<>();
    private final List<Vector2d> emptyPreferred;
    private final List<Vector2d> emptyNotPreferred;
    private final int mapSize;

    protected CorpsesMap(int width, int height) {
        super(width, height);

        mapSize = width * height;
        initMap(width, height);
        emptyPreferred = getPreferred();
        emptyNotPreferred = getNotPreferred();
        updatePreferredPosition();
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

    private void updatePreferredPosition() {
        preferredPositions.sort((o1, o2) -> Integer.compare(elements.get(o1).getDeathCounter(), elements.get(o2).getDeathCounter()));
        updateEmptyPositions();
    }

    private void updateEmptyPositions() {
        for (Vector2d position : getPreferred()) {
            if (emptyNotPreferred.contains(position)) {
                emptyNotPreferred.remove(position);
                emptyPreferred.add(position);
            }
        }
        for (Vector2d position : getNotPreferred()) {
            if (emptyPreferred.contains(position)) {
                emptyPreferred.remove(position);
                emptyNotPreferred.add(position);
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
