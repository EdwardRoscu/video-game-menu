# video-game-menu

## General Description

This is a simple video game menu that provides:

- customizable settings for volume and brightness
- the ability to choose or create a player (In Progress)
- the ability to choose a save file to play (In Progress)

Settings are stored inside an automatically generated .ini file
Players are stored inside an automatically generated .db file
Both are updated when changes are made and can be find at: 

```
%USERPROFILE%/.video-game-menu
```

## Technologies Used
- [Java 16](https://www.oracle.com/java/technologies/javase-downloads.html)
- [JavaFX (as GUI)](https://openjfx.io/openjfx-docs/)
- [Maven (Build Tool)](https://maven.apache.org/)
- [Nitrite (as Database)](https://www.dizitart.org/nitrite-database.html)

## How to run

To run this application, use the following command:

```
mvn javafx:run
```
