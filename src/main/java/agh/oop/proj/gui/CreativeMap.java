package agh.oop.proj.gui;

import agh.oop.proj.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.util.List;
import java.util.Map;

public class CreativeMap {
    private final SimulationEngine engine;
    private final GridPane gridPane;
    private final Application app;

    private final Settings parameters;


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

                MapSquare square = mapsquer.get(new Vector2d(i,j));
                HBox hbox = new HBox();
                hbox.setSpacing(5);
                hbox.setAlignment(Pos.CENTER);
                if (square != null && square.getObjects().size() != 0) {
                    int howMany = square.getObjects().size();
                    for (IMapElement animal : square.getObjects()) {
                        switch (animal.getImage()) {
                            case 5 -> imageView = new ImageView(new Images().Image5);
                            case 4 -> imageView = new ImageView(new Images().Image4);
                            case 3 -> imageView = new ImageView(new Images().Image3);
                            case 2 -> imageView = new ImageView(new Images().Image2);
                            case 1 -> imageView = new ImageView(new Images().Image1);
                            default -> throw new IllegalStateException("Unexpected value: ");
                        }
                        imageView.setFitWidth(800 / (2*parameters.getMapWidth() * (howMany)));
                        imageView.setFitHeight((800 /(2*parameters.getMapHeight() * (howMany))));
                        hbox.getChildren().add(imageView);

                    }
                    gridPane.add(hbox,i,j);
                    GridPane.setHalignment(hbox, Pos.CENTER.getHpos());
                }else{
                    if(square.didGrassGrow()){
                        imageView = new ImageView(new Images().grassImage);
                        imageView.setFitWidth(800 / (2*parameters.getMapWidth() ));
                        imageView.setFitHeight(800/(2*parameters.getMapWidth()));
                        hbox.getChildren().add(imageView);
                        gridPane.add(hbox,i,j);
                        GridPane.setHalignment(hbox, Pos.CENTER.getHpos());
                    }
                }
            }
            gridPane.setAlignment(Pos.CENTER);
        }
    }
}
