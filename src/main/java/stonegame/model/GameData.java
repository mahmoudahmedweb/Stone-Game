package stonegame.model;

/**
 * The {@code GameData} class represents the data associated with a game session.
 * It includes player names, number of turns for each player, game winner,
 * and starting date and time.
 */
public class GameData {

    private String player1Name;
    private String player2Name;
    private int numberOfTurnsOfPlayer1;
    private int numberOfTurnsOfPlayer2;
    private String gameWinner;
    private String startingDateAndTime;
    private String lastPlayer;

    /**
     * Constructs a new {@code GameData} instance with the provided player names.
     *
     * @param player1Name The name of player 1.
     * @param player2Name The name of player 2.
     */
    public GameData(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.numberOfTurnsOfPlayer1 = 0;
        this.numberOfTurnsOfPlayer2 = 0;
        this.gameWinner = null;
        this.lastPlayer = null;
        this.startingDateAndTime = new DateAndTime().getCurrentDateTime().toString();
    }

    /**
     * Default constructor.
     */
    public GameData() {

    }

    /**
     * Gets the name of the last player who made a move.
     *
     * @return The name of the last player.
     */
    public String getLastPlayer() {
        return lastPlayer;
    }

    /**
     * Sets the name of the last player who made a move.
     *
     * @param lastPlayer The name of the last player.
     */
    public void setLastPlayer(String lastPlayer) {
        this.lastPlayer = lastPlayer;
    }

    /**
     * Gets the name of player 1.
     *
     * @return The name of player 1.
     */
    public String getPlayer1Name() {
        return player1Name;
    }

    /**
     * Sets the name of player 1.
     *
     * @param player1Name The name of player 1.
     */
    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    /**
     * Gets the name of player 2.
     *
     * @return The name of player 2.
     */
    public String getPlayer2Name() {
        return player2Name;
    }

    /**
     * Sets the name of player 2.
     *
     * @param player2Name The name of player 2.
     */
    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    /**
     * Gets the number of turns of player 1.
     *
     * @return The number of turns of player 1.
     */
    public int getNumberOfTurnsOfPlayer1() {
        return numberOfTurnsOfPlayer1;
    }

    /**
     * Sets the number of turns of player 1.
     *
     * @param numberOfTurnsOfPlayer1 The number of turns of player 1.
     */
    public void setNumberOfTurnsOfPlayer1(int numberOfTurnsOfPlayer1) {
        this.numberOfTurnsOfPlayer1 = numberOfTurnsOfPlayer1;
    }

    /**
     * Gets the number of turns of player 2.
     *
     * @return The number of turns of player 2.
     */
    public int getNumberOfTurnsOfPlayer2() {
        return numberOfTurnsOfPlayer2;
    }

    /**
     * Sets the number of turns of player 2.
     *
     * @param numberOfTurnsOfPlayer2 The number of turns of player 2.
     */
    public void setNumberOfTurnsOfPlayer2(int numberOfTurnsOfPlayer2) {
        this.numberOfTurnsOfPlayer2 = numberOfTurnsOfPlayer2;
    }

    /**
     * Increments the number of turns of player 1 by one.
     */
    public void incrementNumberOfTurnsOfPlayer1() {
        this.numberOfTurnsOfPlayer1++;
    }

    /**
     * Increments the number of turns of player 2 by one.
     */
    public void incrementNumberOfTurnsOfPlayer2() {
        this.numberOfTurnsOfPlayer2++;
    }

    /**
     * Gets the name of the game winner.
     *
     * @return The name of the game winner.
     */
    public String getGameWinner() {
        return gameWinner;
    }

    /**
     * Sets the name of the game winner.
     *
     * @param gameWinner The name of the game winner.
     */
    public void setGameWinner(String gameWinner) {
        this.gameWinner = gameWinner;
    }

    /**
     * Gets the starting date and time of the game session.
     *
     * @return The starting date and time.
     */
    public String getStartingDateAndTime() {
        return startingDateAndTime;
    }

    /**
     * Sets the starting date and time of the game session.
     *
     * @param startingDateAndTime The starting date and time.
     */
    public void setStartingDateAndTime(String startingDateAndTime) {
        this.startingDateAndTime = startingDateAndTime;
    }


    /**
     * Returns a string representation of the {@code GameData} object.
     *
     * @return A string representation containing player names, number of turns,
     * game winner, and starting date and time.
     */
    @Override
    public String toString() {
        return "GameData{" +
                "player1='" + player1Name + '\'' +
                ", player2='" + player2Name + '\'' +
                ", numberOfTurnsOfPlayer1=" + numberOfTurnsOfPlayer1 +
                ", numberOfTurnsOfPlayer2=" + numberOfTurnsOfPlayer2 +
                ", gameWinner='" + gameWinner + '\'' +
                ", startingDateAndTime='" + startingDateAndTime + '\'' +
                '}';
    }
}
