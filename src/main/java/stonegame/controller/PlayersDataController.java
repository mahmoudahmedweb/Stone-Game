package stonegame.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import stonegame.model.StoneGameModel;

import javax.swing.*;
import java.io.IOException;

public class PlayersDataController {

    @FXML
    public TextField firstPlayerName;
    @FXML
    public TextField secondPlayerName;

    public void startGame(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StoneGame.fxml"));
        Parent root = loader.load();
        StoneGameController controller = loader.getController();
        StoneGameModel stoneGameModel = new StoneGameModel();
        stoneGameModel.startNewGame(firstPlayerName.getText(), secondPlayerName.getText());
        controller.setGameModel(stoneGameModel);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void backToMainMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainMenu.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
