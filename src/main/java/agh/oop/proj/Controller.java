package agh.oop.proj;

import agh.oop.proj.gui.StartApp;

public class Controller implements ISimulationObserver {
    private final StartApp view;

    public Controller(SimulationEngine model, StartApp view) {
        this.view = view;
        model.setObserver(this);
    }

    @Override
    public void SimulationStep() {
        view.uploadMap();
    }
}
