package agh.oop.proj;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Animal implements IMapElement {

    private final List<IPositionChangeObserver> observers= new LinkedList<>();

    private MoveDirection[] orientations;
    private final IWorldMap map;
    private int energy;
    private int life = 0;
    private int children = 0;
    private Vector2d position;
    private MoveDirection orientation;
    private int howManyGrassEat = 0;
    private int deathDay = 0;
    private final Random random = new Random();

    private final Genom genotyp;

    // zwierzak powstający bez podania orientacji i bez rozmnarzania
    public Animal(IWorldMap map, Vector2d position, int startEnergy, int lengthGenom, IGenom mutation, IMove movingAnimal) {
        this.map = map;
        this.position = position;
        map.place(this);
        this.genotyp = new Genom(lengthGenom,map);
        this.orientation = orientations[random.nextInt(orientations.length-1)];
    }

    // rozmnażanie
    public Animal(IWorldMap map, Animal parentTwo, Animal parentOne, IGenom mutation, int bornEenergy, IMove movingAnimal) {
        this.orientation = orientations[random.nextInt(orientations.length - 1)];
        this.map = map;
        this.position = parentOne.getPosition();
        this.genotyp = new Genom(parentOne,parentTwo,bornEenergy,map);
        map.place(this);
        //usunięcie energii rodzicą
        parentOne.loseEnergy(bornEenergy);
        parentTwo.loseEnergy(bornEenergy);
        //dodanie dzieci
        parentTwo.newChildren();
        parentOne.newChildren();
        this.energy = bornEenergy * 2;
    }

    public void move(MoveDirection direction) {
        this.life++;
        map.moving(this);
    }

    public void loseEnergy(int energy) {
        this.energy -= energy;
    }

    public void increaseEnergy(int energyGrass) {
        this.energy += energyGrass;
        this.howManyGrassEat++;
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

    public boolean isDead(int day) {
        if (energy <= 0) {
            deathDay = day;
            for(IPositionChangeObserver observer: observers)
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
        this.position = position;
    }

    @Override
    public MoveDirection getOrientation() {
        return this.orientation;
    }

    public void setOrientation(MoveDirection orientation) {
        this.orientation = orientation;
    }

    public void addObserver(IPositionChangeObserver observer)
    {
        this.observers.add(observer);
    }


    public void positionChanged(Vector2d oldPosition, Vector2d newPosition)
    {
        for(IPositionChangeObserver observer: observers)
            observer.positionChanged(oldPosition, newPosition,this);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    public void setHowManyEatGrass(){
        this.howManyGrassEat+=1;
    }
}