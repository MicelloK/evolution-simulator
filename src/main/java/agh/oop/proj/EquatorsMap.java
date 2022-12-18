package agh.oop.proj;

import java.util.*;


public class EquatorsMap extends AbstractWorldMap {
    protected EquatorsMap(int width, int height) {
        super(width, height);

        float midY = (height - 1) / (float) 2;

        preferredPositions.sort((o1, o2) -> Float.compare(Math.abs(o1.y() - midY), Math.abs(o2.y() - midY)));
        emptyPreferred = getPreferred();
        emptyNotPreferred = getNotPreferred();
    }
}
