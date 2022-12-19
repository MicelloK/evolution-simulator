package agh.oop.proj;

public class EquatorsMap extends AbstractWorldMap {
    protected EquatorsMap(Settings settings) {
        super(settings);

        float midY = (settings.getMapHeight() - 1) / (float) 2;

        preferredPositions.sort((o1, o2) -> Float.compare(Math.abs(o1.y() - midY), Math.abs(o2.y() - midY)));
        emptyPreferred = getPreferred();
        emptyNotPreferred = getNotPreferred();
    }
}
