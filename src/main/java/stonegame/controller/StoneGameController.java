package stonegame.controller;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import stonegame.game.State;
import stonegame.model.GameData;
import stonegame.model.Square;
import stonegame.model.StoneGameModel;
import stonegame.model.StonePlacement;

public class StoneGameController {
    @FXML
    GridPane board;

    private StackPane[][] squares;
    private StoneGameModel model = new StoneGameModel();
    private static final Logger logger = LogManager.getLogger();
    private GameData gameData;
    public void setGameModel(StoneGameModel model) {
        this.model = model;
        this.gameData = model.getGameData();
    }

    @FXML
    private void initialize() {
        squares = new StackPane[model.BOARD_SIZE][model.BOARD_SIZE];
        for (int row = 0; row < model.BOARD_SIZE; row++) {
            for (int col = 0; col < model.BOARD_SIZE; col++) {
                StackPane square = createSquare(row, col);
                board.add(square, col, row);
                squares[row][col] = square;
            }
        }
    }

    private StackPane createSquare(int row, int col) {
        StackPane square = new StackPane();
        square.getStyleClass().add("square");
        square.setOnMouseClicked(mouseEvent -> handleMouseClick(square, row, col));
        return square;
    }

    private void handleMouseClick(StackPane square, int row, int col) {
        if (!model.isGameOver()) {
            logger.info("Click on square ({}, {})", row, col);
            Square squareValue = model.getSquare(row, col);
            State.Player currentPlayer = model.getCurrentPlayer();

            if (squareValue == Square.NONE) {
                StonePlacement stonePlacement = new StonePlacement(row, col, currentPlayer);

                try {
                    model.makeMove(stonePlacement);
                    squareValue = currentPlayer == State.Player.PLAYER_1 ? Square.PLAYER1 : Square.PLAYER2;
                    String stoneStyle = "-fx-background-color: " + changeColorOfStones(squareValue) + ";";
                    square.setStyle(stoneStyle);

                    if (model.isGameOver()) {
                       handleGameEnd();
                    }
                } catch (IllegalArgumentException | IllegalStateException e) {
                    // Handle illegal moves or game already over
                    showAlert(e.getMessage());
                }
            }
        }
        logger.info(model.printBoard());
    }

    private void handleGameEnd() {
        String gameWinner = model.getWinnerName();
        model.setDataSaver();
        logger.warn(model.getGameData());
        winnerAlert(gameWinner);
    }

    private void winnerAlert(String gameWinner) {
        logger.info(gameWinner + " is the winner!");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("You " + gameWinner + " won the game! Time to celebrate!");
        alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(respone -> Platform.exit());
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String changeColorOfStones(Square square) {
        switch (square) {
            case PLAYER1 -> {
                return "#c0392b"; // Blue
            }

            case PLAYER2 -> {
                return "#2980b9"; // Red
            }
            default -> {
                return "#FFFFFF"; // White
            }
        }
    }
}
