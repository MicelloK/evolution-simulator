# Statistics

## Class description
This class is responsible for storage and calculate current simulation statistics

If you want to get current statistic make sure you do not forget use `updateStats()` method.

## Class structure

### Fields:
```java
private final AbstractWorldMap map;
private int numberAnimals;
private int numberGrass;
private int numberDeadAnimals;
private int worldDays;
private double avgLife;
private double avgEnergy;
private int freePositionQuantity;
private double avgChildren;
private int dominantGenotype;
private final SimulationEngine engine;
```

### Methods:
You can get access to the fields with getters:
```java
public int getNumberAnimals() {
    return numberAnimals;
}

public int getNumberGrass() {
    return numberGrass;
}

public int getNumberDeadAnimals() {
    return numberDeadAnimals;
}

public int getWorldDays() {
    return worldDays;
}

public double getAvgLife() {
    return avgLife;
}

public double getAvgEnergy() {
    return avgEnergy;
}

public int getFreePositionQuantity() {
    return freePositionQuantity;
}

public double getAvgChildren() {
    return avgChildren;
}

public int getDominantGenotype() {
    return dominantGenotype;
}
```

To create new Statistics instance use constructor:
```java
public Statistics(SimulationEngine engine)
```

If you need update simulation stats use update method:
```java
public void updateStats()
```

Other private methods:
```java
private void findDominant()
// function used to find dominant genotype

private void calculateAvgLifeLength()
// function used to calculate average animals life length

private void calculateAvgEnergy()
// function used to calculate average animals energy

private void calculateAvgChildren()
// funtion used to calculate average children quantity
```