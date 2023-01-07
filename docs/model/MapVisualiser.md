# MapVisualiser
## Class description
This class is responsible for draw simply console map visualisation.

`.draw()` method contains `Thread.sleep()` instruction. Default value is set on 900 ms.

## Class structure
### Fields:
```java
private static final String EMPTY_CELL = " ";
private static final String GRASS_CELL = "*";
private static final String PREFERRED_CELL = "P";
private static final String CELL_SEGMENT = "|";
private final AbstractWorldMap map;
```

### Methods:
To create new MapVisualiser instance use this constructor:
```java
public MapVisualiser(AbstractWorldMap map)
```
To draw map use this method:
```java
public String draw(Vector2d lowerLeft, Vector2d upperRight)
```
