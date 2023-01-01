package agh.oop.proj;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class Statistic {
    private final VBox statisticLabel;
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
    private SimulationEngine engine = null;

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
        this.statisticLabel = uploudStatic();
        this.freePosition = engine.getFreePosition();
    }

    public VBox uploudStatic(){
        AbstractWorldMap map = this.engine.getSettings().getMap();
        this.numberAnimals = map.getAnimalsNumber();
        this.numberGrass = map.getGrassNumber();
        this.numberDeadAnimals = map.getAnimalsDead() ;
        this.worldDays = engine.getCurrentDay();
        calculateAvgLifeLength();
        calculateAvgEnergy();
        this.freePosition = engine.getFreePosition();
        calculateAvgChildren();
        findDominant();
        Label title = new Label("STATISTIC MAP");

        Label worldDays = new Label("Number Day: " + this.worldDays);
        worldDays.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");

        Label numberOfAliveAnimals = new Label("Number of Alive Animals"+this.numberAnimals);
        numberOfAliveAnimals.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");

        Label numberOfGrass = new Label("Number of grass: " + this.numberGrass);
        numberOfGrass.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");

        Label numberOfDeadAnimals = new Label("Number of Dead Animals " + this.numberDeadAnimals);
        numberOfDeadAnimals.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");

        Label avgEnergy = new Label("Average of energy: " + this.avgEnergy);
        avgEnergy.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");

        Label avgLifeDaysDeadAnimal = new Label("Average of life: " + this.avgLife);
        avgLifeDaysDeadAnimal.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");

        Label avgChildren = new Label("Average of Children: " + this.avgChildren);
        avgChildren.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");

        Label dominantGenotype = new Label("Dominant Genotype: " + this.dominantGenotype);
        dominantGenotype.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");

        VBox statsistic = new VBox(15);
        statsistic.getChildren().addAll(title,worldDays, numberOfAliveAnimals, numberOfGrass, numberOfDeadAnimals, avgEnergy, avgLifeDaysDeadAnimal, avgChildren, dominantGenotype);
        statsistic.setAlignment(Pos.TOP_CENTER);
        title.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);-fx-font-weight: bold;");
        title.setFont(new Font(15));
        statsistic.setStyle(String.valueOf(new Insets(0,1,1,50)));
        return statsistic;
    };

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
