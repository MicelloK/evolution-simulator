package agh.oop.proj;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Animal implements IMapElement {

    private final List<IElementChangeObserver> observers= new LinkedList<>();
    private final AbstractWorldMap map;
    private int energy;
    private int life = 0;
    private int children = 0;
    private Vector2d position;
    private MoveDirection orientation;
    private int howManyGrassEat = 0;
    private int deathDay = 0;
    private final Random random = new Random();

    private final Genom genotyp;

    private final Settings settings;

    // zwierzak powstający bez podania orientacji i bez rozmnarzania
    public Animal(Vector2d position, Settings settings) {
        this.map = settings.getMap();
        this.position = position;
        this.settings = settings;
        map.place(this);
        this.genotyp = new Genom(settings);
        this.orientation = MoveDirection.getRandomOrientation();
        this.energy = settings.getStartAnimalEnergy();
    }

    // rozmnażanie
    public Animal(Animal parentTwo, Animal parentOne, Settings settings) {
        this.settings = settings;
        this.orientation = MoveDirection.getRandomOrientation();
        this.map = settings.getMap();
        this.position = parentOne.getPosition();
        this.genotyp = new Genom(parentOne,parentTwo,settings);
        map.place(this);
        //usunięcie energii rodzicą
        parentOne.loseEnergy(settings.getReproductionEnergy());
        parentTwo.loseEnergy(settings.getReproductionEnergy());
        //dodanie dzieci
        parentTwo.newChildren();
        parentOne.newChildren();
        this.energy = settings.getReproductionEnergy() * 2;
    }

    public void move() {
        this.life++;
        settings.getAnimalMoving().moving(this);
        this.changerposition();
    }

    public void changerposition() {
        int numberdirection = this.getGenotype()[this.getActiveGenom()];
        for(int i = 0;i <= numberdirection;i++){
            this.setOrientation(this.getOrientation().next());
        }
        Vector2d oldPosition = this.getPosition();
        Vector2d newpos = map.newPosition(oldPosition.add(this.getOrientation().toUnitVector()));
        this.loseEnergy(map.moveEnergyLost(newpos));
        this.setPosition(newpos);
    }

    public void loseEnergy(int energy) {
        this.energy -= energy;
    }

    public void increaseEnergy(int eatingGrassEnergy) {
        this.energy += settings.getEatingGrassEnergy();
        this.setHowManyEatGrass();
    }

    public int getEnergy() {
        return this.energy;
    }

    public int getLife() {
        return this.life;
    }

    public int getChildren() {
        return this.children;
    }

    public void newChildren() {
        this.children++;
    }

    public int[] getGenotype(){
        return this.genotyp.getAnimalGenotyp();
    }

    public int getActiveGenom(){
        return this.genotyp.getActiveGenomAnimal();
    }



    public void setActiveGenom(int cuurgen){
        this.genotyp.setActiveGenom(cuurgen);
    }


    @Override
    public boolean isAnimal() {
        return true;
    }

    public boolean isDead() {
        if (energy <= 0) {
            this.deathDay = this.life;
            for(IElementChangeObserver observer: observers)
                observer.animalDie(this);
            return true;
        }
        return false;
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }


    public void setPosition(Vector2d position) {

        this.positionChanged(this.getPosition(),position);
        this.position = position;

    }

    @Override
    public MoveDirection getOrientation() {
        return this.orientation;
    }

    @Override
    public int getImage() {
        return random.nextInt(5) +1;
    }

    public void setOrientation(MoveDirection orientation) {
        this.orientation = orientation;
    }

    public void addObserver(IElementChangeObserver observer)
    {
        this.observers.add(observer);
    }


    public void positionChanged(Vector2d oldPosition, Vector2d newPosition)
    {
        for(IElementChangeObserver observer: observers)
            observer.positionChanged(oldPosition, newPosition,this);
    }

    public void removeObserver(IElementChangeObserver observer) {
        observers.remove(observer);
    }

    public void setHowManyEatGrass(){
        this.howManyGrassEat+=1;
    }
}