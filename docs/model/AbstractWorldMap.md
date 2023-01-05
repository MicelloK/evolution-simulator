# AbstractWorldMap
## Class description
This abstract class is responsible for storage and update information about simulation map.
AbstractWorldMap implements `IWorldMap` and `IElementChangeObserver` interfaces.

## Class structure
### Fields:
```java
protected final Map<Vector2d, MapSquare> elements;
private int animalsNumber;
private int grassNumber;
private int animalsDead;
private int lifeOfDeadAnimal;
protected final int mapSize;
private final Vector2d lowerLeft;
private final Vector2d upperRight;
private final IMoveAllowed movementDetails;
private final int reproductionEnergy;
protected final List<Vector2d> preferredPositions;
protected List<Vector2d> emptyPreferred;
protected List<Vector2d> emptyNotPreferred;
protected final ArrayList<Animal> animalsList;
```

### Methods:
You can get access to the fields with getters:
```java
public List<Vector2d> getPreferred()

public List<Vector2d> getNotPreferred()

public int getAnimalsNumber()

public int getGrassNumber()

public Map<Vector2d, MapSquare> getElements()

public int getAnimalsDead()

public int getLifeOfDeadAnimal()
```
You can also change some values:
```java
private void increaseAnimalsDead()

public void increaseLifeOfDeadAnimal(Animal animal)
```
If you create new map AbstractWorldMap constructor is called:
```java
protected AbstractWorldMap(int width, int height, IMoveAllowed movementDetails, int reproductionEnergy)
```
AbstractWorldMap has abstract method used to update preferred positions:
```java
abstract void updatePreferredPositions()
```
Methods implement from IElementChangeObserver interface:
```java
@Override
public void positionChanged(Vector2d oldPosition, Vector2d newPosition, IMapElement object)
// this function move object from oldPosition to newPosition

@Override
public void animalDies(IMapElement animal)
// this function is responsible for removing dead animal
```
Other methods:
```java
protected boolean isEmptySquares()
// function returnes true if thera are empty squares on map

protected Vector2d drawPosition()
// function draw and return random position (80% from emptyPreferred, 20% from emptyNotPreferred)

public boolean inMap(Vector2d position)
// function check if map contains given position

public Vector2d newPosition(Vector2d oldPosition, Vector2d newPosition)
// function returns new positions depends on movementDetails config

public int moveEnergyLost(Vector2d position)
// function returns quantity of energy lost on move to given position

@Override
public void place(IMapElement object)
// function place given object on map

private void addGrass(Vector2d position)
// function add grass on given a position

private void deleteGrass(Vector2d position)
// function delete grasss from given a position

private boolean isGrass(Vector2d position)
// function returns true if grass has grown on given a position

public void eatGrass(Vector2d position)
// function call deleteGrass(position) if there is grass

public void growGrass(int grassPerDay)
// function add 'grassPerDay' amount of grass to map

public String toString()
// function using MapVisualiser returns String which represent current map status

private void initMap(int width, int height)
// function used in class constructor to init rectangle width * height map
```
