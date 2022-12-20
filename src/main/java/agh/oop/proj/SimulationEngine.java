package agh.oop.proj;

import java.util.Random;

public class SimulationEngine {
    private final Settings settings;
    private final AbstractWorldMap map;
    private int currentDay;

    public SimulationEngine(Settings settings) {
        this.settings = settings;
        map = settings.getMap();
        currentDay = 0;
    }

    private void moveAnimals() {
        for (MapSquare square : map.elements.values()) {
            for (IMapElement element : square.getObjects()) {
                if (element.isAnimal()) {
                    ((Animal) element).move();
                }
            }
        }
    }

    private Animal findAlfaAnimal(MapSquare square) {
        Animal alfaAnimal = null;
        for (IMapElement element : square.getObjects()) {
            if (element.isAnimal()) {
                Animal currentAnimal = (Animal) element;
                if (alfaAnimal == null) {
                    alfaAnimal = currentAnimal;
                }
                if (currentAnimal.getEnergy() > alfaAnimal.getEnergy()) {
                    alfaAnimal = currentAnimal;
                }
                else if (currentAnimal.getLife() > alfaAnimal.getLife()) {
                    alfaAnimal = currentAnimal;
                }
                else if (currentAnimal.getChildren() > alfaAnimal.getChildren()) {
                    alfaAnimal = currentAnimal;
                }
            }
        }
        return alfaAnimal;
    }

    private Animal findSecondAlfaAnimal(MapSquare square) {
        Animal alfa = findAlfaAnimal(square);
        Animal secondAlfaAnimal = null;
        for (IMapElement element : square.getObjects()) {
            if (element.isAnimal()) {
                Animal currentAnimal = (Animal) element;
                if (secondAlfaAnimal == null) {
                    secondAlfaAnimal = currentAnimal;
                }
                if (!secondAlfaAnimal.equals(alfa)) {
                    if (currentAnimal.getEnergy() > secondAlfaAnimal.getEnergy()) {
                        secondAlfaAnimal = currentAnimal;
                    }
                    else if (currentAnimal.getLife() > secondAlfaAnimal.getLife()) {
                        secondAlfaAnimal = currentAnimal;
                    }
                    else if (currentAnimal.getChildren() > secondAlfaAnimal.getChildren()) {
                        secondAlfaAnimal = currentAnimal;
                    }
                }
            }
        }
        return secondAlfaAnimal;
    }

    private void eatGrass() {
        for (MapSquare square : map.elements.values()) {
            Animal alfaAnimal = findAlfaAnimal(square);
            if (alfaAnimal != null) {
                alfaAnimal.increaseEnergy(settings.getEatingGrassEnergy());
                map.eatGrass(alfaAnimal.getPosition());
            }
        }
    }

    private void animalsReproduction() {
        for (MapSquare square : map.elements.values()) {
            Animal firstAnimal = findAlfaAnimal(square);
            Animal secondAnimal = findSecondAlfaAnimal(square);
            if (firstAnimal != null && secondAnimal != null) {
                new Animal(firstAnimal, secondAnimal, settings);
            }
        }
    }

    private void growGrass() {
        map.growGrass(settings.getGrassPerDay());
    }

    private boolean isSimulationNotOver() {
        for (MapSquare square : map.elements.values()) {
            for (IMapElement element : square.getObjects()) {
                if (element.isAnimal() && !((Animal) element).isDead()) {
                    return true;
                }
            }
        }
        return false;
    }

    private Vector2d drawPosition() {
        Random random = new Random();
        int x = random.nextInt() % settings.getMapWidth();
        int y = random.nextInt() % settings.getMapHeight();
        return new Vector2d(x, y);
    }

    private void initSimulation() {
        for (int i = 0; i < settings.getStartAnimalsQuantity(); i++) {
            // MOZLIWA ZMIANA ARGUMENTOW
            new Animal(drawPosition(), settings);
        }
        map.growGrass(settings.getStartGrassQuantity());
    }

    public void run() {
        initSimulation();
        while (isSimulationNotOver()) {
            currentDay += 1;
            moveAnimals();
            eatGrass();
            animalsReproduction();
            growGrass();
        }
    }

    public int getCurrentDay() {
        return currentDay;
    }
}
