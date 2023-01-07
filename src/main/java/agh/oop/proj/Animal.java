package agh.oop.proj;

import java.util.Random;

public class Animal implements IMapElement {
    private IElementChangeObserver observer;
    private final AbstractWorldMap map;
    private Vector2d position;
    private int energy;
    private int children = 0;
    private int howManyGrassEat = 0;
    private final int createdDay;
    private int lifeLength = 0;
    private final int imageIdx;
    private MoveDirection orientation;
    private final Genome genotype;
    private final Settings settings;

    public Animal(Vector2d position, Settings settings, int createdDay) {
        Random random = new Random();
        this.settings = settings;
        this.position = position;
        this.createdDay = createdDay;
        imageIdx = random.nextInt(5) + 1;
        map = settings.getMap();
        genotype = new Genome(settings);
        orientation = MoveDirection.randomDirection();
        energy = settings.getStartAnimalEnergy();
        map.place(this);
    }

    public Animal(Animal parentTwo, Animal parentOne, Settings settings, int createdDay) {
        this.settings = settings;
        this.createdDay = createdDay;
        map = settings.getMap();
        imageIdx = parentOne.getImageIdx();
        orientation = MoveDirection.randomDirection();
        position = parentOne.getPosition();
        genotype = new Genome(parentOne, parentTwo, settings);
        map.place(this);

        parentOne.loseEnergy(settings.getReproductionLostEnergy());
        parentTwo.loseEnergy(settings.getReproductionLostEnergy());

        energy = settings.getReproductionLostEnergy() * 2;
    }

    public void move() {
        lifeLength++;
        settings.getAnimalMoving().moving(this);

        if (isDead()) {
            observer.animalDies(this);
        }
    }

    public void changerPosition() {
        int numberDirection = getGenotype()[getActiveGenome()];
        for (int i = 0; i <= numberDirection; i++) {
            orientation = orientation.next();
        }
        Vector2d oldPosition = position;
        Vector2d newPosition = map.newPosition(oldPosition, oldPosition.add(getOrientation().toUnitVector()));
        loseEnergy(map.moveEnergyLost(newPosition));
        setPosition(newPosition);
    }

    public void loseEnergy(int energy) {
        this.energy -= energy;
    }

    public void increaseEnergy() {
        energy += settings.getEatingGrassEnergy();
        howManyGrassEat += 1;
    }

    public int getEnergy() {
        return energy;
    }

    public int getLifeLength() {
        return lifeLength;
    }

    public int getDeathDay() {
        if (isDead()) {
            return createdDay + lifeLength;
        }
        return 0;
    }

    public int getChildren() {
        return children;
    }

    public void newChildren() {
        children += 1;
    }

    public int[] getGenotype() {
        return genotype.getAnimalGenotype();
    }

    public int getActiveGenome() {
        return genotype.getActiveGenomeAnimal();
    }

    public void setActiveGenome(int currGen) {
        genotype.setActiveGenome(currGen);
    }

    @Override
    public boolean isAnimal() {
        return true;
    }

    public boolean isDead() {
        return energy <= 0;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    public void setPosition(Vector2d newPosition) {
        positionChanged(this.getPosition(), newPosition);
        position = newPosition;
    }

    @Override
    public MoveDirection getOrientation() {
        return orientation;
    }

    @Override
    public int getImageIdx() {
        return imageIdx;
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        observer.positionChanged(oldPosition, newPosition, this);
    }

    public void setObserver(IElementChangeObserver observer) {
        this.observer = observer;
    }

    public int getHowManyGrassEat() {
        return howManyGrassEat;
    }
}
