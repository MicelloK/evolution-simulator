# Genome
## Class description
This class is responsible for storage and update animal genome.

## Class structure
### Fields:
```java
private final int[] animalGenotype;
private final Random random;
private int activeGenome;
```

### Methods:
You can get access to the fields with getters:
```java
public int[] getAnimalGenotype()

public int getActiveGenomeAnimal()
```
You can also set active genome value:
```java
public void setActiveGenome(int activeGenome)
```
To create new Genome instance you can use one of two constructors:
```java
public Genome(Settings settings)
// constructor used to create new Genome with settings

public Genome(Animal parentOne, Animal parentTwo, Settings settings)
// constructor used to create new Genome as a result of reproduction
```