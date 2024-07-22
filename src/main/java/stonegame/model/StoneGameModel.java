package stonegame.model;

import javafx.beans.property.ReadOnlyObjectWrapper;
import stonegame.game.BasicState;

/**
 * Represents the model of the Stone Game.
 * This class manages the game state, including the game board, player turns, and game status.
 * It implements the stonegame.game.BasicState interface for handling game state operations.
 */
public class StoneGameModel implements BasicState<StonePlacement> {
    private GameData gameData;
    private final String dataFile = "Data.json";
    private GameDataStorage dataSaver = new GameDataStorage();
    private ReadOnlyObjectWrapper<Square>[][] board;
    public static final int BOARD_SIZE = 5;
    private Player currentPlayer;

    /**
     * Constructs a new StoneGameModel object.
     * Initializes the game board, setting all squares to NONE,
     * and sets the initial player to PLAYER_1.
     */
    public StoneGameModel() {
        board = new ReadOnlyObjectWrapper[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new ReadOnlyObjectWrapper<Square>(Square.NONE);
            }
        }
        currentPlayer = Player.PLAYER_1;
    }

    /**
     * Starts a new game with the given player names.
     *
     * @param firstPlayerName The name of the first player.
     * @param secondPlayerName The name of the second player.
     */
    public void startNewGame(String firstPlayerName, String secondPlayerName) {
        gameData = new GameData(firstPlayerName, secondPlayerName);
    }

    /**
     * Gets the game data.
     *
     * @return The game data.
     */
    public GameData getGameData() {
        return gameData;
    }

    /**
     * Saves the game data to a file.
     */
    public void setDataSaver() {
        dataSaver.saveGameData(gameData, dataFile);
    }

    /**
     *  gets the player who should make the next move.
     * @return the player who should make the next move.
     */
    @Override
    public Player getNextPlayer() {
        return currentPlayer;
    }

    /**
     * Gets the square at the specified row and column on the game board.
     * @param row The row index.
     * @param col The column index.
     * @return The square at the specified position.
     */
    public Square getSquare(int row, int col) {
        return board[row][col].get();
    }

    /**
     * Sets the square at the specified row and column on the game board to the given square value.
     *
     * @param row The row index.
     * @param col The column index.
     * @param square The square value to set.
     * @return The updated square value.
     */
    public Square setSquare(int row, int col, Square square) {
        board[row][col].set(square);
        return square;
    }

    /**
     * Check if the game is over.
     *
     * @return {@code true} if the game is over, {@code false} otherwise.
     */
    @Override
    public boolean isGameOver() {
        return isWinner(Player.PLAYER_1) || isWinner(Player.PLAYER_2);
    }

    /**
     * Gets the status of the game.
     *
     * @return The status of the game ({@link Status#PLAYER_1_WINS}, {@link Status#PLAYER_2_WINS}, or {@link Status#IN_PROGRESS}).
     */
    @Override
    public Status getStatus() {
        if (isWinner(Player.PLAYER_1)) {
            return Status.PLAYER_1_WINS;
        } else if (isWinner(Player.PLAYER_2)) {
            return Status.PLAYER_2_WINS;
        } else {
            return Status.IN_PROGRESS;
        }
    }

    /**
     * Checks if a move is legal.
     *
     * @param stonePlacement The stone placement to be checked.
     * @return {@code true} if the move is legal, {@code false} otherwise.
     */
    @Override
    public boolean isLegalMove(StonePlacement stonePlacement) {
        int row = stonePlacement.getRow();
        int col = stonePlacement.getCol();

        // Check if the specified position is within the board boundaries
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            return false;
        }
        // Check if the specified position is already occupied
        if (board[row][col].get() != Square.NONE) {
            return false;
        }
        return true;
    }

    /**
     * Makes a move in the game.
     * @param stonePlacement The stone placement to be made.
     * @throws IllegalStateException If the game is already over.
     * @throws IllegalArgumentException If the move is illegal.
     */
    @Override
    public void makeMove(StonePlacement stonePlacement) {
        int row = stonePlacement.getRow();
        int col = stonePlacement.getCol();

        // Check if the game is already over
        if (isGameOver()) {
            throw new IllegalStateException("Game is already over");
        }

        // Check if the move is legal
        if (!isLegalMove(stonePlacement)) {
            throw new IllegalArgumentException("Illegal move");
        }

        // Make the move
        board[row][col].set(currentPlayer == Player.PLAYER_1 ? Square.PLAYER1 : Square.PLAYER2);

        if (currentPlayer == Player.PLAYER_1) {
            gameData.setLastPlayer(gameData.getPlayer1Name());
            gameData.incrementNumberOfTurnsOfPlayer1();
        } else {
            gameData.setLastPlayer(gameData.getPlayer2Name());
            gameData.incrementNumberOfTurnsOfPlayer2();
        }
        currentPlayer = (currentPlayer == Player.PLAYER_1) ? Player.PLAYER_2 : Player.PLAYER_1;
    }

    /**
     * Checks if a player has won the game.
     * @param player  The player to be checked.
     * @return {@code true} if the player has won, {@code false} otherwise.
     */
    @Override
    public boolean isWinner(Player player) {
        // Check rows
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (checkRow(row, player)) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (checkColumn(col, player)) {
                return true;
            }
        }

        // Check diagonals
        if (checkDiagonal(player) || checkAntiDiagonal(player)) {
            return true;
        }
        return false;
    }

    /**
     * Helper method to check if a player has three stones in a row.
     *
     * @param row    The row to be checked.
     * @param player The player to be checked.
     * @return {@code true} if the player has three stones in a row, {@code false} otherwise.
     */
    private boolean checkRow(int row, Player player) {
        for (int col = 0; col < BOARD_SIZE - 2; col++) {
            if (getSquare(row, col) == getSquare(row, col + 1) && getSquare(row, col) == getSquare(row, col + 2) && getSquare(row, col) == (player == Player.PLAYER_1 ? Square.PLAYER1 : Square.PLAYER2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method to check if a player has three stones in a column.
     *
     * @param col    The column to be checked.
     * @param player The player to be checked.
     * @return {@code true} if the player has three stones in a column, {@code false} otherwise.
     */
    private boolean checkColumn(int col, Player player) {
        for (int row = 0; row < BOARD_SIZE - 2; row++) {
            if (getSquare(row, col) == getSquare(row + 1, col) && getSquare(row, col) == getSquare(row + 2, col) && getSquare(row, col) == (player == Player.PLAYER_1 ? Square.PLAYER1 : Square.PLAYER2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method to check if a player has three stones in a diagonal.
     *
     * @param player The player to be checked.
     * @return {@code true} if the player has three stones in a diagonal, {@code false} otherwise.
     */
    private boolean checkDiagonal(Player player) {
        for (int row = 0; row < BOARD_SIZE - 2; row++) {
            for (int col = 0; col < BOARD_SIZE - 2; col++) {
                if (getSquare(row, col) == getSquare(row + 1, col + 1) && getSquare(row, col) == getSquare(row + 2, col + 2) && getSquare(row, col) == (player == Player.PLAYER_1 ? Square.PLAYER1 : Square.PLAYER2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Helper method to check if a player has three stones in an anti-diagonal.
     *
     * @param player The player to be checked.
     * @return {@code true} if the player has three stones in an anti-diagonal, {@code false} otherwise.
     */
    private boolean checkAntiDiagonal(Player player) {
        for (int row = 0; row < BOARD_SIZE - 2; row++) {
            for (int col = 2; col < BOARD_SIZE; col++) {
                if (getSquare(row, col) == getSquare(row + 1, col - 1) && getSquare(row, col) == getSquare(row + 2, col - 2) && getSquare(row, col) == (player == Player.PLAYER_1 ? Square.PLAYER1 : Square.PLAYER2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets the name of the winner.
     *
     * @return The name of the winner.
     */
    public String getWinnerName() {
        String lastPlayer = gameData.getLastPlayer();

        String gameWinner = lastPlayer.equals(gameData.getPlayer1Name())
                ? gameData.getPlayer1Name()
                : gameData.getPlayer2Name();

        gameData.setGameWinner(gameWinner);
        return gameWinner;
    }

    /**
     * Prints the current state of the game board.
     *
     * @return A string representing the current state of the game board.
     */
    public String printBoard() {
        StringBuilder boardString = new StringBuilder("Current Board:\n");
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Square square = getSquare(row, col);
                boardString.append(square).append("\t");
            }
            boardString.append("\n");
        }
        return boardString.toString();
    }

    /**
     * Gets the current player.
     *
     * @return The current player.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
