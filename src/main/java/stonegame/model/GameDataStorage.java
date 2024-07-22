package stonegame.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code GameDataStorage} class handles the storage and retrieval of game data.
 * It provides methods to save game data to a file and read game data from a file.
 */
public class GameDataStorage {
    private static final Logger logger = LogManager.getLogger();
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Saves the provided {@link GameData} object to a file with the given file name.
     *
     * @param game     The game data to be saved.
     * @param fileName The name of the file to save the game data.
     */
    public void saveGameData(GameData game, String fileName) {
        try {
            List<GameData> gameDataList = readGameData(fileName);
            gameDataList.add(game);
            writeGameData(gameDataList, fileName);
            logger.info("Game data has been saved successfully.");
        } catch (IOException e) {
            logger.error("Game data cannot be saved! An Error occurred.", e);
        }
    }

    List<GameData> readGameData(String fileName) throws IOException {
        List<GameData> gameDataList;
        InputStream inputStream = getClass().getResourceAsStream("/" + fileName);
        if (inputStream != null && inputStream.available() > 0) {
            gameDataList = objectMapper.readValue(inputStream, new TypeReference<>() {
            });
        } else {
            gameDataList = new ArrayList<>();
        }
        if (inputStream != null) {
            inputStream.close();
        }
        return gameDataList;
    }

    private void writeGameData(List<GameData> gameDataList, String fileName) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(new File(getClass().getResource("/" + fileName).toURI()))) {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(outputStream, gameDataList);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
