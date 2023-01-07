# Settings
## Class description
This class is responsible for storage simulation configuration. You use instance of this class in SimulationEngine constructor.

## Class structure

### Fields:
```java
private final String name;
private final int mapWidth;
private final int mapHeight; 
private final int startGrassQuantity; 
private final int eatingGrassEnergy;
private final int grassPerDay; 
private final int startAnimalsQuantity;
private final int startAnimalEnergy;
private final int animalFullEnergy;
private final int reproductionLostEnergy;
private final int genLength;

private final AbstractWorldMap map;
private final IMove animalMoving;
private final IGenome mutationVariant;
```

### Methods:

You can get access to the fields with getters:
```java
public String getName() {
    return name;
}

public int getMapWidth() {
    return mapWidth;
}

public int getMapHeight() {
    return mapHeight;
}

public int getStartGrassQuantity() {
    return startGrassQuantity;
}

public int getEatingGrassEnergy() {
    return eatingGrassEnergy;
}

public int getGrassPerDay() {
    return grassPerDay;
}

public int getStartAnimalsQuantity() {
    return startAnimalsQuantity;
}

public int getStartAnimalEnergy() {
    return startAnimalEnergy;
}

public int getAnimalFullEnergy() {
    return animalFullEnergy;
}

public int getReproductionLostEnergy() {
    return reproductionLostEnergy;
}

public int getGenLength() {
    return genLength;
}

public AbstractWorldMap getMap() {
    return map;
}

public IMove getAnimalMoving() {
    return animalMoving;
}

public IGenome getMutationVariant() {
    return mutationVariant;
}
```

If you want to create new Settings instance use constructor:
```java
public Settings(String configName, String[] config)
```
This constructor accepts String `configName` and String[] `config` arguments.
`configName` can not contain commas, start or end with white sign or be empty.

config structure:
* `config[0]` - map width
* `config[1]` - map height
* `config[2]` - start grass quantity
* `config[3]` - energy from eating grass
* `config[4]` - grass grown per day
* `config[5]` - start animals quantity
* `config[6]` - start animals energy
* `config[7]` - full animal energy
* `config[8]` - reproduction energy cost
* `config[9]` - minimal mutation number
* `config[10]` - maximal mutation number
* `config[11]` - gen length
* `config[12]` - animal moving
* `config[13]` - mutation variant
* `config[14]` - movement details
* `config[15]` - map

Settings data correctness:
* `map width` Integer, > 0
* `map height` Integer, > 0
* `start grass quantity` Integer, > 0 and <= `mapWidth` * `mapHeight`
* `energy from eating grass` Integer, >= 0 
* `grass grown per day` Integer, >= 0
* `start animals quantity` Integer, > 0
* `start animals energy` Integer, > 0
* `full animal energy` Integer, >= 0
* `reproduction energy cost` Integer, >= 0
* `minimal mutation number` Integer, >= 0
* `maximal mutation number` Integer, >= `minimalMutationNumber`
* `gen length` Integer, > 0
* `animalMoving` String ("Predestination", "Craziness")
* `mutationVariant` String ("Correction", "Random")
* `movementDetails` String ("Earth", "Portal")
* `map` String ("Equators", "Corpses")