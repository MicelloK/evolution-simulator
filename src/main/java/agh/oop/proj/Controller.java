package agh.oop.proj;

import agh.oop.proj.gui.StartApp;

public class Controller implements ISimulationObserver {
    private final SimulationEngine model;
    private final StartApp view;

    public Controller(SimulationEngine model, StartApp view) {
        this.model = model;
        this.view = view;

        model.setObserver(this);
    }

    @Override
    public void SimulationStep() {
        view.uploadMap();
    }
}
