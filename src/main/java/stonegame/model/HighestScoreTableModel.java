package stonegame.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The {@code HighestScoreTableModel} class represents a model for managing highest score statistics in the game.
 * It loads player statistics from a file and provides methods to retrieve the top 5 players with the highest scores.
 */
public class HighestScoreTableModel {

    private static final Logger logger = LogManager.getLogger();

    private final Map<String, Integer> playerStatistics;
    private final ObjectMapper objectMapper;

    /**
     * Constructs a {@code HighestScoreTableModel} with the specified object mapper and file name.
     *
     * @param objectMapper The object mapper to be used for JSON serialization and deserialization.
     * @param fileName     The name of the file containing player statistics.
     */
    public HighestScoreTableModel(ObjectMapper objectMapper, String fileName) {
        this.objectMapper = objectMapper;
        this.playerStatistics = loadPlayerStatisticsFromFile(fileName);
    }

    private Map<String, Integer> loadPlayerStatisticsFromFile(String fileName) {
        Map<String, Integer> playerStatistics = new HashMap<>();
        try {
            InputStream inputStream = getClass().getResourceAsStream("/" + fileName);
            if (inputStream != null && inputStream.available() > 0) {
                List<GameData> gameDataList = objectMapper.readValue(inputStream, new TypeReference<>() {});

                for (GameData gameData : gameDataList) {
                    String gameWinner = gameData.getGameWinner();
                    if (gameWinner != null && !gameWinner.isEmpty()) {
                        playerStatistics.put(gameWinner, playerStatistics.getOrDefault(gameWinner, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            logger.error("An Error occurred while loading player statistics from file", e);
        }
        return playerStatistics;
    }

    /**
     * Retrieves the top 5 players with the highest scores.
     *
     * @return A list of map entries representing the top 5 players and their scores.
     */
    public List<Map.Entry<String, Integer>> getTop5SortedPlayerStatistics() {
        return playerStatistics.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .collect(Collectors.toList());
    }
}
