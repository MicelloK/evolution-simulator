package agh.oop.proj.gui;

import agh.oop.proj.*;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ElementInformation implements IElementChangeObserver {
    private final Stage stage;
    private final BorderPane borderPane;
    private final VBox needDate;

    private Animal animal;

    public ElementInformation(Stage mainStage,Animal animal) {
        this.animal = animal;
        this.stage = new Stage();
        this.borderPane = new BorderPane();
        Scene sceneMain = new Scene(borderPane, 500, 300);
        stage.setScene(sceneMain);
        stage.initOwner(mainStage);
        this.needDate = infoDate();
        animal.addObserver(this);
        creativeInfo();
        this.stage.show();

    }

    private VBox infoDate() {
        VBox listOfLabels = new VBox();
        listOfLabels.setSpacing(10);
        Label activeGenomeLabel = new Label("Active Genome:");
        Label genomeLabel = new Label("Genome:");
        Label energyLabel = new Label("Energy:");
        Label grassesEatenLabel = new Label("Grasses Eaten:");
        Label childrenLabel = new Label("Children:");
        Label liveLabel = new Label("Days Lived:");
        Label deadLabel = new Label("Day of Death:");
        activeGenomeLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: transparent;");
        genomeLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: transparent;");
        energyLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: transparent;");
        grassesEatenLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: transparent;");
        childrenLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: transparent;");
        liveLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: transparent;");
        deadLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: transparent;");
        listOfLabels.getChildren().addAll(activeGenomeLabel, genomeLabel, energyLabel, grassesEatenLabel, childrenLabel, liveLabel, deadLabel);
        return listOfLabels;
    }

    public void creativeInfo(){
        new Thread(()-> {
            Label tittle = new Label("Information about animal!");
            tittle.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 22pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
            borderPane.setTop(tittle);
            BorderPane.setAlignment(tittle, Pos.CENTER);
            borderPane.setBackground(new Background(new BackgroundFill(Color.PALETURQUOISE, CornerRadii.EMPTY, Insets.EMPTY)));
            BorderPane.setMargin(tittle, new Insets(20, 0, 20, 0));
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
            Label activeGenome = new Label(Integer.toString(animal.getActiveGenome()));
            Label genome = new Label(genomeString.toString());
            Label energy = new Label(Integer.toString(animal.getEnergy()));
            Label grassesEaten = new Label(Integer.toString(animal.getHowManyGrassEat()));
            Label children = new Label(Integer.toString(animal.getChildren()));
            Label live = new Label(Integer.toString(animal.getLife()));
            Label dead = new Label(Integer.toString(animal.getDeathDay()));
            listOfInformation.getChildren().addAll(activeGenome, genome, energy, grassesEaten, children, live, dead);
            activeGenome.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
            genome.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
            energy.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
            grassesEaten.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
            children.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
            live.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
            dead.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");

            HBox inputList = new HBox();
            inputList.getChildren().addAll(needDate, listOfInformation);
            inputList.setSpacing(20);
            inputList.setAlignment(Pos.CENTER);
            borderPane.setCenter(inputList);

            Button exitButton = new Button("EXIT");

            exitButton.setOnAction(actoion -> {
                animal.removeObserver(this);
                this.stage.close();
            });

            exitButton.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
            exitButton.setAlignment(Pos.BOTTOM_CENTER);
            borderPane.setBottom(exitButton);
        }).start();
    }

    @Override
    public boolean positionChanged(Vector2d oldPosition, Vector2d newPosition, IMapElement object) {
        borderPane.setCenter(null);
        borderPane.setRight(null);
        borderPane.setLeft(null);
        Platform.runLater(this::creativeInfo
        );
        return true;
    }

    @Override
    public boolean animalDies(IMapElement animal) {
        return false;
    }
}