package stonegame.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stonegame.game.State;

import static org.junit.jupiter.api.Assertions.*;

public class StoneGameModelTest {

    private StoneGameModel model;

    @BeforeEach
    void setUp() {
        model = new StoneGameModel();
        model.startNewGame("Mahmoud", "Ahmed");
    }

    @Test
    void testBoardInitialization() {
        for (int i = 0; i < StoneGameModel.BOARD_SIZE; i++) {
            for (int j = 0; j < StoneGameModel.BOARD_SIZE; j++) {
                assertEquals(Square.NONE, model.getSquare(i, j));
            }
        }
    }

    @Test
    void testStartNewGame() {
        model.startNewGame("Mahmoud", "Ahmed");
        GameData gameData = model.getGameData();
        assertNotNull(model.getGameData());
        assertEquals("Mahmoud", model.getGameData().getPlayer1Name());
        assertEquals("Ahmed", model.getGameData().getPlayer2Name());
        assertEquals(0, gameData.getNumberOfTurnsOfPlayer1());
        assertEquals(0, gameData.getNumberOfTurnsOfPlayer2());
        assertNull(gameData.getGameWinner());
        assertNotNull(gameData.getStartingDateAndTime());
    }

    @Test
    void testGetNextPlayer() {
        assertEquals(State.Player.PLAYER_1, model.getNextPlayer());
        model.makeMove(new StonePlacement(0, 0, State.Player.PLAYER_1));
        assertEquals(State.Player.PLAYER_2, model.getNextPlayer());
        model.makeMove(new StonePlacement(0, 1, State.Player.PLAYER_2));
        assertEquals(State.Player.PLAYER_1, model.getNextPlayer());
    }

    @Test
    void testIsLegalMove() {
        assertTrue(model.isLegalMove(new StonePlacement(0, 0, State.Player.PLAYER_1)));
        model.makeMove(new StonePlacement(0, 0, State.Player.PLAYER_1));
        assertFalse(model.isLegalMove(new StonePlacement(0, 0, State.Player.PLAYER_2)));
        assertFalse(model.isLegalMove(new StonePlacement(-1, 0, State.Player.PLAYER_1)));
        assertFalse(model.isLegalMove(new StonePlacement(0, 5, State.Player.PLAYER_1)));
    }

    @Test
    void testMakeMove() {
        model.startNewGame("Mahmoud", "Ahmed");
        model.makeMove(new StonePlacement(0, 0, State.Player.PLAYER_1));
        assertEquals(Square.PLAYER1, model.getSquare(0, 0));
        assertThrows(IllegalArgumentException.class, () -> model.makeMove(new StonePlacement(0, 0, State.Player.PLAYER_2)));

        GameData gameData = model.getGameData();
        assertEquals(1, gameData.getNumberOfTurnsOfPlayer1());
        assertEquals(0, gameData.getNumberOfTurnsOfPlayer2());
        assertEquals("Mahmoud", gameData.getLastPlayer());

        model.makeMove(new StonePlacement(0, 1, State.Player.PLAYER_2));
        assertEquals(Square.PLAYER2, model.getSquare(0, 1));
        assertEquals(1, gameData.getNumberOfTurnsOfPlayer1());
        assertEquals(1, gameData.getNumberOfTurnsOfPlayer2());
        assertEquals("Ahmed", gameData.getLastPlayer());
    }

    @Test
    void testIsGameOver() {
        assertFalse(model.isGameOver());
        model.startNewGame("Mahmoud", "Ahmed");
        model.makeMove(new StonePlacement(0, 0, State.Player.PLAYER_1));
        model.makeMove(new StonePlacement(0, 1, State.Player.PLAYER_2));
        model.makeMove(new StonePlacement(1, 0, State.Player.PLAYER_1));
        model.makeMove(new StonePlacement(1, 1, State.Player.PLAYER_2));
        model.makeMove(new StonePlacement(2, 0, State.Player.PLAYER_1));
        assertTrue(model.isGameOver());
    }

    @Test
    void testGetStatus() {
        model.startNewGame("Mahmoud", "Ahmed");
        assertEquals(State.Status.IN_PROGRESS, model.getStatus());
        model.makeMove(new StonePlacement(0, 0, State.Player.PLAYER_1));
        model.makeMove(new StonePlacement(0, 1, State.Player.PLAYER_2));
        model.makeMove(new StonePlacement(1, 0, State.Player.PLAYER_1));
        model.makeMove(new StonePlacement(1, 1, State.Player.PLAYER_2));
        model.makeMove(new StonePlacement(2, 0, State.Player.PLAYER_1));
        assertEquals(State.Status.PLAYER_1_WINS, model.getStatus());
    }

    @Test
    void testIsWinner() {
        model.startNewGame("Mahmoud", "Ahmed");
        model.makeMove(new StonePlacement(0, 0, State.Player.PLAYER_1));
        model.makeMove(new StonePlacement(0, 1, State.Player.PLAYER_2));
        model.makeMove(new StonePlacement(1, 0, State.Player.PLAYER_1));
        model.makeMove(new StonePlacement(1, 1, State.Player.PLAYER_2));
        model.makeMove(new StonePlacement(2, 0, State.Player.PLAYER_1));
        assertTrue(model.isWinner(State.Player.PLAYER_1));
        assertFalse(model.isWinner(State.Player.PLAYER_2));
    }



    @Test
    void testGetWinnerName() {
        model.startNewGame("Mahmoud", "Ahmed");
        model.makeMove(new StonePlacement(0, 0, State.Player.PLAYER_1));
        model.makeMove(new StonePlacement(0, 1, State.Player.PLAYER_2));
        model.makeMove(new StonePlacement(1, 0, State.Player.PLAYER_1));
        model.makeMove(new StonePlacement(1, 1, State.Player.PLAYER_2));
        model.makeMove(new StonePlacement(2, 0, State.Player.PLAYER_1));
        assertEquals("Mahmoud", model.getWinnerName());
    }

    @Test
    void testPrintBoard() {
        model.startNewGame("Mahmoud", "Ahmed");
        String expectedBoard = "Current Board:\nNONE\tNONE\tNONE\tNONE\tNONE\t\nNONE\tNONE\tNONE\tNONE\tNONE\t\nNONE\tNONE\tNONE\tNONE\tNONE\t\nNONE\tNONE\tNONE\tNONE\tNONE\t\nNONE\tNONE\tNONE\tNONE\tNONE\t\n";
        assertEquals(expectedBoard, model.printBoard());
    }

    @Test
    void testGetCurrentPlayer() {
        model.startNewGame("Mahmoud", "Ahmed");
        assertEquals(State.Player.PLAYER_1, model.getCurrentPlayer());
        model.makeMove(new StonePlacement(0, 0, State.Player.PLAYER_1));
        assertEquals(State.Player.PLAYER_2, model.getCurrentPlayer());
    }

}
