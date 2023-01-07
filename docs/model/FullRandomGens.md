# FullRandomGens
## Class description
This class is responsible for full random genome mutation logic.
Implements `IGenome` interface.

## Class structure
### Fields:
```java
private final Random randomMachine;
private final int minimum;
private final int maximum;
```
### Methods:
To create new FullRandomGens use this constructor:
```java
public FullRandomGens(int minimum, int maximum)
// this constructor accepts minimum and maximum mutation numbers
```
To make genome mutation use this method:
```java
@Override
public void genomeMutation(int[] genotype)
```
