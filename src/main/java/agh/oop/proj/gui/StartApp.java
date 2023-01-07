package agh.oop.proj.gui;

import agh.oop.proj.Controller;
import agh.oop.proj.Settings;
import agh.oop.proj.SimulationEngine;
import agh.oop.proj.Statistic;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StartApp {
    private final Stage stage;
    private final BorderPane borderPane;

    private final Button startButton = new Button("Create Simulation");

    private final Button exitButton = new Button("EXIT");

    private final Button buttonEndTracking = new Button("START/STOP");

    private final SimulationEngine engine;

    private final Thread engineThread;

    private final CreativeMap newMap;

    public StartApp(Settings parameters) throws FileNotFoundException {

        this.stage = new Stage();
        this.borderPane = new BorderPane();
        //ustawienia sceny
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());
        Scene sceneMain = new Scene(borderPane);
        stage.setScene(sceneMain);
        stage.show();
        stage.getIcons().add(new Image(new FileInputStream("src/main/resources/world.jpg")));
        stage.setTitle("About unusual adventures with evolution");

        //ustawienia borderPane i tytułu
        Label tittle = new Label("This is the world that evolving before our eyes! ");
        tittle.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 22pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        borderPane.setTop(tittle);
        BorderPane.setAlignment(tittle, Pos.CENTER);
        borderPane.setBackground(new Background(new BackgroundFill(Color.PALETURQUOISE, CornerRadii.EMPTY, Insets.EMPTY)));
        BorderPane.setMargin(tittle, new Insets(20, 0, 20, 0));

        //rozpoczęcie tytyułu
        engine = new SimulationEngine(parameters,this);
        this.newMap = new CreativeMap(engine,stage.getHeight());
        new Controller(engine, this);
        this.engineThread = new Thread(() -> {
            try {
                while (true) {
                    engine.run();
                }
            } catch(IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        });
        GridPane gridPane = newMap.getGridPane();
        gridPane.setAlignment(Pos.CENTER);
        borderPane.setCenter(gridPane);
        startApp();
    }

    public void startApp() {
        startButton.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84)");
        borderPane.setCenter(startButton);
        startButton.setOnAction(actionEvent -> {
            engine.changeStatus();
            engineThread.start();
            addButtons();
        });
    }

    private void addButtons() {
        HBox buttons = new HBox();
        buttons.setSpacing(300);


        HBox centerButtons = new HBox(exitButton, buttonEndTracking);
        centerButtons.setSpacing(15);
        exitButton.setOnAction(action -> {
            engineThread.interrupt();
            stage.close();
        });
        buttonEndTracking.setOnAction(action -> {
            engine.changeStatus();
        });

        buttons.getChildren().addAll(centerButtons);
        buttons.setAlignment(Pos.CENTER);
        borderPane.setBottom(buttons);
        BorderPane.setAlignment(buttons, Pos.CENTER);
        BorderPane.setMargin(buttons, new Insets(10, 0, 10, 0));
        exitButton.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        buttonEndTracking.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
    }

    public VBox uploadStats() {
        Statistic stats = engine.getStat();
        stats.updateStats();

        String labelStyle = "-fx-font-family: 'Bauhaus 93'; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);";

        Label title = new Label("STATISTIC MAP");

        Label worldDays = new Label("Number Day: " + stats.getWorldDays());
        worldDays.setStyle(labelStyle);

        Label numberOfAliveAnimals = new Label("Number of Alive Animals"+ stats.getNumberAnimals());
        numberOfAliveAnimals.setStyle(labelStyle);

        Label numberOfGrass = new Label("Number of grass: " + stats.getNumberGrass());
        numberOfGrass.setStyle(labelStyle);

        Label numberOfDeadAnimals = new Label("Number of Dead Animals " + stats.getNumberDeadAnimals());
        numberOfDeadAnimals.setStyle(labelStyle);

        Label avgEnergy = new Label("Average of energy: " + stats.getAvgEnergy());
        avgEnergy.setStyle(labelStyle);

        Label avgLifeDaysDeadAnimal = new Label("Average of life: " + stats.getAvgLife());
        avgLifeDaysDeadAnimal.setStyle(labelStyle);

        Label avgChildren = new Label("Average of Children: " + stats.getAvgChildren());
        avgChildren.setStyle(labelStyle);

        Label dominantGenotype = new Label("Dominant Genotype: " + stats.getDominantGenotype());
        dominantGenotype.setStyle(labelStyle);

        VBox statistics = new VBox(15);
        statistics.getChildren().addAll(title,worldDays, numberOfAliveAnimals, numberOfGrass, numberOfDeadAnimals, avgEnergy, avgLifeDaysDeadAnimal, avgChildren, dominantGenotype);
        statistics.setAlignment(Pos.TOP_CENTER);
        title.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);-fx-font-weight: bold;");
        title.setFont(new Font(15));
        statistics.setStyle(String.valueOf(new Insets(0,1,1,50)));
        return statistics;
    }

    public void uploadMap() {
        Platform.runLater(() ->{
            newMap.creativeMap();
            GridPane gridPane = newMap.getGridPane();
            gridPane.setGridLinesVisible(true);
            VBox stat = uploadStats();
            stat.setAlignment(Pos.CENTER);
            stat.setMaxHeight(stage.getHeight()/1.5);
            stat.setStyle("-fx-background-color: rgba(8,56,65,0.84);");
            HBox hbox = new HBox(10);
            hbox.getChildren().addAll(gridPane, stat);
            hbox.setAlignment(Pos.CENTER);
            borderPane.setCenter(hbox);

        });
    }


}
