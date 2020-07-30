# Argumentor

[![Build Status](https://travis-ci.com/JFuerste/Argumentor.svg?token=DXxKCUnhUTNCqpsap9Wf&branch=master)](https://travis-ci.com/JFuerste/Argumentor)


Simple Java library that simplifies argument parsing for command line utilities

## Usage

### Example 
```Java
class Main{
  public static void main(String[] args){
    Argumentor argumentor = new Argumentor();
    // Step 1: Add all possible arguments
    argumentor.addBooleanArgument("v", "verbose"); // Makes arguments '-v' or '--verbose' possible
    // Step 2: Parse Arguments
    argumentor.parseArguments(args); 
    // Step 3: Get Values 
    boolean verboseMode = argumentor.getBooleanArgument("v");
  }
}
```

### Full Interface
```Java
void addBooleanArgument(String id, String... alternativeNames);
void addIntegerArgument(String id, String... alternativeNames);
void addDoubleArgument(String id, String... alternativeNames);
void addStringArgument(String id, String... alternativeNames);

parseArguments(String[] arguments);

boolean getBooleanArgument(String id);
Optional<double> getDoubleArgument(String id):
Optional<int> getIntegerArgument(String id);
Optional<String> getStringArgument(String id);
```

## Install
Install using ```./gradlew.bat install``` or (```gradle install``` if you have gradle installed)

## ToDo
- Arguments with multiple values e.g ```--imageSize 1920 1080```
- Informative output on parse error or ```--help flag```
