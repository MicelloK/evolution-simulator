package agh.oop.proj.gui;

import agh.oop.proj.Animal;
import agh.oop.proj.SimulationEngine;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ElementInformation {
    private final Stage stage;
    private final BorderPane borderPane;
    private final Button exitButton = new Button("EXIT");
    private final VBox needDate;


    public ElementInformation() {
        this.stage = new Stage();
        this.borderPane = new BorderPane();
        Scene sceneMain = new Scene(borderPane, 500, 300);
        stage.setScene(sceneMain);
        Label tittle = new Label("Information about animal!");
        tittle.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 22pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        borderPane.setTop(tittle);
        BorderPane.setAlignment(tittle, Pos.CENTER);
        borderPane.setBackground(new Background(new BackgroundFill(Color.PALETURQUOISE, CornerRadii.EMPTY, Insets.EMPTY)));
        BorderPane.setMargin(tittle, new Insets(20, 0, 20, 0));
        this.needDate = infoDate();

    }

    private VBox infoDate(){
        VBox listOfEtykiet = new VBox();
        listOfEtykiet.setSpacing(10);
        Label activeGenomLabel = new Label("Active Genome:");
        Label genomLabel = new Label("Genome:");;
        Label energyLabel = new Label("Energy:");
        Label grassesEatenLabel = new Label("Grasses Eaten:");
        Label childrenLabel = new Label("Children:");
        Label liveLabel = new Label("Days Lived:");
        Label deadLabel = new Label("Day of Death:");
        activeGenomLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: transparent;");
        genomLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: transparent;");
        energyLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: transparent;");
        grassesEatenLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: transparent;");
        childrenLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: transparent;");
        liveLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: transparent;");
        deadLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: transparent;");
        listOfEtykiet.getChildren().addAll(activeGenomLabel, genomLabel, energyLabel, grassesEatenLabel, childrenLabel, liveLabel, deadLabel);
        return listOfEtykiet;
    }

    public void creativeInfo(Animal animal, SimulationEngine engine) {
        borderPane.setCenter(null);
        StringBuilder genomeString = new StringBuilder();
        genomeString.append("[");
        for (int i = 0; i < animal.getGenotype().length; i++) {
            if (i > 0) {
                genomeString.append(", ");
            }
            genomeString.append(animal.getGenotype()[i]);
        }
        genomeString.append("]");

        VBox listOfInformation = new VBox();
        listOfInformation.setSpacing(10);
        Label activeGenom = new Label(Integer.toString(animal.getActiveGenome()));
        Label genom = new Label(genomeString.toString());
        Label energy = new Label(Integer.toString(animal.getEnergy()));
        Label grassesEaten = new Label(Integer.toString(animal.getHowManyGrassEat()));
        Label children = new Label(Integer.toString(animal.getChildren()));
        Label live = new Label(Integer.toString(animal.getLife()));
        Label dead = new Label(Integer.toString(animal.getDeathDay()));
        listOfInformation.getChildren().addAll(activeGenom, genom, energy, grassesEaten, children, live, dead);
        activeGenom.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        genom.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        energy.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        grassesEaten.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        children.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        live.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        dead.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");

        HBox inputList = new HBox();
        inputList.getChildren().addAll(needDate,listOfInformation);
        inputList.setSpacing(20);
        inputList.setAlignment(Pos.CENTER);
        borderPane.setCenter(inputList);
        stage.show();

        exitButton.setOnAction(e -> {

            engine.changeStatus();
            stage.close();

        });
    }
}
