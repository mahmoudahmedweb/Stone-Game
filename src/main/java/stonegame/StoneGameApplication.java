package stonegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StoneGameApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/MainMenu.fxml"));
        stage.setTitle("StoneGame");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
