package agh.oop.proj;

import java.util.*;

abstract public class AbstractWorldMap implements IWorldMap, IElementChangeObserver {
    protected final HashMap<Vector2d, MapSquare> elements;
    private int animalsNumber;
    private int grassNumber;
    private final int mapSize;
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;
    private final IMoveAllowed movementDetails;
    private final int reproductionEnergy;
    protected final List<Vector2d> preferredPositions = new LinkedList<>();
    protected List<Vector2d> emptyPreferred;
    protected List<Vector2d> emptyNotPreferred;

    protected ArrayList<IMapElement> animalsList = new ArrayList<>();

    protected ArrayList<IMapElement> grassList = new ArrayList<>();

    protected AbstractWorldMap(int width, int height, IMoveAllowed movementDetails, int reproductionEnergy) {
        elements = new HashMap<>();
        this.movementDetails = movementDetails;
        this.reproductionEnergy = reproductionEnergy;
        mapSize = width * height;
        lowerLeft = new Vector2d(0, 0);
        upperRight = new Vector2d(width, height);
        animalsNumber = 0;
        grassNumber = 0;

        initMap(width, height);
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

    @Override
    public boolean positionChanged(Vector2d oldPosition, Vector2d newPosition, IMapElement object) {
        if (canMoveTo(newPosition)) {
            elements.get(oldPosition).removeObject(object);
            elements.get(newPosition).placeObject(object);
            return true;
        }
        return false;
    }

    public List<Vector2d> getPreferred() {
        return preferredPositions.subList(0, (int) Math.round(0.2 * mapSize));
    }

    public List<Vector2d> getNotPreferred() {
        return preferredPositions.subList((int) Math.round(0.2 * mapSize), preferredPositions.size());
    }

    protected boolean isEmptySquares() {
        return !emptyPreferred.isEmpty() || !emptyNotPreferred.isEmpty();
    }

    protected Vector2d drawPosition() {
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

    @Override
    public boolean animalDie(IMapElement animal) {
        Vector2d position = animal.getPosition();
        if (elements.get(position).getObjects().contains(animal)) {
            MapSquare square = elements.get(position);
            square.animalDie(animal);
            animalsList.remove(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return movementDetails.canMoveTo(position, lowerLeft, upperRight);
    }

    public Vector2d newPosition(Vector2d position) {
        return movementDetails.newPosition(position, lowerLeft, upperRight);
    }

    public int moveEnergyLost(Vector2d position) {
        return movementDetails.lotsEnergy(position, lowerLeft, upperRight, reproductionEnergy);
    }

    @Override
    public boolean place(IMapElement object) {
        Vector2d position = object.getPosition();
        if (canMoveTo(position)) {
            elements.get(position).placeObject(object);
            animalsNumber += 1;
            animalsList.add(object);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        MapSquare square = elements.get(position);
        return !square.getObjects().isEmpty();
    }

    @Override
    public List<IMapElement> objectsAt(Vector2d position) {
        MapSquare square = elements.get(position);
        return square.getObjects();
    }

    private void addGrass(Vector2d position) {
        MapSquare square = elements.get(position);
        if (!square.didGrassGrow()) {
            square.growGrass();
            grassNumber += 1;
        }
    }

    public int getAnimalsNumber() {
        return animalsNumber;
    }

    public int getGrassNumber() {
        return grassNumber;
    }

    private void deleteGrass(Vector2d position) {
        elements.get(position).eatGrass();
        grassNumber -= 1;
    }

    private boolean isGrass(Vector2d position) {
        return elements.get(position).didGrassGrow();
    }

    public boolean eatGrass(Vector2d position) {
        if (isGrass(position)) {
            deleteGrass(position);
            return true;
        }
        return false;
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
