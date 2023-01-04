package agh.oop.proj;

import java.util.List;

public class Statistic {
    private final AbstractWorldMap map;
    private int numberAnimals;
    private int numberGrass;
    private int numberDeadAnimals;
    private int worldDays;
    private double avgLife;
    private double avgEnergy;
    private int freePosition;
    private double avgChildren;
    private int dominantGenotype;
    private final SimulationEngine engine;

    public Statistic(SimulationEngine engine) {
        this.map = engine.getSettings().getMap();
        this.numberAnimals = 0;
        this.numberGrass = 0;
        this.numberDeadAnimals = 0;
        this.worldDays = 0;
        this.avgLife = 0;
        this.avgEnergy = 0;
        this.freePosition = 0;
        this.avgChildren = 0;
        this.dominantGenotype = 0;
        this.engine = engine;
        this.freePosition = engine.getFreePosition();
    }

    public void updateStats() {
        AbstractWorldMap map = engine.getSettings().getMap();
        numberAnimals = map.getAnimalsNumber();
        numberGrass = map.getGrassNumber();
        numberDeadAnimals = map.getAnimalsDead() ;
        worldDays = engine.getCurrentDay();
        freePosition = engine.getFreePosition();
        calculateAvgLifeLength();
        calculateAvgEnergy();
        calculateAvgChildren();
        findDominant();
    }

    public void findDominant(){
        List<Animal> animals = engine.getSettings().getMap().animalsList;
        int[] counter = new int[8];
        for(Animal animal : animals){
            counter[animal.getGenotype()[animal.getActiveGenome()]] += 1;
        }
        int dominant = 0;
        int maxGenotype =0;
        for(int i = 0; i < 8; i++ ){
            if(counter[i] > maxGenotype){
                maxGenotype = counter[i];
                dominant = i;
            }
        }
        this.dominantGenotype = dominant;
    }

    public void calculateAvgLifeLength(){
        if(map.getAnimalsDead() != 0) {
            this.avgLife =  map.getLifeOfDeadAniaml() / map.getAnimalsDead();
        }
    }


    public void calculateAvgEnergy(){
        if(engine.getSettings().getMap().animalsList.size() != 0){
            int energy = 0;
            for(Animal animal : engine.getSettings().getMap().animalsList) {
                if (animal.getEnergy() > 0) {
                    energy += animal.getEnergy();
                }
            }

            this.avgEnergy = energy / this.numberAnimals;
        }
    }

    public void calculateAvgChildren(){
        int numberOfChildren = 0;
        List<Animal> animals = engine.getSettings().getMap().animalsList;
        if(animals.size() == 0){
            this.avgChildren = 0;
        }
        else{
            for(Animal animal : animals) {
                if (animal.getChildren() > 0) {
                    numberOfChildren = numberOfChildren+animal.getChildren();
                }
            }
            this.avgChildren =  numberOfChildren / animals.size();
        }
    }

    public int getNumberAnimals() {
        return numberAnimals;
    }

    public int getNumberGrass() {
        return numberGrass;
    }

    public int getNumberDeadAnimals() {
        return numberDeadAnimals;
    }

    public int getWorldDays() {
        return worldDays;
    }

    public double getAvgLife() {
        return avgLife;
    }

    public double getAvgEnergy() {
        return avgEnergy;
    }

    public int getFreePosition() {
        return freePosition;
    }

    public double getAvgChildren() {
        return avgChildren;
    }

    public int getDominantGenotype() {
        return dominantGenotype;
    }
}
