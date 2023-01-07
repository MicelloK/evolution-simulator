# CorpsesMap
## Class description
This class extends from `AbstractWorldMap`. Is responsible for CorpsesMap preferred positions logic.

## Class structure
To create new map use class constructor:
```java
protected CorpsesMap(int width, int height, IMoveAllowed movementDetails, int reproductionEnergy)
```
This constructor called `super(width, height, movementDetails, reproductionEnergy)` and set emptyPreferred and emptyNotPreferred positions.

Other methods:
```java
@Override
protected void updatePreferredPositions()

private void updateEmptyPositions()
```