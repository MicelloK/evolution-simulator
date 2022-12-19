package agh.oop.proj;

import java.util.*;

public class CorpsesMap extends AbstractWorldMap {
    protected CorpsesMap(Settings settings) {
        super(settings);

        emptyPreferred = getPreferred();
        emptyNotPreferred = getNotPreferred();
        updatePreferredPosition();
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
}
