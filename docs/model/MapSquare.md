# MapSquare
## Class describe
This class is responsible for storage and update information about map square status.

## Class structure
### Fields:
```java
private final ArrayList<IMapElement> objects;
private boolean grass;
private int deathCounter;
```

### Methods:
You can get access to the fields with getters:
```java
public boolean didGrassGrow()

public int getDeathCounter()

public List<IMapElement> getObjects()
```
You can also change some values:
```java
public void growGrass()

public void eatGrass()

private void increaseDeathCounter()

public void removeObject(IMapElement object)

public void placeObject(IMapElement object)
```
To create new MapSquare use class constructor:
```java
public MapSquare()
```
To remove animal from square use animalDie method:
```java
public void animalDie(IMapElement animal)
```
