package agh.oop.proj;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Animal implements IMapElement {
    private final List<IElementChangeObserver> observer;
    private final AbstractWorldMap map;
    private int energy;
    private int life = 0;
    private int children = 0;
    private Vector2d position;
    private MoveDirection orientation;
    private int howManyGrassEat = 0;
    private final int createdDay;
    private final Genome genotype;
    private final Settings settings;

    private final Random random = new Random();

    private final int image;


    public Animal(Vector2d position, Settings settings, int createdDay) {
        this.image = random.nextInt(5)+1;
        this.map = settings.getMap();
        this.position = position;
        this.settings = settings;
        this.createdDay = createdDay;
        this.observer = new LinkedList<>();
        this.genotype = new Genome(settings);
        this.orientation = MoveDirection.randomDirection();
        this.energy = settings.getStartAnimalEnergy();
        map.place(this);
    }

    public Animal(Animal parentTwo, Animal parentOne, Settings settings, int createdDay) {
        this.image = parentOne.getImage();
        this.settings = settings;
        this.createdDay = createdDay;
        this.orientation = MoveDirection.randomDirection();
        this.map = settings.getMap();
        this.observer = new LinkedList<>();
        this.position = parentOne.getPosition();
        this.genotype = new Genome(parentOne, parentTwo, settings);
        map.place(this);

        parentOne.loseEnergy(settings.getReproductionEnergy());
        parentTwo.loseEnergy(settings.getReproductionEnergy());

        this.energy = settings.getReproductionEnergy() * 2;
    }

    public void move() {
        this.life++;
        settings.getAnimalMoving().moving(this);

        if (isDead()) {
            for(IElementChangeObserver observers: observer) {
                observers.animalDies(this);
            }
        }
    }

    public void changerPosition() {
        int numberDirection = this.getGenotype()[this.getActiveGenome()];
        for (int i = 0; i <= numberDirection; i++) {
            orientation = orientation.next();
        }
        Vector2d oldPosition = this.position;
        Vector2d newPosition = map.newPosition(oldPosition, oldPosition.add(getOrientation().toUnitVector()));
        this.loseEnergy(map.moveEnergyLost(newPosition));
        this.setPosition(newPosition);
    }

    public void loseEnergy(int energy) {
        this.energy -= energy;
    }

    public void increaseEnergy() {
        this.energy += settings.getEatingGrassEnergy();
        howManyGrassEat += 1;
    }

    public int getEnergy() {
        return this.energy;
    }

    public int getLife() {
        return life;
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
        this.children += 1;
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

    public void setPosition(Vector2d newPosition) {
        this.positionChanged(this.getPosition(), newPosition);
        this.position = newPosition;
    }

    @Override
    public MoveDirection getOrientation() {
        return this.orientation;
    }

    @Override
    public int getImage() {
        return image;
    }

    public void setOrientation(MoveDirection orientation) {
        this.orientation = orientation;
    }



    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for(IElementChangeObserver observers : observer) {
            observers.positionChanged(oldPosition, newPosition, this);
        }
    }

    public void addObserver(IElementChangeObserver observer){
        this.observer.add(observer);
    }
    public void removeObserver(IElementChangeObserver observer){
        this.observer.remove(observer);
    }


    public int getHowManyGrassEat() {
        return howManyGrassEat;
    }
}
