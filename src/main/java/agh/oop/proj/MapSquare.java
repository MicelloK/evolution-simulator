package agh.oop.proj;

import java.util.ArrayList;
import java.util.List;

public class MapSquare {
    private boolean grass;
    private final ArrayList<IMapElement> objects;
    private int deathCounter;

    public MapSquare() {
        this.grass = false;
        this.objects = new ArrayList<>();
        this.deathCounter = 0;
    }

    public boolean didGrassGrow() {
        return grass;
    }

    public void growGrass() {
        grass = true;
    }

    public void eatGrass() {
        grass = false;
    }

    private void increaseDeathCounter() {
        deathCounter += 1;
    }

    public int getDeathCounter() {
        return deathCounter;
    }

    public List<IMapElement> getObjects() {
        return objects;
    }

    public void removeObject(IMapElement object) {
        objects.remove(object);
    }

    public void placeObject(IMapElement object) {
        objects.add(object);
    }

    public void animalDie(IMapElement animal) {
        removeObject(animal);
        increaseDeathCounter();
    }
}
