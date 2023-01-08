package agh.oop.proj.gui;

import agh.oop.proj.*;
import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class App extends Application {
    private final BorderPane border = new BorderPane();
    String buttonStyle = "-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);";

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.getIcons().add(new Image(new FileInputStream("src/main/resources/world.jpg")));
        primaryStage.setTitle("About unusual adventures with evolution");
        primaryStage.alwaysOnTopProperty();
        primaryStage.setScene(new Scene(border, 880, 460));
        primaryStage.show();
    }

    private void initBorder() {
        Label tittle = new Label("Choice your configuration and have fun!");
        tittle.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 26 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        border.setTop(tittle);
        BorderPane.setAlignment(tittle, Pos.CENTER);
        BorderPane.setMargin(tittle, new Insets(20, 0, 20, 0));
    }

    private void initGetDate() throws FileNotFoundException {
        ChoiceBox<String> confVariant = new ChoiceBox<>();
        confVariant.getItems().add("My Configuration");
        confVariant.getItems().addAll(OptionReader.names());
        confVariant.setValue("My Configuration");

        Button getParameter = new Button("CONFIRM");
        getParameter.setStyle(buttonStyle);

        Button exitButton = new Button("EXIT");
        exitButton.setStyle(buttonStyle);

        Label choiceLabel = new Label("Your choice: ");
        choiceLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");

        HBox inputList = new HBox(10, choiceLabel, confVariant);
        inputList.setAlignment(Pos.CENTER);

        HBox confirm = new HBox(50, getParameter, exitButton);
        BorderPane.setMargin(confirm, new Insets(10, 0, 60, 0));

        border.setCenter(inputList);
        border.setBottom(confirm);
        confirm.setAlignment(Pos.BOTTOM_CENTER);
        border.setBackground(new Background(new BackgroundFill(Color.PALETURQUOISE, CornerRadii.EMPTY, Insets.EMPTY)));

        getParameter.setOnAction(action -> {
            try {
                String items = confVariant.getValue();
                if (items.equals("My Configuration")) {
                    new GetDateStage();
                } else {
                    String[] headers = OptionReader.names();
                    for (String name : headers) {
                        if (items.equals(name)) {
                            String[] parameters = OptionReader.find(name);
                            if (parameters != null) {
                                Settings settings = new Settings(name, parameters);
                                new StartApp(settings);
                            } else {
                                throw new Exception("wrong configuration");
                            }
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        exitButton.setOnAction(action -> System.exit(0));
    }

    @Override
    public void init() throws IOException {
        initBorder();
        initGetDate();
    }
}
