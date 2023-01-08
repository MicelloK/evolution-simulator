package agh.oop.proj;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SimulationEngine implements Runnable {
    private final Settings settings;
    private final StatisticsWriter writer = new StatisticsWriter();
    private final AbstractWorldMap map;
    private final Statistics stats;
    private int currentDay;
    private int freePositionQuantity;
    private boolean isActive = false;
    private ISimulationObserver observer;

    public SimulationEngine(Settings settings) {
        this.settings = settings;
        map = settings.getMap();
        currentDay = 0;
        stats = new Statistics(this);
    }

    public void setObserver(ISimulationObserver observer) {
        this.observer = observer;
    }

    private void moveAnimals() {
        List<Animal> animals = new LinkedList<>();
        for (MapSquare square : map.elements.values()) {
            for (IMapElement element : square.getObjects()) {
                if (element.isAnimal()) {
                    animals.add(((Animal) element));
                }
            }
        }
        for (Animal animal : animals) {
            animal.move();
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
                } else if (currentAnimal.getLifeLength() > alfaAnimal.getLifeLength()) {
                    alfaAnimal = currentAnimal;
                } else if (currentAnimal.getChildren() > alfaAnimal.getChildren()) {
                    alfaAnimal = currentAnimal;
                }
            }
        }
        return alfaAnimal;
    }

    private Animal findAlfaFullAnimal(MapSquare square) {
        Animal alfaAnimal = null;
        for (IMapElement element : square.getObjects()) {
            if (element.isAnimal()) {
                Animal currentAnimal = (Animal) element;
                if (currentAnimal.getEnergy() < settings.getAnimalFullEnergy()) {
                    continue;
                }
                if (alfaAnimal == null) {
                    alfaAnimal = currentAnimal;
                }

                if (currentAnimal.getEnergy() > alfaAnimal.getEnergy()) {
                    alfaAnimal = currentAnimal;
                } else if (currentAnimal.getLifeLength() > alfaAnimal.getLifeLength()) {
                    alfaAnimal = currentAnimal;
                } else if (currentAnimal.getChildren() > alfaAnimal.getChildren()) {
                    alfaAnimal = currentAnimal;
                }
            }
        }
        return alfaAnimal;
    }

    private Animal findSecondAlfaFullAnimal(MapSquare square) {
        if (square.getObjects().size() < 2) return null;

        Animal alfa = findAlfaFullAnimal(square);
        Animal secondAlfaAnimal = null;
        for (IMapElement element : square.getObjects()) {
            if (element.isAnimal()) {
                Animal currentAnimal = (Animal) element;
                if (secondAlfaAnimal == null) {
                    secondAlfaAnimal = currentAnimal;
                }

                if (!currentAnimal.equals(alfa) && currentAnimal.getEnergy() >= settings.getAnimalFullEnergy()) {
                    if (currentAnimal.getEnergy() > secondAlfaAnimal.getEnergy()) {
                        secondAlfaAnimal = currentAnimal;
                    } else if (currentAnimal.getLifeLength() > secondAlfaAnimal.getLifeLength()) {
                        secondAlfaAnimal = currentAnimal;
                    } else if (currentAnimal.getChildren() > secondAlfaAnimal.getChildren()) {
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
            if (square.didGrassGrow() && alfaAnimal != null) {
                alfaAnimal.increaseEnergy();
                map.eatGrass(alfaAnimal.getPosition());
            }
        }
    }

    private void animalsReproduction() {
        for (MapSquare square : map.elements.values()) {
            Animal firstAnimal = findAlfaFullAnimal(square);
            Animal secondAnimal = findSecondAlfaFullAnimal(square);
            if (firstAnimal != null && secondAnimal != null) {
                new Animal(firstAnimal, secondAnimal, settings, currentDay);
                firstAnimal.newChildren();
                secondAnimal.newChildren();
            }
        }
    }

    private void growGrass() {
        map.growGrass(settings.getGrassPerDay());
    }

    public boolean isSimulationNotOver() {
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
        int x = random.nextInt(settings.getMapWidth());
        int y = random.nextInt(settings.getMapHeight());
        return new Vector2d(x, y);
    }

    private void initSimulation() {
        for (int i = 0; i < settings.getStartAnimalsQuantity(); i++) {
            new Animal(drawPosition(), settings, currentDay);
        }
        map.growGrass(settings.getStartGrassQuantity());
        System.out.println(settings.getMap().toString());
    }

    public void run() {
        if (currentDay == 0) {
            try {
                writer.setSettingsFile(settings.getName());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("start simulation:");
            initSimulation();
            observer.SimulationStep();
        }
        while (isSimulationNotOver() && isActive) {
            try {
                currentDay += 1;
                settings.getMap().updatePreferredPositions();
                moveAnimals();
                eatGrass();
                animalsReproduction();
                growGrass();
                observer.SimulationStep();
                writer.save(stats);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(settings.getMap().toString()); // visualization
        }
    }

    public Settings getSettings() {
        return settings;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public void changeStatus() {
        this.isActive = !this.isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getFreePositionQuantity() {
        return freePositionQuantity;
    }

    public void setFreePositionQuantity(int freePositionQuantity) {
        this.freePositionQuantity = freePositionQuantity;
    }

    public Statistics getStats() {
        return stats;
    }
}