package agh.oop.proj;

import java.util.Random;

public class Animal implements IMapElement {
    private final IElementChangeObserver observer;
    private final AbstractWorldMap map;
    private int energy;
    private int life = 0;
    private int children = 0;
    private Vector2d position;
    private MoveDirection orientation;
    private int howManyGrassEat = 0;
    private final int createdDay;
    private final Random random = new Random();
    private final Genome genotype;
    private final Settings settings;

    public Animal(Vector2d position, Settings settings, int createdDay) {
        this.map = settings.getMap();
        this.position = position;
        this.settings = settings;
        this.createdDay = createdDay;
        this.observer = map;
        this.genotype = new Genome(settings);
        this.orientation = MoveDirection.randomDirection();
        this.energy = settings.getStartAnimalEnergy();
        map.place(this);
    }

    public Animal(Animal parentTwo, Animal parentOne, Settings settings, int createdDay) {
        this.settings = settings;
        this.createdDay = createdDay;
        this.orientation = MoveDirection.randomDirection();
        this.map = settings.getMap();
        this.observer = map;
        this.position = parentOne.getPosition();
        this.genotype = new Genome(parentOne, parentTwo, settings);
        map.place(this);

        parentOne.loseEnergy(settings.getReproductionEnergy());
        parentTwo.loseEnergy(settings.getReproductionEnergy());

        parentTwo.newChildren();
        parentOne.newChildren();
        this.energy = settings.getReproductionEnergy() * 2;
    }

    public void move() {
        this.life++;
        settings.getAnimalMoving().moving(this);

        if (isDead()) {
            observer.animalDies(this);
        }
    }

    public void changerPosition() {
        int numberDirection = this.getGenotype()[this.getActiveGenome()];
        for (int i = 0; i <= numberDirection; i++) {
            this.setOrientation(this.getOrientation().next());
        }
        Vector2d oldPosition = this.getPosition();
        Vector2d newPosition = map.newPosition(oldPosition.add(this.getOrientation().toUnitVector()));
        this.loseEnergy(map.moveEnergyLost(newPosition));
        this.setPosition(newPosition);
    }

    public void loseEnergy(int energy) {
        this.energy -= energy;
    }

    public void increaseEnergy() {
        this.energy += settings.getEatingGrassEnergy();
        this.increaseHowManyEatGrass();
    }

    public int getEnergy() {
        return this.energy;
    }

    public int getLife() {
        return this.life;
    }

    public int getDeathDay() {
        if (isDead()) {
            return createdDay + life;
        }
        return -1;
    }

    public int getChildren() {
        return this.children;
    }

    public void newChildren() {
        this.children++;
    }

    public int[] getGenotype() {
        return this.genotype.getAnimalGenotype();
    }

    public int getActiveGenome() {
        return this.genotype.getActiveGenomeAnimal();
    }


    public void setActiveGenome(int currGen) {
        this.genotype.setActiveGenome(currGen);
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
        return this.position;
    }

    public void setPosition(Vector2d position) {
        this.positionChanged(this.getPosition(), position);
        this.position = position;
    }

    @Override
    public MoveDirection getOrientation() {
        return this.orientation;
    }

    public void setOrientation(MoveDirection orientation) {
        this.orientation = orientation;
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        observer.positionChanged(oldPosition, newPosition, this);
    }

    public void increaseHowManyEatGrass() {
        this.howManyGrassEat += 1;
    }

    public int getHowManyGrassEat() {
        return howManyGrassEat;
    }
}
