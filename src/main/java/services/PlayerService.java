package services;

import exceptions.UncompletedNameFieldException;
import exceptions.NameIsAlreadyUsedException;

import models.Player;
import models.Save;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

import static services.FileSystemService.getPathToFile;

public class PlayerService {

    private static ObjectRepository<Player> playerRepository;
    private static Nitrite database;

    public static void initDatabase() {
        FileSystemService.initDirectory();
        database = Nitrite.builder()
                .filePath(getPathToFile("players.db").toFile())
                .openOrCreate("test", "test");

        playerRepository = database.getRepository(Player.class);
    }

    public static void closeDatabase() {
        database.close();
    }

    public static void addPlayer(String name) throws NameIsAlreadyUsedException, UncompletedNameFieldException {
        uncompletedNameField(name);
        nameIsAlreadyUsed(name);

        Player player = new Player(name);
        UUID id = player.getId();
        while (!checkIDisUnique(id)) {
            id = player.rand_UUID();
            checkIDisUnique(id);
        }
        playerRepository.insert(player);
        updateCurrent(name);
    }

    public static void deletePlayer(String name) {
        for (Player player : playerRepository.find()) {
            if (Objects.equals(name, player.getName())) {
                playerRepository.remove(player);
                break;
            }
        }
    }

    public static ArrayList<Player> getAllPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        for (Player player : playerRepository.find()) {
            players.add(player);
        }
        return players;
    }

    public static Player getCurrentPlayer() {
        for (Player player : playerRepository.find()) {
            if (player.isCurrent())
                return player;
        }
        return null;
    }

    public static void addSave(String name, Save save, int index) {
        for (Player player : playerRepository.find()) {
            if (Objects.equals(player.getName(), name)) {
                player.addSave(save, index);
                playerRepository.update(player);
                break;
            }
        }
    }

    private static void updateCurrent(String name) {
        for (Player player : playerRepository.find()) {
            player.setCurrent(Objects.equals(player.getName(), name));
            playerRepository.update(player);
        }
    }







    public static boolean checkIDisUnique(UUID u) {
        Cursor<Player> cursor = playerRepository.find();
        for (Player player : cursor) {
            if (u.equals(player.getId()))
                return false;
        }
        return true;
    }

    public static void uncompletedNameField(String name) throws UncompletedNameFieldException {
        Pattern pattern = Pattern.compile("[\\S+]");
        if (!pattern.matcher(name).find())
            throw new UncompletedNameFieldException();
    }

    private static void nameIsAlreadyUsed(String name) throws NameIsAlreadyUsedException {
        for (Player player : playerRepository.find()) {
            if (Objects.equals(name, player.getName()))
                throw new NameIsAlreadyUsedException(name);
        }
    }


}
