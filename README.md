# Video Game Menu

This simple video game menu provides a customizable user experience with features including
volume and brightness settings, player selection/creation, and save file selection.
Settings and player data are stored in automatically generated files, `.ini` and `.db` respectively, which are updated as changes are made.

## File Locations
- Settings: `%USERPROFILE%/.video-game-menu/settings.ini`
- Players: `%USERPROFILE%/.video-game-menu/players.db`

## Technologies Used
- [Java 16](https://www.oracle.com/java/technologies/javase-downloads.html)
- [JavaFX (as GUI)](https://openjfx.io/openjfx-docs/)
- [Maven (Build Tool)](https://maven.apache.org/)
- [Nitrite (as Database)](https://www.dizitart.org/nitrite-database.html)

## How to Run
Ensure you have Maven and Java 16 installed on your system. Then, run the following command in the terminal:

```sh
mvn javafx:run
```

## Dependencies
The application's dependencies are managed using Maven, and they are listed in the 'pom.xml' file. Key dependencies include:

- JavaFX (GUI framework)
- Nitrite (Database)
- TestFX (Testing framework)

For a detailed list of dependencies, please refer to the pom.xml file.