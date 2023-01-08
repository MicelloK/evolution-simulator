package agh.oop.proj.gui;

import agh.oop.proj.OptionReader;
import agh.oop.proj.Settings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GetDateStage {
    private final Stage stage;
    private final BorderPane borderPane = new BorderPane();
    String labelStyle = "-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);";

    public GetDateStage() throws FileNotFoundException {

        this.stage = new Stage();
        stage.getIcons().add(new Image(new FileInputStream("src/main/resources/world.jpg")));
        stage.setTitle("About unusual adventures with evolution");
        stage.setScene(new Scene(borderPane, 880, 500));
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
        TextField mapWidth = new TextField("20");
        TextField mapHeight = new TextField("15");
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


        ChoiceBox movementDetails = new ChoiceBox();
        movementDetails.getItems().addAll("Earth", "Portal");
        ChoiceBox animalMoving = new ChoiceBox();
        animalMoving.getItems().addAll("Predestination", "Craziness");
        ChoiceBox mutationVariant = new ChoiceBox();
        mutationVariant.getItems().addAll("Random", "Correction");
        ChoiceBox mapVariant = new ChoiceBox();
        mapVariant.getItems().addAll("Equators", "Corpses");


        Button getParametr = new Button("CONFIRM");
        getParametr.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");

        VBox listTextFieldRight = new VBox(10);
        listTextFieldRight.getChildren().addAll(name, mapWidth, mapHeight, startGrassQuantity, eatingGrassEnergy, grassPerDay, startAnimalsQuantity, startAnimalsEnergy, animalFullEnergy);

        VBox listTextFieldLeft = new VBox(10);
        listTextFieldLeft.getChildren().addAll(reproductionEnergy, minimalMutationNumber, maximalMutationNumber, genLength, movementDetails, animalMoving, mutationVariant, mapVariant);


        Label namelabel = new Label("Name: ");
        namelabel.setStyle(labelStyle);
        Label mapWidthlabel = new Label("Width: ");
        mapWidthlabel.setStyle(labelStyle);
        Label mapHeightlabel = new Label("Height: ");
        mapHeightlabel.setStyle(labelStyle);
        Label startGrassQuantitylabel = new Label("Start Quantity of Grass: ");
        startGrassQuantitylabel.setStyle(labelStyle);
        Label eatingGrassEnergylabel = new Label("Energy from eating grass: ");
        eatingGrassEnergylabel.setStyle(labelStyle);
        Label grassPerDaylabel = new Label("How many grass grow per day: ");
        grassPerDaylabel.setStyle(labelStyle);
        Label startAnimalsQuantitylabel = new Label("Start Quantity of Animals: ");
        startAnimalsQuantitylabel.setStyle(labelStyle);
        Label startAnimalsEnergylabel = new Label("Start energy of Animals: ");
        startAnimalsEnergylabel.setStyle(labelStyle);
        Label animalFullEnergylabel = new Label("Full Animals energy: ");
        animalFullEnergylabel.setStyle(labelStyle);
        Label reproductionEnergylabel = new Label("Energy to reproduction: ");
        reproductionEnergylabel.setStyle(labelStyle);
        Label minimalMutationNumberlabel = new Label("Minimal mutation number: ");
        minimalMutationNumberlabel.setStyle(labelStyle);
        Label maximalMutationNumberlabel = new Label("Maximal mutation number: ");
        maximalMutationNumberlabel.setStyle(labelStyle);
        Label genLengthlabel = new Label("Gen length: ");
        genLengthlabel.setStyle(labelStyle);
        Label movementDetailsLabel = new Label("Movement Details:");
        movementDetailsLabel.setStyle(labelStyle);
        Label animalMovingLabel = new Label("Animal Moving:");
        animalMovingLabel.setStyle(labelStyle);
        Label mutationVariantLabel = new Label("Mutation Variant:");
        mutationVariantLabel.setStyle(labelStyle);
        Label mapVariantLabel = new Label("Map Variant:");
        mapVariantLabel.setStyle(labelStyle);

        VBox listOfLabelLeft = new VBox(18);
        VBox listOfLabelRight = new VBox(18);
        listOfLabelLeft.getChildren().addAll(reproductionEnergylabel, minimalMutationNumberlabel, maximalMutationNumberlabel, genLengthlabel, movementDetailsLabel, animalMovingLabel, mutationVariantLabel, mapVariantLabel);
        listOfLabelRight.getChildren().addAll(namelabel, mapWidthlabel, mapHeightlabel, startGrassQuantitylabel, eatingGrassEnergylabel, grassPerDaylabel, startAnimalsQuantitylabel, startAnimalsEnergylabel, animalFullEnergylabel);


        HBox inputList = new HBox(10);
        inputList.getChildren().addAll(listOfLabelRight, listTextFieldRight, listOfLabelLeft, listTextFieldLeft);
        inputList.setAlignment(Pos.TOP_CENTER);


        VBox confirm = new VBox(getParametr);
        VBox.setVgrow(getParametr, Priority.ALWAYS);
        VBox.setMargin(getParametr, new Insets(60, 0, 200, 0));
        confirm.setAlignment(Pos.TOP_CENTER);

        borderPane.setCenter(inputList);
        borderPane.setBottom(confirm);
        borderPane.setBackground(new Background(new BackgroundFill(Color.PALETURQUOISE, CornerRadii.EMPTY, Insets.EMPTY)));

        getParametr.setOnAction(action -> {
            String configName;
            configName = name.getText();
            if (configName.contains(",")) {
                try {
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
            textFieldValues[14] = (String) movementDetails.getValue();
            textFieldValues[12] = (String) animalMoving.getValue();
            textFieldValues[13] = (String) mutationVariant.getValue();
            textFieldValues[15] = (String) mapVariant.getValue();

            Settings parameter;
            try {
                OptionReader.add(configName, textFieldValues);
                parameter = new Settings(configName, textFieldValues);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                new StartApp(parameter);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            stage.close();
        });
    }
}
