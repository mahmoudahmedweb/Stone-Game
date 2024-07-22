package stonegame.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    public void playNewGame(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/PlayersData.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void openHighestScoreTable(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/HighestScore.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void quit(ActionEvent actionEvent) {
        Platform.exit();
    }
}
