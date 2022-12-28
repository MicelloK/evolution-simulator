package agh.oop.proj;

import agh.oop.proj.gui.App;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SimulationEngine implements Runnable{


    private final Settings settings;
    private final AbstractWorldMap map;
    private int currentDay;

    private final List<Animal> animals = new LinkedList<>();

    private boolean active = false;

    private App app;
    public SimulationEngine(Settings settings, App app) {
        this.app = app;
        this.settings = settings;
        map = settings.getMap();
        currentDay = 0;
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
                } else if (currentAnimal.getLife() > alfaAnimal.getLife()) {
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
                } else if (currentAnimal.getLife() > alfaAnimal.getLife()) {
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
                    } else if (currentAnimal.getLife() > secondAlfaAnimal.getLife()) {
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
        int x = random.nextInt(settings.getMapWidth());
        int y = random.nextInt(settings.getMapHeight());
        return new Vector2d(x, y);
    }

    public void initSimulation() {
        for (int i = 0; i < settings.getStartAnimalsQuantity(); i++) {
            Animal animal = new Animal(drawPosition(), settings, currentDay);
            animals.add(animal);
        }
        map.growGrass(settings.getStartGrassQuantity());
        System.out.println(settings.getMap().toString());
    }

    public void run() {
        System.out.println(settings.getMap().toString());
        System.out.println("start simulation:");
        if(currentDay == 0){
            initSimulation();
            app.uploadMap();
        }
        while (isSimulationNotOver() && active) {
            try{
                currentDay += 1;
                settings.getMap().updatePreferredPositions();
                moveAnimals();
                eatGrass();
                animalsReproduction();
                growGrass();
                app.uploadMap();
                Thread.sleep(500);

            }  catch (InterruptedException ex) {
                System.out.println(ex);
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

    public void changeStatus(){
        this.active = !this.active;
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}