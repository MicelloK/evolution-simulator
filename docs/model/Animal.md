# Animal
## Class description
This class is responsible for storage and update information about animal.
Implements IMapElement interface.

Creating new `Animal` automatically place it on the map.

## Class structure
### Fields:
```java
private IElementChangeObserver observer;
private final AbstractWorldMap map;
private Vector2d position;
private int energy;
private int children;
private int howManyGrassEat;
private final int createdDay;
private int lifeLength;
private final int imageIdx;
private MoveDirection orientation;
private final Genome genotype;
private final Settings settings;
```

### Methods:
You can get access to the fields with getters:
```java
public int getEnergy()

public int getLifeLength()

public int getDeathDay()

public int getChildren()

public int[] getGenotype()

public int getActiveGenome()

public int getActiveGenomeIdx()

public boolean isDead()

public int getHowManyGrassEat()

@Override
public Vector2d getPosition()

@Override
public MoveDirection getOrientation()

@Override
public int getImageIdx()

@Override
public boolean isAnimal()
```
You can also change some values:
```java
public void loseEnergy(int energy)

public void increaseEnergy()

public void increaseChildrenQuantity()

public void setActiveGenomeIdx(int currGen)

public void setPosition(Vector2d newPosition)

public void setObserver(IElementChangeObserver observer)
```
To create new Animal instance you can use one of two constructors:
```java
public Animal(Vector2d position, Settings settings, int createdDay)
// constructor used to create new animal

public Animal(Animal parentTwo, Animal parentOne, Settings settings, int createdDay)
// constructor used to create new animal as a result of reproduction
```
Other methods:
```java
public void move()
// function used to move animal to the next position

public void changerPosition()
// function responsible for set new orientation and make move, used in AnimalMoving.moving() method

public void positionChanged(Vector2d oldPosition, Vector2d newPosition)
// function responsible for informing the observer that animal made move
```