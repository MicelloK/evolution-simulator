package agh.oop.proj;

import java.util.*;

abstract public class AbstractWorldMap implements IWorldMap, IElementChangeObserver {
    protected final Map<Vector2d, MapSquare> elements;
    private int animalsNumber;
    private int grassNumber;
    private int animalsDead = 0;
    private int lifeOfDeadAnimal = 0;
    protected final int mapSize;
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;
    private final IMoveAllowed movementDetails;
    private final int reproductionEnergy;
    protected final List<Vector2d> preferredPositions = new ArrayList<>();
    protected List<Vector2d> emptyPreferred;
    protected List<Vector2d> emptyNotPreferred;
    protected final ArrayList<Animal> animalsList = new ArrayList<>();

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
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, IMapElement object) {
        if (elements.containsKey(oldPosition) && elements.containsKey(newPosition)) {
            elements.get(oldPosition).removeObject(object);
            elements.get(newPosition).placeObject(object);
        }
    }

    abstract void updatePreferredPositions();

    public List<Vector2d> getPreferred() {
        return new LinkedList<>(preferredPositions.subList(0, (int) Math.round(0.2 * mapSize)));
    }

    protected List<Vector2d> getNotPreferred() {
        return new LinkedList<>(preferredPositions.subList((int) Math.round(0.2 * mapSize), preferredPositions.size()));
    }

    protected boolean isEmptySquares() {
        return !emptyPreferred.isEmpty() || !emptyNotPreferred.isEmpty();
    }

    protected Vector2d drawPosition() {
        Random random = new Random();
        Vector2d position;

        if (emptyPreferred.isEmpty()) {
            position = emptyNotPreferred.get(random.nextInt(emptyNotPreferred.size()));
            emptyNotPreferred.remove(position);
            return position;
        }
        if (emptyNotPreferred.isEmpty()) {
            position = emptyPreferred.get(random.nextInt(emptyPreferred.size()));
            emptyPreferred.remove(position);
            return position;
        }

        int preference = random.nextInt(100);
        if (preference >= 20) {
            position = emptyPreferred.get(random.nextInt(emptyPreferred.size()));
            emptyPreferred.remove(position);
        } else {
            position = emptyNotPreferred.get(random.nextInt(emptyNotPreferred.size()));
            emptyNotPreferred.remove(position);
        }
        return position;
    }

    @Override
    public void animalDies(IMapElement animal) {
        Vector2d position = animal.getPosition();
        if (elements.get(position).getObjects().contains(animal)) {
            MapSquare square = elements.get(position);
            square.animalDie(animal);
            animalsList.remove((Animal) animal);
            animalsNumber -= 1;
            this.setAnimalsDead();
            this.setLifeOfDeadAnimal((Animal) animal);
        }
    }

    public boolean inMap(Vector2d position) {
        return position.precedes(upperRight) && position.follows(lowerLeft);
    }

    public Vector2d newPosition(Vector2d oldPosition, Vector2d newPosition) {
        return movementDetails.newPosition(oldPosition, newPosition, lowerLeft, upperRight);
    }

    public int moveEnergyLost(Vector2d position) {
        return movementDetails.lotsEnergy(position, lowerLeft, upperRight, reproductionEnergy);
    }

    @Override
    public void place(IMapElement object) {
        Vector2d position = object.getPosition();
        if (inMap(position)) {
            elements.get(position).placeObject(object);
            animalsNumber += 1;
            animalsList.add((Animal) object);
            object.setObserver(this);
        }
    }

    private void addGrass(Vector2d position) {
        MapSquare square = elements.get(position);
        square.growGrass();
        grassNumber += 1;
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

        if (getPreferred().contains(position)) {
            emptyPreferred.add(position);
        } else {
            emptyNotPreferred.add(position);
        }
    }

    private boolean isGrass(Vector2d position) {
        return elements.get(position).didGrassGrow();
    }

    public void eatGrass(Vector2d position) {
        if (isGrass(position)) {
            deleteGrass(position);
        }
    }

    public void growGrass(int grassPerDay) {
        for (int i = 0; i < grassPerDay; i++) {
            if (isEmptySquares()) {
                Vector2d position = drawPosition();
                addGrass(position);
            }
        }
    }

    public Map<Vector2d, MapSquare> getElements() {
        return elements;
    }

    public String toString() {
        return new MapVisualiser(this).draw(lowerLeft, upperRight);
    }

    public int getAnimalsDead() {
        return animalsDead;
    }

    private void setAnimalsDead() {
        this.animalsDead = this.animalsDead + 1;
    }

    public int getLifeOfDeadAnimal() {
        return lifeOfDeadAnimal;
    }

    public void setLifeOfDeadAnimal(Animal animal) {
        this.lifeOfDeadAnimal = this.lifeOfDeadAnimal + animal.getLifeLength();
    }
}
