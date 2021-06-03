package services;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileSystemService {

    public static String APPLICATION_FOLDER = ".video-game-menu";
    private static final String USER_FOLDER = System.getProperty("user.home");
    private static final File settingsFile = new File(String.valueOf(getPathToFile("GameTitle.ini")));

    public static Path getPathToFile(String... path) {
        return getApplicationHomeFolder().resolve(Paths.get(".", path));
    }

    @NotNull
    public static Path getApplicationHomeFolder() {
        return Paths.get(USER_FOLDER, APPLICATION_FOLDER);
    }

    public static void initDirectory() {
        Path applicationHomePath = getApplicationHomeFolder();
        if (!Files.exists(applicationHomePath)) {
            boolean wasSuccessful;
            wasSuccessful = applicationHomePath.toFile().mkdirs();
            if (!wasSuccessful) {
                System.out.println("was not successful.");
            }
        }
    }

    public static void getSettings() {
        try {
            Scanner myReader = new Scanner(settingsFile);
            String separator = "=";
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.startsWith("[") || data.startsWith(";") || data.isEmpty()) continue;
                int index = data.indexOf(separator);
                int value = Integer.parseInt(data.substring(index + 1));
                data = data.substring(0 , index);
                switch (data) {
                    case "Brightness":
                        DisplayService.setBrightness(value);
                        break;
                    case "Volume":
                        AudioService.setVolume(value);
                        break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException ignored) { }
    }

    public static void saveSettings() {
        try {
            FileWriter myWriter = new FileWriter(settingsFile);
            myWriter.write( DisplayService.getSettings() + "\n" +
                                AudioService.getSettings());
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
