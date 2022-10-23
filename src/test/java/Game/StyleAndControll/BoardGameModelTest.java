package Game.StyleAndControll;

import javafx.beans.property.ReadOnlyObjectWrapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardGameModelTest {

    @Test
    void goalTest(){
        var board = new BoardGameModel();
        board.board[0][0].set(Square.FILLED);
        board.board[0][3].set(Square.FILLED);
        board.board[3][0].set(Square.FILLED);
        board.board[3][3].set(Square.FILLED);
        assertTrue(board.goalState());
    }

}