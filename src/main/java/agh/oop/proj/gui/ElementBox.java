package agh.oop.proj.gui;

import agh.oop.proj.Animal;
import agh.oop.proj.IMapElement;
import agh.oop.proj.SimulationEngine;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static java.lang.Math.min;

public class ElementBox {
    private final IMapElement element;
    private final SimulationEngine engine;

    public ElementBox(IMapElement element, SimulationEngine engine) {
        this.element = element;
        this.engine = engine;
    }

    public void createElement(ImageView imageView)
    {

        Hyperlink hyperLink = new Hyperlink();
        hyperLink.setGraphic(imageView);

        if(element.isAnimal()) {
            setButtonOnAction(hyperLink, (Animal) element, engine);
        }
    }


    public ProgressBar energyInAnimal() {
        ProgressBar energyBar = null;
        if (element.isAnimal()) {
            Animal animal = (Animal) element;
            int energy = animal.getEnergy();
            energyBar = new ProgressBar((double) energy / (double) engine.getSettings().getAnimalFullEnergy());
            if (((double) energy / (double) engine.getSettings().getAnimalFullEnergy()) * 100.00 >= 100) {
                energyBar.setStyle("-fx-accent: #410000;-fx-background-insets: 1 1 2 1;-fx-padding: 0.40em;");
            } else if (((double) energy / (double) engine.getSettings().getAnimalFullEnergy()) * 100.00 >= 80.00) {
                energyBar.setStyle("-fx-accent: #7a0000;-fx-background-insets: 1 1 2 1;-fx-padding: 0.40em;");
            } else if (((double) energy / (double) engine.getSettings().getAnimalFullEnergy()) * 100.00  >= 60.00) {
                energyBar.setStyle("-fx-accent: #b30000;-fx-background-insets: 1 1 2 1;-fx-padding: 0.40em;");
            } else if (((double) energy / (double) engine.getSettings().getAnimalFullEnergy()) * 100.00  >= 40.00) {
                energyBar.setStyle("-fx-accent: #ec0000;-fx-background-insets: 1 1 2 1;-fx-padding: 0.40em;");
            } else if (((double) energy / (double) engine.getSettings().getAnimalFullEnergy()) * 100.00 >= 20.00) {
                energyBar.setStyle("-fx-accent: rgb(255,0,0);-fx-background-insets: 1 1 2 1;-fx-padding: 0.40em;");
            } else if (((double) energy / (double) engine.getSettings().getAnimalFullEnergy()) * 100.00 >= 5.00) {
                energyBar.setStyle("-fx-accent: #ff0000;-fx-background-insets: 1 1 2 1;-fx-padding: 0.40em;");
            } else {
                energyBar.setStyle("-fx-accent: #ffffff;-fx-background-insets: 1 1 2 1;-fx-padding: 0.40em;");
            }

        }
        return energyBar;
    }


    private static void setButtonOnAction(Hyperlink hyperLink, Animal animal,SimulationEngine engine)
    {
        hyperLink.setOnAction(event -> {
            if (!engine.isActive())
            {
                Button genotypeButton = new Button("Show Information");
                Button trackButton = new Button("Track");

                HBox.setMargin(genotypeButton, new Insets(20));

                HBox.setMargin(trackButton, new Insets(20));

                HBox hBox = new HBox(genotypeButton, trackButton);

                Scene scene = new Scene(hBox, 230, 100);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                StringBuilder sb = new StringBuilder();
                sb.append("[");
                for (int i = 0; i < animal.getGenotype().length; i++) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(animal.getGenotype()[i]);
                }
                sb.append("]");

                VBox listOfInformation = new VBox();
                listOfInformation.setSpacing(10);
                Label activeGenom = new Label(Integer.toString(animal.getActiveGenome()));
                Label genom = new Label(sb.toString());
                Label energy = new Label(Integer.toString(animal.getEnergy()));
                Label grassesEaten = new Label(Integer.toString(animal.getHowManyGrassEat()));
                Label children = new Label(Integer.toString(animal.getChildren()));
                Label live = new Label(Integer.toString(engine.getCurrentDay()-animal.getLife()));
                Label dead = new Label(Integer.toString(animal.getDeathDay()));
                listOfInformation.getChildren().addAll(activeGenom, genom, energy, grassesEaten, children, live, dead);
                listOfInformation.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
                VBox listOfEtykiet = new VBox();
                listOfEtykiet.setSpacing(10);
                Label activeGenomLabel = new Label("Active Genome:");
                Label genomLabel = new Label("Genome:");;
                Label energyLabel = new Label("Energy:");
                Label grassesEatenLabel = new Label("Grasses Eaten:");
                Label childrenLabel = new Label("Children:");
                Label liveLabel = new Label("Days Lived:");
                Label deadLabel = new Label("Day of Death:");
                listOfEtykiet.getChildren().addAll(activeGenomLabel, genomLabel, energyLabel, grassesEatenLabel, childrenLabel, liveLabel, deadLabel);
                listOfEtykiet.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");

                HBox inputList = new HBox();
                inputList.getChildren().addAll(listOfEtykiet,listOfInformation);

                genotypeButton.setOnAction(e -> {
                    stage.hide();
                    Stage genotypeStage = new Stage();
                    genotypeStage.setScene(new Scene(inputList));
                    genotypeStage.show();
                });

                trackButton.setOnAction(e -> {
                    engine.changeStatus();
                    stage.hide();
                });
            }
        });
    }
}
