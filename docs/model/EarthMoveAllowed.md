# EarthMoveAllowed
## Class description
This class implements `IMoveAllowed` interface. Is responsible for "earth move allowed" logic.

## Class structure
To get new position use this method:
```java
@Override
public Vector2d newPosition(Vector2d oldPosition, Vector2d newPosition, Vector2d lowerLeft, Vector2d upperRight)
```
To check if animal can move to position use:
```java
@Override
public boolean canMoveTo(Vector2d position, Vector2d lowerLeft, Vector2d upperRight)
```
To get energy lost when move was taken use:
```java
@Override
public int lotsEnergy(Vector2d position, Vector2d lowerLeft, Vector2d upperRight, int EnergyNeededToReproduction)
```
Methods to check on which side given position is:
```java
private boolean onRightSide(Vector2d position, Vector2d lowerLeft, Vector2d upperRight)

private boolean onLeftSide(Vector2d position, Vector2d lowerLeft, Vector2d upperRight)
```