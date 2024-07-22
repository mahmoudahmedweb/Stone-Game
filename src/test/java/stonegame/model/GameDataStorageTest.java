package stonegame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameDataStorageTest {

    GameDataStorage storage = new GameDataStorage();

    @BeforeEach
    void setUp() {
        storage = new GameDataStorage();
    }
    @Test
    void readGameDataSuccessfully() throws IOException {
        List<GameData> initialGameDataList = storage.readGameData("TestGameData.json");
        assertNotNull(initialGameDataList);
        assertEquals(2, initialGameDataList.size());

        assertEquals("PlayerA", initialGameDataList.get(0).getPlayer1Name());
        assertEquals("PlayerB", initialGameDataList.get(0).getPlayer2Name());
        assertEquals(10, initialGameDataList.get(0).getNumberOfTurnsOfPlayer1());
        assertEquals(8, initialGameDataList.get(0).getNumberOfTurnsOfPlayer2());
        assertEquals("PlayerA", initialGameDataList.get(0).getGameWinner());
        assertEquals("PlayerA", initialGameDataList.get(0).getLastPlayer());

        assertEquals("PlayerC", initialGameDataList.get(1).getPlayer1Name());
        assertEquals("PlayerD", initialGameDataList.get(1).getPlayer2Name());
        assertEquals(15, initialGameDataList.get(1).getNumberOfTurnsOfPlayer1());
        assertEquals(12, initialGameDataList.get(1).getNumberOfTurnsOfPlayer2());
        assertEquals("PlayerC", initialGameDataList.get(1).getGameWinner());
        assertEquals("PlayerD", initialGameDataList.get(1).getLastPlayer());

    }
    @Test
    void saveGameDataSuccessfully() throws IOException {
        String fileName = "TestSaveMethod.json";
        List<GameData> gameDataList = storage.readGameData(fileName);
        int initialSize = gameDataList.size();

        GameData gameData = new GameData("PlayerE", "PlayerF");
        storage.saveGameData(gameData, fileName);

        List<GameData> updatedGameDataList = storage.readGameData(fileName);

        assertNotNull(gameDataList);
        assertEquals(initialSize + 1, updatedGameDataList.size());
        assertEquals("PlayerE", updatedGameDataList.get(updatedGameDataList.size() - 1).getPlayer1Name());
        assertEquals("PlayerF", updatedGameDataList.get(updatedGameDataList.size() - 1).getPlayer2Name());

    }
}
