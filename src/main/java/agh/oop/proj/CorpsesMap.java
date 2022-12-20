package agh.oop.proj;

public class CorpsesMap extends AbstractWorldMap {
    protected CorpsesMap(int width, int height, IMoveAllowed movementDetails, int reproductionEnergy) {
        super(width, height, movementDetails, reproductionEnergy);

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
