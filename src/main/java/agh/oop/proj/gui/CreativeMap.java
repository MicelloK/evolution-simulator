package agh.oop.proj.gui;

import agh.oop.proj.AbstractWorldMap;
import agh.oop.proj.Settings;
import agh.oop.proj.SimulationEngine;
import agh.oop.proj.Vector2d;
import javafx.application.Application;
import javafx.scene.layout.*;

public class CreativeMap {
    private final SimulationEngine engine;
    private final GridPane gridPane;
    private final Application app;



    public CreativeMap(SimulationEngine engine, Application app) {
        this.app = app;
        this.engine = engine;
        this.gridPane = new GridPane();
        for (int i = 0; i <engine.getSettings().getMapWidth(); i++){
            this.gridPane.getColumnConstraints().add(new ColumnConstraints(1200/ (3 * engine.getSettings().getMapWidth() )));
        }
        for (int i = 0; i < engine.getSettings().getMapHeight(); i++){
            this.gridPane.getRowConstraints().add(new RowConstraints(1000/ (3 * engine.getSettings().getMapHeight()) ));
        }
        this.gridPane.setGridLinesVisible(true);
    }

    public GridPane getGridPane(){ return this.gridPane;}

    private void updateMap(){
        gridPane.getChildren().clear();
        gridPane.setGridLinesVisible(true);
        coloringMap();
    }

    private void coloringMap(){
        Settings sett = engine.getSettings();
        AbstractWorldMap map = sett.getMap();

        for(int i = 0; i <= sett.getMapHeight(); i++ ) {
            for (int j = 0; j <= sett.getMapWidth(); j++) {
                StackPane till = new StackPane();
                Vector2d pos = new Vector2d(i,j);
                if(sett.getMap().objectsAt(pos) == null) {
                    till.setStyle("-fx-background-color: #00b27a; ");
                } else {
                    till.setStyle("-fx-background-color: #00ce8e; ");
                }
                gridPane.add(till, i, j);
            }
        }
    }

}
