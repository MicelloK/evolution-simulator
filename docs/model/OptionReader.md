# OptionReader
## Class description
This class is responsible for reading and adding configuration to `CSV_FILE`.

Configurations will be saved in `src/main/resources/config.csv`

WARNING: `config.csv` should have empty line at the end.

## Class structure
### Fields:
```java
private static final String CSV_FILE = "src/main/resources/config.csv";
private static final String CSV_SPLIT_BY = ",";
```

### Methods:
To read options from `CSV_FILE` use static read method:
```java
public static List<String[]> read() throws FileNotFoundException
```
To add new configuration use static add method:
```java
public static void add(String name, String[] config) throws Exception
```
To find configuration by name use static find method:
```java
public static String[] find(String name) throws FileNotFoundException
```
To get all configurations names use names method:
```java
public static String[] names() throws FileNotFoundException
```