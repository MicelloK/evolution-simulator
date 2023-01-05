# MoveDirection
## Class description
This enum class stores possible IMapElement directions.

## Class structure
```java
public enum MoveDirection {
    NORTH,
    SOUTH,
    EAST,
    WEST,
    NORTH_EAST,
    SOUTH_EAST,
    SOUTH_WEST,
    NORTH_WEST;
```

### Methods:
You can represent Direction by the String using this method:
```java
@Override
public String toString()
```
To get next Direction use below method:
```java
public MoveDirection next()
```
To projecting MoveDirection value to the unit vector use this method:
```java
public Vector2d toUnitVector()
```
To get random Direction you can use this static method
```java
public static MoveDirection randomDirection()
```
