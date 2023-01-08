# Graphical user interface

## Classes

### App
This class is responsible for display configuration selection stage. Extends `Application`

Public methods:
```java
@Override
public void start(Stage primaryStage) throws Exception

@Override
public void init() throws IOException
```

### StartApp
This class is responsible for create and display simulation

Public methods:
```java
public StartApp(Settings parameters) throws FileNotFoundException

public void updateInfo()

public void uploadMap()

public int getDominantGenotype()

public void setFollowingAnimal(Animal followingAnimal)
```

### Images
This class is responsible for loading images

### CreateMap
This class is responsible for create and update simulation map

Public methods:
```java
public CreateMap(SimulationEngine engine, Stage stage, StartApp app)

public void createMap()

public GridPane getGridPane()
```

### ChartsStatistic
This class is responsible for updating charts

Public methods:
```java
public ChartsStatistic(String string)

public LineChart getChart()

public void updateAnimalsChart(Statistics stats)

public void updateGrassChart(Statistics stats)

public void updateAvgEnergy(Statistics stats)

public void updateAvgLifeLength(Statistics stats)

public void updateAvgChildren(Statistics stats)

public void updateFreePosition(Statistics stats)

public void updateDeathAnimals(Statistics stats)
```

### StageWithCharts
This class is responsible for display charts stage

Public methods:
```java
public StageWithCharts(Stage mainStage, SimulationEngine engine)

public void updateCharts()

public void chartsShow()
```

### GetDataStage
This class is responsible for create and display data stage

Public methods:
```java
public GetDataStage()
```

### ElementBox
This class is responsible for storage information about animal energy

Public methods:
```java
public ElementBox(IMapElement element, SimulationEngine engine)

public ProgressBar energyInAnimal()
```

### ElementInformation
This class is responsible for display information about selected animal

Public methods:
```java
public ElementInformation(Stage MainStage, StartApp app)

public void creativeInfo(Animal animal)
```
