# PortalMoveAllowed
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
To check if position is on edge of map use:
```java
private boolean onEdge(Vector2d position, Vector2d lowerLeft, Vector2d upperRight)
```
To draw new position `newPosition()` method use:
```java
private Vector2d drawNewPosition(Vector2d upperRight)
```