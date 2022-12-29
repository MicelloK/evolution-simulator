package agh.oop.proj.gui;

import agh.oop.proj.*;
import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class App extends Application {
    private final BorderPane border = new BorderPane();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.getIcons().add(new Image(new FileInputStream("src/main/resources/world.jpg")));
        primaryStage.setTitle("About unusual adventures with evolution");
        primaryStage.alwaysOnTopProperty();
        primaryStage.setScene(new Scene(border, 880, 460));
        primaryStage.show();
    }

    private void initBorder() throws FileNotFoundException {
        Label tittle = new Label("Choice your configuration and have fun!");
        tittle.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 22pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        border.setTop(tittle);
        BorderPane.setAlignment(tittle, Pos.CENTER);
        BorderPane.setMargin(tittle, new Insets(20, 0, 20, 0));
    }

    private void initGetDate(){
        ChoiceBox confVariant = new ChoiceBox();
        confVariant.getItems().addAll("My Configuration", "Configuration 1", "Configuration 2");


        Button getParametr = new Button("CONFIRM");
        getParametr.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");

        Button exitButton = new Button("EXIT");
        exitButton.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");


        Label choiceLabel = new Label("Your choice: ");
        choiceLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");


        HBox inputList = new HBox();
        inputList.getChildren().addAll(choiceLabel,confVariant);
        inputList.setSpacing(10);
        inputList.setAlignment(Pos.CENTER);

        HBox confirm = new HBox();
        confirm.setSpacing(50);
        confirm.getChildren().addAll(getParametr,exitButton);
        border.setMargin(confirm, new Insets(10, 0, 60, 0));
        border.setCenter(inputList);
        border.setBottom(confirm);
        confirm.setAlignment(Pos.BOTTOM_CENTER);
        border.setBackground(new Background(new BackgroundFill(Color.PALETURQUOISE, CornerRadii.EMPTY, Insets.EMPTY)));

        getParametr.setOnAction(action -> {
            try {
                String items = confVariant.getValue().toString();
                if ("My Configuration" == items) {
                    new GetDateStage();
                } else if ("Configuration 1" == items) {
                    System.exit(0);
                } else if ("Configuration 2" == items) {
                    System.exit(0);
                } else {
                    throw new Exception("Configuration not choice");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        exitButton.setOnAction(action -> {
            System.exit(0);
        });

    }

    @Override
    public void init() throws IOException {
        initBorder();
        initGetDate();
    }
}