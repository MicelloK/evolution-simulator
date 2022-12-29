package agh.oop.proj.gui;

import agh.oop.proj.Settings;
import agh.oop.proj.SimulationEngine;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StartApp {
    private final Stage stage;
    private final BorderPane borderPane;

    private final Button startButton = new Button("Create Simulation");

    private final Button exitButton = new Button("EXIT");

    private final Button buttonEndTracking = new Button("END TRACKING");

    private final SimulationEngine engine;

    private Thread engineThread;

    public StartApp(Settings parameters) {
        this.stage = new Stage();
        this.borderPane = new BorderPane();
        Scene sceneMain = new Scene(borderPane);
        stage.setScene(sceneMain);
        stage.show();
        stage.setFullScreen(true);
        Label tittle = new Label("This is the world that evolving before our eyes! ");
        tittle.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 22pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        borderPane.setTop(tittle);
        BorderPane.setAlignment(tittle, Pos.CENTER);
        borderPane.setBackground(new Background(new BackgroundFill(Color.PALETURQUOISE, CornerRadii.EMPTY, Insets.EMPTY)));
        BorderPane.setMargin(tittle, new Insets(20, 0, 20, 0));
        engine = new SimulationEngine(parameters,this);
        this.engineThread = new Thread(() -> {
            try {
                while (true) {
                    engine.run();
                }
            } catch(IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        });
        CreativeMap mapWorld = new CreativeMap(engine,borderPane);
        GridPane gridPane = mapWorld.getGridPane();
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

    public void uploadMap() {
        Platform.runLater(() ->{
            CreativeMap newMap = new CreativeMap(engine,borderPane);
            GridPane gridPane = newMap.getGridPane();
            gridPane.setGridLinesVisible(true);
            gridPane.setAlignment(Pos.CENTER);
            borderPane.setCenter(gridPane);
        });
    }


}
