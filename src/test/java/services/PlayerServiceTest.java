package services;

import exceptions.UncompletedNameFieldException;
import exceptions.NameIsAlreadyUsedException;

import models.Player;

import org.apache.commons.io.FileUtils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;

public class PlayerServiceTest {

    private final String NAME = "Ezra";

    @BeforeAll
    static void beforeAll() {
        FileSystemService.APPLICATION_FOLDER = ".test-choose-your-own-adventure";
        FileSystemService.initDirectory();
    }

    @BeforeEach
    void setUp() throws Exception {
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        PlayerService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        PlayerService.closeDatabase();
    }

    @Test
    void testDatabaseIsInitializedAndNoPlayerIsPersisted() {
        assertThat(PlayerService.getAllPlayers()).isNotNull();
        assertThat(PlayerService.getAllPlayers()).isEmpty();
    }

    @Test
    void testPlayerIsAddedToDatabase() throws NameIsAlreadyUsedException, UncompletedNameFieldException {
        PlayerService.addPlayer(NAME);
        assertThat(PlayerService.getAllPlayers()).isNotEmpty();
        assertThat(PlayerService.getAllPlayers()).size().isEqualTo(1);

        Player player = PlayerService.getAllPlayers().get(0);
        assertThat(player).isNotNull();
        assertThat(player.getName()).isEqualTo(NAME);
    }

    @Test
    void testPlayerWithSameNameCanNotBeAddedTwice() {
        assertThrows(NameIsAlreadyUsedException.class, () -> {
            PlayerService.addPlayer(NAME);
            PlayerService.addPlayer(NAME);
        });
    }

    @Test
    void testNameAMEFieldIsCompleted() {
        assertThrows(UncompletedNameFieldException.class, () ->
                PlayerService.addPlayer(""));
        assertThrows(UncompletedNameFieldException.class, () ->
                PlayerService.addPlayer(" "));
        assertThrows(UncompletedNameFieldException.class, () ->
                PlayerService.addPlayer("   "));
        assertThrows(UncompletedNameFieldException.class, () ->
                PlayerService.addPlayer("          "));
    }

    @Test
    void testPlayerIsDeletedFromDatabase() throws NameIsAlreadyUsedException, UncompletedNameFieldException {
        PlayerService.addPlayer(NAME);
        assertThat(PlayerService.getAllPlayers()).size().isEqualTo(1);
        PlayerService.deletePlayer(NAME);
        assertThat(PlayerService.getAllPlayers()).size().isEqualTo(0);
    }

    @Test
    void testIDCheck() throws NameIsAlreadyUsedException, UncompletedNameFieldException {
        PlayerService.addPlayer(NAME);
        UUID id = PlayerService.getAllPlayers().get(0).getId();
        assertThat(PlayerService.checkIDisUnique(id)).isEqualTo(false);
    }


}
