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

        builder.append(" y\\x ");
        for (int i = lowerLeft.x(); i < upperRight.x(); i++) {
            builder.append(String.format("%2d", i));
        }
        builder.append(System.lineSeparator());

        for (int i = lowerLeft.y(); i < upperRight.y(); i++) {
            builder.append(String.format("%3d: ", i));
            for (int j = lowerLeft.x(); j < upperRight.x(); j++) {
                Vector2d vec = new Vector2d(j, i);
                MapSquare square = map.elements.get(vec);
                if (square == null) {
                    System.out.println("square null");
                } else {
                    builder.append(CELL_SEGMENT);
                    if (!square.getObjects().isEmpty()) {
                        builder.append(square.getObjects().size());
                    } else if (square.didGrassGrow()) {
                        builder.append(GRASS_CELL);
                    } else if (map.getPreferred().contains(vec)) {
                        builder.append(PREFERRED_CELL);
                    } else {
                        builder.append(EMPTY_CELL);
                    }
                }
            }
            builder.append(CELL_SEGMENT);
            builder.append(System.lineSeparator());
        }

        //-------------------
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //--------------------

        return builder.toString();
    }
}
