package stonegame.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HighestScoreTableModelTest {

    private HighestScoreTableModel highestScoreTableModel;
    private static final String TEST_FILE_NAME = "TestHighestScoreTable.json";

    @BeforeEach
    public void setup() {
        ObjectMapper objectMapper = new ObjectMapper();
        highestScoreTableModel = new HighestScoreTableModel(objectMapper, TEST_FILE_NAME);
    }

    @Test
    void getTop5SortedPlayerStatistics_ValidFile_ReturnsTop5Players() {
                List<Map.Entry<String, Integer>> topPlayers = highestScoreTableModel.getTop5SortedPlayerStatistics();
                assertNotNull(topPlayers);
                assertEquals(3, topPlayers.size());

                assertEquals("PlayerA", topPlayers.get(0).getKey());
                assertEquals(2, topPlayers.get(0).getValue());

                assertEquals("PlayerC", topPlayers.get(1).getKey());
                assertEquals(2, topPlayers.get(1).getValue());

                assertEquals("PlayerB", topPlayers.get(2).getKey());
                assertEquals(1, topPlayers.get(2).getValue());

    }
}
