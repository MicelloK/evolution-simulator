# LittleCrazinessMove
## Class description
This class is responsible for animal "little craziness" moving.
Implements `IMove` interface.

## Class structure
### Fields:
```java
private final Random random;
```
### Methods:
To move animal use this method:
```java
@Override
public void moving(Animal animal)
```
To update active genome `moving()` method use:
```java
private void updateActiveGen(Animal animal)
```