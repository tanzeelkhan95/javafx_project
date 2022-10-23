package Game;


import Game.StyleAndControll.PlayerModel;
import Game.StyleAndControll.Square;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import Game.StyleAndControll.BoardGameModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.ActionEvent;
import java.time.LocalDateTime;

public class BoardGameController {


    @FXML
    private GridPane board;

    @FXML
    private Pane pane;

    @FXML
    private Label label;

    @FXML
    private Button resetButton;

    @FXML
    private TextField movesNumber;

    private IntegerProperty numberOfMoves = new SimpleIntegerProperty(0);

    PlayerModel playerModel = new PlayerModel();

    private BoardGameModel model = new BoardGameModel();

    public LocalDateTime finishTime;

    int prevrow,prevcol;


    private String winnerName = "";


    @FXML
    private void initialize() {
        createControlBindings();
        for (var i = 0; i < board.getRowCount(); i++) {
            for (var j = 0; j < board.getColumnCount(); j++) {
                var square = createSquare(i, j);
                board.add(square, j, i);
            }
        }
    }

    private void createControlBindings() {
        movesNumber.textProperty().bind(numberOfMoves.asString());
    }

    @FXML
    private void resetGame() {
        model = new BoardGameModel();
        numberOfMoves.set(0);
        initialize();
    }


    private StackPane createSquare(int i, int j) {
        var square = new StackPane();
        square.getStyleClass().add("square");
        var piece1 = new Circle(60);
        piece1.fillProperty().bind(
                new ObjectBinding<Paint>() {
                    {
                        super.bind(model.squareProperty(i, j));
                    }
                    @Override
                    protected Paint computeValue() {
                        return switch (model.squareProperty(i, j).get()) {
                            case EMPTY,POSSIBLE -> Color.TRANSPARENT;
                            case FILLED -> Color.GOLD;
                        };
                    }
                }
        );
        square.getChildren().add(piece1);

            var piece2 = new Circle(10);
            piece2.fillProperty().bind(
                    new ObjectBinding<Paint>() {
                        {
                            super.bind(model.squareProperty(i, j));
                        }
                        @Override
                        protected Paint computeValue() {
                            return switch (model.squareProperty(i, j).get()) {
                                case POSSIBLE -> Color.GREEN;
                                case FILLED,EMPTY -> Color.TRANSPARENT;
                            };
                        }
                    }
            );
            square.getChildren().add(piece2);
        square.setOnMouseClicked(this::handleMouseClick);
        return square;
    }

    @FXML
    private void handleMouseClick(MouseEvent event) {
        var square = (StackPane) event.getSource();
        var row = GridPane.getRowIndex(square);
        var col = GridPane.getColumnIndex(square);
        if(model.getSquare(row,col)== Square.FILLED){
          model.removeGoodMoves();
          prevcol = col;
          prevrow = row;
          model.goodMoves(row,col);
          System.out.println(model);
        }else if(model.getSquare(row,col) == Square.POSSIBLE){
            model.move(prevrow,prevcol,row,col);
            numberOfMoves.set(numberOfMoves.get() + 1);
        }
        if(model.goalState() == true){
            winnerName = playerModel.getPlayerName();
            System.out.println("Congrats! " + winnerName + " you have reached your goal");
            finishTime = LocalDateTime.now();
            System.out.println("Time Finished: " + finishTime);
            var alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Game Over");
            alert.setContentText("Congratulations, you have reached your goal!");
            alert.showAndWait();
        }

    }






}

