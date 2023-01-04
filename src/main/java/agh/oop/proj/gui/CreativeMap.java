package agh.oop.proj.gui;

import agh.oop.proj.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.List;
import java.util.Map;

public class CreativeMap {
    private final SimulationEngine engine;
    private final GridPane gridPane;

    private final Settings parameters;
    private double size;

    private Images images = new Images();

    public CreativeMap(SimulationEngine engine, BorderPane border,double sizeScene) {
        this.engine = engine;
        this.parameters = engine.getSettings();
        this.gridPane = new GridPane();
        int width = parameters.getMapWidth();
        int height = parameters.getMapHeight();
        this.size = Math.max(width, height);
        for (int i = 0; i < parameters.getMapWidth(); i++) {
            this.gridPane.getColumnConstraints().add(new ColumnConstraints(sizeScene / (0.8*size)));
        }
        for (int i = 0; i < parameters.getMapHeight(); i++) {
            this.gridPane.getRowConstraints().add(new RowConstraints(sizeScene / (1.5*size)));
        }
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
        int freePosition = 0;
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
                        switch (animal.getImageIdx()) {
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
                        posit.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
                        posit.setFont(Font.font(20/(0.2*size)));
                        ElementBox pictures = new ElementBox(animal, engine);
                        pictures.createElement(imageView);
                        double imageHeight = 500 / (1.5 * size*howMany);
                        double imageWidth = 600 / (1.5 * size*howMany);
                        imageView.setFitHeight(imageHeight);
                        imageView.setFitWidth(imageWidth);
                        ProgressBar lifeBar = pictures.energyInAnimal();
                        Label live = new Label(String.format("%.2f%%", lifeBar.getProgress() * 100));
                        live.setVisible(false);
                        live.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
                        StackPane stackPane = new StackPane();
                        stackPane.getChildren().addAll(imageView, live);
                        imageView.setOnMouseEntered(event -> live.setVisible(true));  // po najechaniu na obrazek ustaw etykietę jako widoczną
                        imageView.setOnMouseExited(event -> live.setVisible(false));  // po opuszczeniu obrazka ustaw etykietę jako niewidoczną
                        lifeBar.setPrefHeight(80/(size));
                        lifeBar.setPrefWidth(600 / (1.5 * size*howMany));
                        lifeBar.setMinHeight(10);
                        HBox lifeandposition = new HBox();
                        lifeandposition.getChildren().addAll(lifeBar,posit);
                        box.getChildren().addAll(stackPane,lifeandposition);
                        hbox.getChildren().addAll(box,posit);
                    }
                    gridPane.add(hbox,i,j);
                    GridPane.setHalignment(hbox, Pos.CENTER.getHpos());
                }else{
                    freePosition+=1;
                    if(square.didGrassGrow()){
                        Label posit = new Label(position.toString());
                        posit.setStyle("-fx-font-family: 'Bauhaus 93'; -fx-text-fill: #30cbc8; -fx-background-color: rgba(8,56,65,0.84);");
                        posit.setFont(Font.font(20/(0.2*size)));
                        VBox box = new VBox();
                        box.setSpacing(3);
                        box.setAlignment(Pos.CENTER);
                        imageView = new ImageView(images.grassImage);
                        double imageHeight = 500 / (1.5 * size);
                        double imageWidth = 600 / (1.5 * size);
                        imageView.setFitHeight(imageHeight);
                        imageView.setFitWidth(imageWidth);
                        box.getChildren().addAll(imageView,posit);
                        gridPane.add(box,i,j);
                        GridPane.setHalignment(box, Pos.CENTER.getHpos());
                    }
                }
            }
            engine.setFreePositionQuantity(freePosition);
            gridPane.setAlignment(Pos.CENTER);
        }
    }
}
