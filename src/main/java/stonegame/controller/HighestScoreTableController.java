package stonegame.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import stonegame.model.HighestScoreTableModel;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class HighestScoreTableController {
    @FXML
    public TableView<Map.Entry<String, Integer>> highestScoreTable;
    @FXML
    public TableColumn<Map.Entry<String, Integer>, String> nameColumn;
    @FXML
    public TableColumn<Map.Entry<String, Integer>, Integer> totalGameWinsColumn;

    public void initialize() {
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getKey()));
        totalGameWinsColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getValue()).asObject());
        HighestScoreTableModel model = new HighestScoreTableModel(new ObjectMapper(), "Data.json");
        List<Map.Entry<String, Integer>> sortedStatistics = model.getTop5SortedPlayerStatistics();
        ObservableList<Map.Entry<String, Integer>> data = FXCollections.observableArrayList(sortedStatistics);
        highestScoreTable.setItems(data);
    }

    public void backToMainMenu(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/MainMenu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void startNewGame(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/PlayersData.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}