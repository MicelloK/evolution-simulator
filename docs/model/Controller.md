# Controller
## Class description
This class is responsible for communication model and view. 
Implements ISimulationObserver interface.

## Class structure
### Fields:
```java
private final StartApp view;
```

### Methods:
To create new Controller instance use this contractor:
```java
public Controller(SimulationEngine model, StartApp view)
```
Controller observe simulation model by:
```java
@Override
public void SimulationStep()
```