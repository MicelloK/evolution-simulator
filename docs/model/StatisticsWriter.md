# StatisticsWriter
## Class description
This class is responsible for writing statistics to the file.

Statistics will be saved in `src/main/statistics/file_name_currentDate`.

Before you use `save()` method make sure you set file name with `setSettingsFile(String name)`.

## Class structure
### Fields:
```java
private static String SETTINGS_FILE;
private static final String CSV_SPLIT_BY = ",";
```

### Methods:
To save current statistics in `SETTINGS_FILE` use save method:
```java
public void save(Statistics statistics) throws IOException
```

Before you use above function, set file to which you will save the data:
```java
public void setSettingsFile(String name)
```

Class has also private function used to parse statistics to String[]:
```java
private String[] statisticsParser(Statistics statistics) // -> String[9]
```