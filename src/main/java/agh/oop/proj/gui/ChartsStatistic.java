package agh.oop.proj.gui;

import agh.oop.proj.Statistics;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class ChartsStatistic {
    private final LineChart chart;
    private final XYChart.Series mapSeries;

    public ChartsStatistic(String string) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Day");
        this.chart = new LineChart(xAxis, yAxis);
        this.mapSeries = new XYChart.Series();

        chart.setTitle(string);
        mapSeries.setName("Map Information");
        chart.setCreateSymbols(false);
        chart.lookup(".chart-legend").setStyle("-fx-background-color: transparent;");
        chart.getData().addAll(mapSeries);
    }

    public LineChart getChart() {
        return chart;
    }

    public void updateAnimalsChart(Statistics stats) {
        mapSeries.getData().add(new XYChart.Data<>(stats.getWorldDays(), stats.getNumberAnimals()));
    }

    public void updateGrassChart(Statistics stats) {
        mapSeries.getData().add(new XYChart.Data<>(stats.getWorldDays(), stats.getNumberGrass()));
    }

    public void updateAvgEnergy(Statistics stats) {
        mapSeries.getData().add(new XYChart.Data<>(stats.getWorldDays(), stats.getAvgEnergy()));
    }

    public void updateAvgLifeLength(Statistics stats) {
        mapSeries.getData().add(new XYChart.Data<>(stats.getWorldDays(), stats.getAvgLife()));
    }

    public void updateAvgChildren(Statistics stats) {
        mapSeries.getData().add(new XYChart.Data<>(stats.getWorldDays(), stats.getAvgChildren()));
    }

    public void updateFreePosition(Statistics stats) {
        mapSeries.getData().add(new XYChart.Data<>(stats.getWorldDays(), stats.getFreePositionQuantity()));
    }

    public void updateDeathAnimals(Statistics stats) {
        mapSeries.getData().add(new XYChart.Data<>(stats.getWorldDays(), stats.getNumberDeadAnimals()));
    }
}
