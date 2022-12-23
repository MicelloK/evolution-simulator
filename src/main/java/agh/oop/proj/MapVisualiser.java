package agh.oop.proj;

public class MapVisualiser {
    private static final String EMPTY_CELL = " ";
    private static final String GRASS_CELL = "*";
    private static final String PREFERRED_CELL = "P";
    private static final String FRAME_SEGMENT = "-";
    private static final String CELL_SEGMENT = "|";
    private final AbstractWorldMap map;

    public MapVisualiser(AbstractWorldMap map) {
        this.map = map;
    }

    public String draw(Vector2d lowerLeft, Vector2d upperRight) {
        StringBuilder builder = new StringBuilder();
        for (int i = upperRight.y() + 1; i >= lowerLeft.y() - 1; i--) {
            if (i == upperRight.y() + 1) {
                builder.append(drawHeader(lowerLeft, upperRight));
            }
            builder.append(String.format("%3d: ", i));
            for (int j = lowerLeft.x(); j <= upperRight.x() + 1; j++) {
                if (i < lowerLeft.y() || i > upperRight.y()) {
                    builder.append(drawFrame(j <= upperRight.x()));
                } else {
                    builder.append(CELL_SEGMENT);
                    if (j <= upperRight.x()) {
                        builder.append(drawObject(new Vector2d(j, i)));
                    }
                }
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

    private String drawFrame(boolean innerSegment) {
        if (innerSegment) {
            return FRAME_SEGMENT + FRAME_SEGMENT;
        } else {
            return FRAME_SEGMENT;
        }
    }

    private String drawHeader(Vector2d lowerLeft, Vector2d upperRight) {
        StringBuilder builder = new StringBuilder();
        builder.append(" y\\x ");
        for (int j = lowerLeft.x(); j < upperRight.x() + 1; j++) {
            builder.append(String.format("%2d", j));
        }
        builder.append(System.lineSeparator());
        return builder.toString();
    }

    private String drawObject(Vector2d currentPosition) {
        String result;
        MapSquare square = map.getElements().get(currentPosition);
        if (!square.getObjects().isEmpty()) {
            result = String.valueOf(square.getObjects().size());
        }
        else if (square.didGrassGrow()) {
            result = GRASS_CELL;
        } else if (map.getPreferred().contains(currentPosition)) {
            result = PREFERRED_CELL;
        } else {
            result = EMPTY_CELL;
        }
        return result;
    }
}
