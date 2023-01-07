# LittleCorrectionGens
## Class description
This class is responsible for little correction genome mutation logic.
Implements `IGenome` interface.

## Class structure
### Fields:
```java
private final int[] CORRECT = {-1, 1};
private final Random randomMachine;
```

### Methods:
To make genome mutation use this method:
```java
@Override
public void genomeMutation(int[] genotype)
```