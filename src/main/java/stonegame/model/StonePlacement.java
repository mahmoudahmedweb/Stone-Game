package stonegame.model;
import stonegame.game.State;

/**
 * The {@code StonePlacement} class represents a stone placement on the game board.
 * It encapsulates the row, column, and current player associated with the stone placement.
 */
public class StonePlacement {
    private int row;
    private int col;
    private State.Player currentPlayer;

    /**
     * Constructs a {@code StonePlacement} object with the specified row, column, and current player.
     *
     * @param row           The row index of the stone placement.
     * @param col           The column index of the stone placement.
     * @param currentPlayer The current player who made the stone placement.
     */
    public StonePlacement(int row, int col, State.Player currentPlayer) {
        this.row = row;
        this.col = col;
        this.currentPlayer = currentPlayer;
    }

    /**
     * Gets the row index of the stone placement.
     *
     * @return The row index.
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column index of the stone placement.
     *
     * @return The column index.
     */
    public int getCol() {
        return col;
    }

    /**
     * Gets the current player who made the stone placement.
     *
     * @return The current player.
     */
    public State.Player getCurrentPlayer() {
        return currentPlayer;
    }
}
