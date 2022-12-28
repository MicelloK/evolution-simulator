package agh.oop.proj.gui;

import agh.oop.proj.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.util.List;
import java.util.Map;

public class CreativeMap {
    private final SimulationEngine engine;
    private final GridPane gridPane;
    private final Application app;

    private final Settings parameters;
    private int size;

    private Images images = new Images();



    public CreativeMap(SimulationEngine engine, Application app, BorderPane border) {
        this.app = app;
        this.engine = engine;
        this.parameters = engine.getSettings();
        this.gridPane = new GridPane();
        for (int i = 0; i < parameters.getMapWidth(); i++) {
            this.gridPane.getColumnConstraints().add(new ColumnConstraints(border.getWidth() / (2*parameters.getMapWidth())));
        }
        for (int i = 0; i < parameters.getMapHeight(); i++) {
            this.gridPane.getRowConstraints().add(new RowConstraints(border.getHeight() / (2*parameters.getMapHeight())));
        }
        int width = parameters.getMapWidth();
        int height = parameters.getMapHeight();
        this.size = Math.max(width, height);
        creativeMap();
    }

    private void creativeMap() {
        gridPane.getChildren().clear();
        gridPane.setGridLinesVisible(true);
        puttingObjects();
    }

    public GridPane getGridPane() {
        return this.gridPane;
    }

    private void puttingObjects() {
        AbstractWorldMap map = parameters.getMap();
        ImageView imageView;
        Map<Vector2d,MapSquare> mapsquer = map.getElements();
        List<Vector2d> mapContain = parameters.getMap().getPreferred();
        for (int i = 0; i < parameters.getMapWidth(); i++) {
            for (int j = 0; j < parameters.getMapHeight(); j++) {
                StackPane grasses = new StackPane();
                if(mapContain.contains(new Vector2d(i,j))){
                    grasses.setStyle("-fx-background-color: rgba(177,234,167,0.84)");
                    gridPane.add(grasses,i,j);
                }else{
                    grasses.setStyle("-fx-background-color: rgb(8,56,65)");
                    gridPane.add(grasses,i,j);
                }
                Vector2d position = new Vector2d(i,j);
                MapSquare square = mapsquer.get(new Vector2d(i,j));
                if (square != null && square.getObjects().size() != 0) {
                    HBox hbox = new HBox();
                    hbox.setSpacing(5);
                    hbox.setAlignment(Pos.CENTER);
                    int howMany = square.getObjects().size();
                    for (IMapElement animal : square.getObjects()) {
                        switch (animal.getImage()) {
                            case 5 -> imageView = new ImageView(images.Image5);
                            case 4 -> imageView = new ImageView(images.Image4);
                            case 3 -> imageView = new ImageView(images.Image3);
                            case 2 -> imageView = new ImageView(images.Image2);
                            case 1 -> imageView = new ImageView(images.Image1);
                            default -> throw new IllegalStateException("Unexpected value: ");
                        }
                        VBox box = new VBox();
                        box.setAlignment(Pos.CENTER);
                        Label posit = new Label(position.toString());
                        posit.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-font-size: 10px; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
                        ElementBox pictures = new ElementBox(animal, engine);
                        pictures.createElement(imageView);
                        double imageHeight = 500 / (2 * size*howMany);
                        double imageWidth = 600 / (2 * size*howMany);
                        imageView.setFitHeight(imageHeight);
                        imageView.setFitWidth(imageWidth);
                        ProgressBar lifeBar = pictures.energyInAnimal();
                        lifeBar.setPrefHeight(20);
                        lifeBar.setPrefWidth(600 / (2 * size)*howMany);
                        HBox lifeandposition = new HBox();
                        lifeandposition.getChildren().addAll(lifeBar,posit);
                        box.getChildren().addAll(imageView,lifeandposition);
                        hbox.getChildren().addAll(box,posit);
                    }
                    gridPane.add(hbox,i,j);
                    GridPane.setHalignment(hbox, Pos.CENTER.getHpos());
                }else{
                    if(square.didGrassGrow()){
                        Label posit = new Label(position.toString());
                        posit.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
                        VBox box = new VBox();
                        box.setSpacing(3);
                        box.setAlignment(Pos.CENTER);
                        imageView = new ImageView(images.grassImage);
                        double imageHeight = 500 / (2 * size);
                        double imageWidth = 600 / (2 * size);
                        imageView.setFitHeight(imageHeight);
                        imageView.setFitWidth(imageWidth);
                        box.getChildren().addAll(imageView,posit);
                        gridPane.add(box,i,j);
                        GridPane.setHalignment(box, Pos.CENTER.getHpos());
                    }
                }
            }
            gridPane.setAlignment(Pos.CENTER);
        }
    }
}
