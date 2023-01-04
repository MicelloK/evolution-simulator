package agh.oop.proj;

import java.util.List;

public class Statistics {
    private final AbstractWorldMap map;
    private int numberAnimals;
    private int numberGrass;
    private int numberDeadAnimals;
    private int worldDays;
    private double avgLife;
    private double avgEnergy;
    private int freePositionQuantity;
    private double avgChildren;
    private int dominantGenotype;
    private final SimulationEngine engine;

    public Statistics(SimulationEngine engine) {
        this.engine = engine;
        map = engine.getSettings().getMap();
        numberAnimals = 0;
        numberGrass = 0;
        numberDeadAnimals = 0;
        worldDays = 0;
        avgLife = 0;
        avgEnergy = 0;
        freePositionQuantity = 0;
        avgChildren = 0;
        dominantGenotype = 0;
        freePositionQuantity = engine.getFreePositionQuantity();
    }

    public void updateStats() {
        AbstractWorldMap map = engine.getSettings().getMap();
        numberAnimals = map.getAnimalsNumber();
        numberGrass = map.getGrassNumber();
        numberDeadAnimals = map.getAnimalsDead();
        worldDays = engine.getCurrentDay();
        freePositionQuantity = engine.getFreePositionQuantity();
        calculateAvgLifeLength();
        calculateAvgEnergy();
        calculateAvgChildren();
        findDominant();
    }

    public void findDominant() {
        List<Animal> animals = engine.getSettings().getMap().animalsList;
        int[] counter = new int[8];
        for (Animal animal : animals) {
            counter[animal.getGenotype()[animal.getActiveGenome()]] += 1;
        }
        int dominant = 0;
        int maxGenotype = 0;
        for (int i = 0; i < 8; i++) {
            if (counter[i] > maxGenotype) {
                maxGenotype = counter[i];
                dominant = i;
            }
        }
        this.dominantGenotype = dominant;
    }

    public void calculateAvgLifeLength() {
        if (map.getAnimalsDead() != 0) {
            avgLife = Math.round(map.getLifeOfDeadAnimal() / (double) map.getAnimalsDead() * 100) / 100.0;
        }
    }


    public void calculateAvgEnergy() {
        if (engine.getSettings().getMap().animalsList.size() != 0) {
            int energy = 0;
            for (Animal animal : engine.getSettings().getMap().animalsList) {
                if (animal.getEnergy() > 0) {
                    energy += animal.getEnergy();
                }
            }

            avgEnergy = Math.round(energy / (double) numberAnimals * 100) / 100.0;
        }
    }

    public void calculateAvgChildren() {
        int numberOfChildren = 0;
        List<Animal> animals = engine.getSettings().getMap().animalsList;
        if (animals.size() == 0) {
            this.avgChildren = 0;
        } else {
            for (Animal animal : animals) {
                if (animal.getChildren() > 0) {
                    numberOfChildren = numberOfChildren + animal.getChildren();
                }
            }
            avgChildren = Math.round(numberOfChildren / (double) animals.size() * 100) / 100.0;
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

    public int getFreePositionQuantity() {
        return freePositionQuantity;
    }

    public double getAvgChildren() {
        return avgChildren;
    }

    public int getDominantGenotype() {
        return dominantGenotype;
    }
}
