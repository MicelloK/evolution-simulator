package agh.oop.proj;

public class EquatorsMap extends AbstractWorldMap {
    protected EquatorsMap(int width, int height, IMoveAllowed movementDetails, int reproductionEnergy) {
        super(width, height, movementDetails, reproductionEnergy);

        float midY = (height - 1) / (float) 2;

        preferredPositions.sort((o1, o2) -> Float.compare(Math.abs(o1.y() - midY), Math.abs(o2.y() - midY)));
        emptyPreferred = getPreferred();
        emptyNotPreferred = getNotPreferred();
    }
}
