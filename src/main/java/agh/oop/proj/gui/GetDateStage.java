package agh.oop.proj.gui;

import agh.oop.proj.OptionReader;
import agh.oop.proj.Settings;
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

public class GetDateStage {
    private final Stage stage;
    private final BorderPane borderPane = new BorderPane();

    public GetDateStage() throws FileNotFoundException {
        this.stage = new Stage();
        stage.getIcons().add(new Image(new FileInputStream("src/main/resources/world.jpg")));
        stage.setTitle("About unusual adventures with evolution");
        stage.alwaysOnTopProperty();
        stage.setScene(new Scene(borderPane, 880, 460));
        stage.show();
        Label tittle = new Label("This is the world that evolving before our eyes! ");
        tittle.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 22pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        borderPane.setTop(tittle);
        BorderPane.setAlignment(tittle, Pos.CENTER);
        BorderPane.setMargin(tittle, new Insets(20, 0, 20, 0));
        initGetDate();
    }

    private void initGetDate() {
        TextField name = new TextField("My new configuration");
        TextField mapWidth = new TextField();
        TextField mapHeight = new TextField();
        TextField startGrassQuantity = new TextField("5");
        TextField eatingGrassEnergy = new TextField("1");
        TextField startAnimalsQuantity = new TextField("15");
        TextField startAnimalsEnergy = new TextField("10");
        TextField animalFullEnergy = new TextField("15");
        TextField reproductionEnergy = new TextField("5");
        TextField minimalMutationNumber = new TextField("2");
        TextField maximalMutationNumber = new TextField("9");
        TextField genLength = new TextField("8");
        TextField grassPerDay = new TextField("8");
        ChoiceBox<String> movementDetails = new ChoiceBox<>();
        movementDetails.getItems().addAll("Earth", "Portal");
        ChoiceBox<String> animalMoving = new ChoiceBox<>();
        animalMoving.getItems().addAll("Predestination", "Craziness");
        ChoiceBox<String> mutationVariant = new ChoiceBox<>();
        mutationVariant.getItems().addAll("Random", "Correction");
        ChoiceBox<String> mapVariant = new ChoiceBox<>();
        mapVariant.getItems().addAll("Equators", "Corpses");


        Button getParameter = new Button("CONFIRM");
        getParameter.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        VBox listTextFieldRight = new VBox();
        listTextFieldRight.getChildren().addAll(name, mapWidth, mapHeight, startGrassQuantity, eatingGrassEnergy, grassPerDay, startAnimalsQuantity, startAnimalsEnergy, animalFullEnergy);
        listTextFieldRight.setSpacing(10);
        VBox listTextFieldLeft = new VBox();
        listTextFieldLeft.getChildren().addAll(reproductionEnergy, minimalMutationNumber, maximalMutationNumber, genLength, movementDetails, animalMoving, mutationVariant, mapVariant);
        listTextFieldLeft.setSpacing(10);


        Label nameLabel = new Label("Name: ");
        nameLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label mapWidthLabel = new Label("Width: ");
        mapWidthLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label mapHeightLabel = new Label("Height: ");
        mapHeightLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label startGrassQuantityLabel = new Label("Start Quantity of Grass: ");
        startGrassQuantityLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label eatingGrassEnergyLabel = new Label("Energy from eating grass: ");
        eatingGrassEnergyLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label grassPerDayLabel = new Label("How many grass grow per day: ");
        grassPerDayLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label startAnimalsQuantityLabel = new Label("Start Quantity of Animals: ");
        startAnimalsQuantityLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label startAnimalsEnergyLabel = new Label("Start energy of Animals: ");
        startAnimalsEnergyLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label animalFullEnergyLabel = new Label("Full Animals energy: ");
        animalFullEnergyLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label reproductionEnergyLabel = new Label("Energy to reproduction: ");
        reproductionEnergyLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label minimalMutationNumberLabel = new Label("Minimal mutation number: ");
        minimalMutationNumberLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label maximalMutationNumberLabel = new Label("Maximal mutation number: ");
        maximalMutationNumberLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label genLengthLabel = new Label("Gen length: ");
        genLengthLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label movementDetailsLabel = new Label("Movement Details:");
        movementDetailsLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label animalMovingLabel = new Label("Animal Moving:");
        animalMovingLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label mutationVariantLabel = new Label("Mutation Variant:");
        mutationVariantLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label mapVariantLabel = new Label("Map Variant:");
        mapVariantLabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");

        VBox listOfLabelLeft = new VBox();
        VBox listOfLabelRight = new VBox();
        listOfLabelLeft.getChildren().addAll(reproductionEnergyLabel, minimalMutationNumberLabel, maximalMutationNumberLabel, genLengthLabel, movementDetailsLabel, animalMovingLabel, mutationVariantLabel, mapVariantLabel);
        listOfLabelLeft.setSpacing(18);
        listOfLabelRight.getChildren().addAll(nameLabel, mapWidthLabel, mapHeightLabel, startGrassQuantityLabel, eatingGrassEnergyLabel, grassPerDayLabel, startAnimalsQuantityLabel, startAnimalsEnergyLabel, animalFullEnergyLabel);
        listOfLabelRight.setSpacing(18);

        HBox inputList = new HBox();
        inputList.getChildren().addAll(listOfLabelRight, listTextFieldRight, listOfLabelLeft, listTextFieldLeft);
        inputList.setSpacing(10);
        inputList.setAlignment(Pos.TOP_CENTER);

        VBox confirm = new VBox();
        confirm.getChildren().addAll(getParameter);
        VBox.setVgrow(getParameter, Priority.ALWAYS);
        VBox.setMargin(getParameter, new Insets(60, 0, 200, 0));
        borderPane.setCenter(inputList);
        borderPane.setBottom(confirm);
        confirm.setAlignment(Pos.TOP_CENTER);
        borderPane.setBackground(new Background(new BackgroundFill(Color.PALETURQUOISE, CornerRadii.EMPTY, Insets.EMPTY)));

        getParameter.setOnAction(action -> {
            String configName;
            configName = name.getText();
            if (configName.contains(",") || configName.isEmpty() || configName.charAt(0) == ' ' || configName.charAt(configName.length()-1)  == ' ') {
                try {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("error");
                    alert.setHeaderText("INCORRECT CONFIG NAME");
                    alert.setContentText("Please, make sure your name is correct");
                    alert.showAndWait();
                    throw new Exception("Incorrect config name");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                if (OptionReader.find(configName) != null) {
                    throw new Exception("this configuration already exist, use another name");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            String[] textFieldValues = new String[16];
            textFieldValues[0] = mapWidth.getText();
            textFieldValues[1] = mapHeight.getText();
            textFieldValues[2] = startGrassQuantity.getText();
            textFieldValues[3] = eatingGrassEnergy.getText();
            textFieldValues[4] = grassPerDay.getText();
            textFieldValues[5] = startAnimalsQuantity.getText();
            textFieldValues[6] = startAnimalsEnergy.getText();
            textFieldValues[7] = animalFullEnergy.getText();
            textFieldValues[8] = reproductionEnergy.getText();
            textFieldValues[9] = minimalMutationNumber.getText();
            textFieldValues[10] = maximalMutationNumber.getText();
            textFieldValues[11] = genLength.getText();
            textFieldValues[14] = movementDetails.getValue();
            textFieldValues[12] = animalMoving.getValue();
            textFieldValues[13] = mutationVariant.getValue();
            textFieldValues[15] = mapVariant.getValue();
            Settings parameter;
            try {
                parameter = new Settings(configName, textFieldValues);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("error");
                alert.setHeaderText("INCORRECT INPUT DATA");
                alert.setContentText("Please, check your settings and try again");
                alert.showAndWait();
                throw new RuntimeException(e);
            }

            try {
                OptionReader.add(configName, textFieldValues);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            new StartApp(parameter);
            stage.close();
        });
    }
}
