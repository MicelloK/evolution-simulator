package agh.oop.proj.gui;

import agh.oop.proj.SimulationEngine;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StageWithCharts {
    private final BorderPane borderPane;
    private final Stage stage;
    private final SimulationEngine engine;
    private final ChartsStatistic animalChart = new ChartsStatistic("Animals");
    private final ChartsStatistic grassChart = new ChartsStatistic("Grass");
    private final ChartsStatistic energyChart = new ChartsStatistic("Average Energy");
    private final ChartsStatistic avgLifeLength = new ChartsStatistic("Average life length");
    private final ChartsStatistic kidsChart = new ChartsStatistic("Average Children");
    private final ChartsStatistic freePositionChart = new ChartsStatistic("Free Positions");
    private final ChartsStatistic deathAnimalChart = new ChartsStatistic("Death Animal");

    public StageWithCharts(Stage mainStage, SimulationEngine engine) {
        this.engine = engine;
        this.borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 800, 800);
        this.stage = new Stage();
        stage.setScene(scene);
        stage.initOwner(mainStage);

        stage.setOnCloseRequest(event -> this.stage.hide());

        Button exitButton = new Button("EXIT");
        exitButton.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        exitButton.setAlignment(Pos.CENTER);


        exitButton.setOnAction(action -> this.stage.hide());

        Label title = new Label("Map Information on Charts!");
        title.setStyle("-fx-font-weight: bold");
        title.setFont(new Font(15));
        HBox mainDescription = new HBox(10, title, exitButton);
        mainDescription.setAlignment(Pos.CENTER);
        borderPane.setBackground(new Background(new BackgroundFill(Color.PALETURQUOISE, CornerRadii.EMPTY, Insets.EMPTY)));
        BorderPane.setMargin(mainDescription, new Insets(20, 0, 20, 0));
        borderPane.setTop(mainDescription);
        BorderPane.setAlignment(mainDescription, Pos.CENTER);
    }

    public void updateCharts() {
        borderPane.setCenter(null);
        grassChart.updateGrassChart(engine.getStats());
        animalChart.updateAnimalsChart(engine.getStats());
        energyChart.updateAvgEnergy(engine.getStats());
        avgLifeLength.updateAvgLifeLength(engine.getStats());
        deathAnimalChart.updateDeathAnimals(engine.getStats());
        freePositionChart.updateFreePosition(engine.getStats());
        kidsChart.updateAvgChildren(engine.getStats());
        VBox chartsAfter = new VBox(animalChart.getChart(), grassChart.getChart(), energyChart.getChart());
        VBox chartsVol = new VBox(avgLifeLength.getChart(), deathAnimalChart.getChart());
        VBox chartsEnd = new VBox(freePositionChart.getChart(), kidsChart.getChart());
        HBox charts = new HBox(2, chartsVol, chartsAfter, chartsEnd);
        borderPane.setCenter(charts);
    }

    public void chartsShow() {
        stage.show();
    }
}
