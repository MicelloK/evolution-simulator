# Vector2d

## Class description
This record class describe position at `WorldMap`

## Class structure
### Fields:
```java
int x;
int y;
```
    
### Methods:
```java
public String toString()
// this function returns String "(x,y)"

public Vector2d add(Vector2d other)
// this function returns result of addition two vectors

public boolean equals(Object other)
// this function checks if two objects are equal

public boolean precedes(Vector2d other)
// this function check if vector precedes other vector, it means x1 < x2 and y1 < y2

public boolean follows(Vector2d other)
// this function check if vector follows other vector, it means x1 >= x2 and y1 >= y2

@Override
public int hashCode()
```