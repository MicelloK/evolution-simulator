# Simulation Engine

## Class description
This class is responsible for all simulation processes.

If you want to create new simulation model you should create new class instance and call `.run()` method.

## Class structure

### Fields:
```java
private final Settings settings;
private final Statistic writer;
private final AbstractWorldMap;
private final Statistics stats;
private final currentDay;
private final freePositionQuantity;
private boolean isActive;
private ISimulationObserver observer;
```

### Methods:
You can get access to the fields with getters:
```java
public Settings getSettings()

public int getCurrentDay()

public int getFreePositionQuantity()

public Statistics getStats()

public boolean isActive()
```

You can also change some values:
```java
public void changeStatus()
// this method change isActive status to the opposite

public void setFreePositionQuantity(int freePositionQuantity)
```

To create new simulation use class constructor
```java
public SimulationEngine(Settings settings)
```

If you want set gui simulation `observer` you can use 
```java
public void setObserver(ISimulationObserver observer)
```

You can check if simulation is over with
```java
public boolean isSimulationNotOver()
```

To run `SimulationEngine` use
```java
public void run()
```

Other private methods:
```java
private coid initSimulation()
// this init map at the begining of simulation

private void moveAnimals()
// this function move animals in the simulation step

private void eatGrass()
// this function is responsible for call suitable animals to eat grass

private void animalsReproduction()
// this function is responsible for animals reproduction

private void growGrass()
// this function is responsible for grass growing

private Vector2d drawPosition()
// this function return random position from the appropriate range

private Animal findAlfaAnimal(MapSquare square)
// finds alfa animal on given square

private Animal findAlfaFullAnimal(MapSquare square)
// finds alfa animal which is ready to reproduction on given square

private Animal findSecondAlfaFullAnimal(MapSquare square)
// finds second alfa animal which is ready to reproduction on given square
// if above functions did not find an animal returns `null`

```

