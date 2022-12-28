package agh.oop.proj.gui;

import agh.oop.proj.*;
import javafx.application.Application;

import javafx.application.Platform;
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
import java.io.IOException;

public class App extends Application {
    private final BorderPane border = new BorderPane();
    private SimulationEngine engine;

    private ImageView grass = new ImageView(new Images().grassImage);


    private GridPane gridPane;

    private Settings parameters;

    private VBox stats;
    private final Button startButton = new Button("Create Simulation");
    private final Button exitButton = new Button("EXIT");
    private final Button buttonStartStopRight = new Button("START/STOP Right Map");
    private final Button buttonStartStopLeft = new Button("START/STOP Left Map");
    private final Button buttonEndTracking = new Button("END TRACKING");

    private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.getIcons().add(new Image(new FileInputStream("src/main/resources/world.jpg")));
        primaryStage.setTitle("About unusual adventures with evolution");
        primaryStage.alwaysOnTopProperty();
        primaryStage.setScene(new Scene(border, 880, 460));
        primaryStage.show();
    }

    private void initBorder() {
        Label tittle = new Label("This is the world that evolving before our eyes! ");
        tittle.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 22pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        border.setTop(tittle);
        BorderPane.setAlignment(tittle, Pos.CENTER);
        BorderPane.setMargin(tittle, new Insets(20, 0, 20, 0));
    }

    private void initGetDate(){
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
        VBox listTextFieldRight = new VBox();
        listTextFieldRight.getChildren().addAll(mapWidth, mapHeight, startGrassQuantity, eatingGrassEnergy, grassPerDay, startAnimalsQuantity, startAnimalsEnergy, animalFullEnergy);
        listTextFieldRight.setSpacing(10);
        VBox listTextFieldLeft = new VBox();
        listTextFieldLeft.getChildren().addAll(reproductionEnergy, minimalMutationNumber, maximalMutationNumber, genLength, movementDetails, animalMoving, mutationVariant, mapVariant);
        listTextFieldLeft.setSpacing(10);


        Label mapWidthlabel = new Label("Width: ");
        mapWidthlabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label mapHeightlabel = new Label("Height: ");
        mapHeightlabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label startGrassQuantitylabel = new Label("Start Quantity of Grass: ");
        startGrassQuantitylabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label eatingGrassEnergylabel = new Label("Energy from eating grass: ");
        eatingGrassEnergylabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label grassPerDaylabel = new Label("How many grass grow per day: ");
        grassPerDaylabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label startAnimalsQuantitylabel = new Label("Start Quantity of Animals: ");
        startAnimalsQuantitylabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label startAnimalsEnergylabel = new Label("Start energy of Animals: ");
        startAnimalsEnergylabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label animalFullEnergylabel = new Label("Full Animals energy: ");
        animalFullEnergylabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label reproductionEnergylabel = new Label("Energy to reproduction: ");
        reproductionEnergylabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label minimalMutationNumberlabel = new Label("Minimal mutation number: ");
        minimalMutationNumberlabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label maximalMutationNumberlabel = new Label("Maximal mutation number: ");
        maximalMutationNumberlabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        Label genLengthlabel = new Label("Gen length: ");
        genLengthlabel.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
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
        listOfLabelLeft.getChildren().addAll(reproductionEnergylabel, minimalMutationNumberlabel, maximalMutationNumberlabel, genLengthlabel, movementDetailsLabel, animalMovingLabel, mutationVariantLabel, mapVariantLabel);
        listOfLabelLeft.setSpacing(18);
        listOfLabelRight.getChildren().addAll(mapWidthlabel, mapHeightlabel, startGrassQuantitylabel, eatingGrassEnergylabel, grassPerDaylabel, startAnimalsQuantitylabel, startAnimalsEnergylabel, animalFullEnergylabel);
        listOfLabelRight.setSpacing(18);

        HBox inputList = new HBox();
        inputList.getChildren().addAll(listOfLabelRight, listTextFieldRight, listOfLabelLeft, listTextFieldLeft);
        inputList.setSpacing(10);
        inputList.setAlignment(Pos.TOP_CENTER);

        VBox confirm = new VBox();
        confirm.getChildren().addAll(getParametr);
        VBox.setVgrow(getParametr, Priority.ALWAYS);
        VBox.setMargin(getParametr, new Insets(60, 0, 200, 0));
        border.setCenter(inputList);
        border.setBottom(confirm);
        confirm.setAlignment(Pos.TOP_CENTER);
        border.setBackground(new Background(new BackgroundFill(Color.PALETURQUOISE, CornerRadii.EMPTY, Insets.EMPTY)));

        getParametr.setOnAction(action -> {
            borderNew();
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
            try {
                parameters = new Settings(textFieldValues);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            engine = new SimulationEngine(parameters,this);
            grass.setFitWidth(800 / (2 * parameters.getMapWidth()));
            grass.setFitHeight(800 / (2 * parameters.getMapHeight()));
            primaryStage.setFullScreen(true);
            startApp();
        });

    }

    @Override
    public void init() throws IOException {
        initBorder();
        initGetDate();
    }

    public void startApp() {
        Thread engineThread = new Thread(engine);
        startButton.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84)");
        border.setCenter(startButton);
        startButton.setOnAction(actionEvent -> {
            engine.changeStatus();
            engineThread.start();
            addButtons();
        });
    }

    public void uploadMap() {
        Platform.runLater(() ->{
            CreativeMap newMap = new CreativeMap(engine, this,border);
            gridPane = newMap.getGridPane();
            this.gridPane.setGridLinesVisible(true);
            gridPane.setAlignment(Pos.CENTER);
            border.setCenter(gridPane);
        });
    }

    private void addButtons() {
        HBox buttons = new HBox();
        buttons.setSpacing(300);


        HBox centerButtons = new HBox(exitButton, buttonEndTracking);
        centerButtons.setSpacing(15);
        exitButton.setOnAction(action -> {
            System.exit(0);
        });
        buttonEndTracking.setOnAction(action -> {
            engine.changeStatus();
        });

        buttons.getChildren().addAll(centerButtons);
        buttons.setAlignment(Pos.CENTER);
        border.setBottom(buttons);
        BorderPane.setAlignment(buttons, Pos.CENTER);
        BorderPane.setMargin(buttons, new Insets(10, 0, 10, 0));
        exitButton.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        buttonStartStopLeft.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
        buttonStartStopRight.setStyle("-fx-background-color: #d79097; ");
        buttonEndTracking.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 15 pt; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
    }

    private void borderNew() {
        border.setCenter(null);
        border.setRight(null);
        border.setLeft(null);
        border.setBottom(null);
    }
}