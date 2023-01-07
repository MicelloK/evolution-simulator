package agh.oop.proj.gui;

import agh.oop.proj.Animal;
import agh.oop.proj.IMapElement;
import agh.oop.proj.SimulationEngine;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static java.lang.Math.min;

public class ElementBox {
    private final IMapElement element;
    private final SimulationEngine engine;

    private final Stage stage;

    public ElementBox(IMapElement element, SimulationEngine engine, Stage stage) {
        this.element = element;
        this.engine = engine;
        this.stage = stage;
    }

    public void createElement(StackPane till)
    {

        if(element.isAnimal()) {
            setButtonOnAction(till, (Animal) element, engine);
        }
    }


    public ProgressBar energyInAnimal() {
        ProgressBar energyBar = null;
        if (element.isAnimal()) {
            Animal animal = (Animal) element;
            int energy = animal.getEnergy();
            energyBar = new ProgressBar(min(1.00,(double) energy / (double) engine.getSettings().getAnimalFullEnergy()));
            if (((double) energy / (double) engine.getSettings().getAnimalFullEnergy()) * 100.00 >= 100) {
                energyBar.setStyle("-fx-accent: #410000;-fx-background-insets: 0 0 0 0;-fx-padding: 0em;");
            } else if (((double) energy / (double) engine.getSettings().getAnimalFullEnergy()) * 100.00 >= 80.00) {
                energyBar.setStyle("-fx-accent: #7a0000;-fx-background-insets: 0 0 0 0;-fx-padding: 0em;");
            } else if (((double) energy / (double) engine.getSettings().getAnimalFullEnergy()) * 100.00  >= 60.00) {
                energyBar.setStyle("-fx-accent: #b30000;-fx-background-insets: 0 0 0 0;-fx-padding: 0em;");
            } else if (((double) energy / (double) engine.getSettings().getAnimalFullEnergy()) * 100.00  >= 40.00) {
                energyBar.setStyle("-fx-accent: #ec0000;-fx-background-insets: 0 0 0 0;-fx-padding: 0em;");
            } else if (((double) energy / (double) engine.getSettings().getAnimalFullEnergy()) * 100.00 >= 20.00) {
                energyBar.setStyle("-fx-accent: rgb(255,0,0);-fx-background-insets: 0 0 0 0;-fx-padding: 0em;");
            } else if (((double) energy / (double) engine.getSettings().getAnimalFullEnergy()) * 100.00 >= 5.00) {
                energyBar.setStyle("-fx-accent: #ff0000;-fx-background-insets: 0 0 0 0;-fx-padding: 0em;");
            } else {
                energyBar.setStyle("-fx-accent: #ffffff;-fx-background-insets:0 0 0 0;-fx-padding: 0em;");
            }
            energyBar.setBorder(Border.EMPTY);

        }
        return energyBar;
    }


    private void setButtonOnAction(StackPane till, Animal animal,SimulationEngine engine)
    {

            till.setOnMouseClicked(event -> {
                if (!engine.isActive()) {
                    new ElementInformation(stage, animal);
                }
            });
    }
}
