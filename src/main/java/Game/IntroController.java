package Game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class IntroController {
    @FXML
    TextField playerText;
    @FXML
    Button startButton;
    @FXML
    Label warningText;

    FXMLLoader fxmlLoader = new FXMLLoader();

    @FXML
    public void handleButton(ActionEvent actionEvent) throws IOException {
        if(playerText.getText().isBlank()){
            warningText.setText("Please Enter Player name");
        }
        else{
            BoardGameController control = new BoardGameController();
            System.out.println("Name Entered!");
            control.playerModel.setPlayerName(playerText.getText());
            fxmlLoader.setLocation(getClass().getResource("/ui.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    public void openScores(ActionEvent actionEvent) throws IOException {
    }
}

