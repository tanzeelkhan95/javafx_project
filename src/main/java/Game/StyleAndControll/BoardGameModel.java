package Game.StyleAndControll;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;

import java.sql.SQLInput;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.StringJoiner;


/**
 * Class specifically designed to handle the model of the board game
 */

public class BoardGameModel {
    public static int BOARD_SIZE = 4;
    public LocalDateTime startingTime;

    ReadOnlyObjectWrapper<Square>[][] board = new ReadOnlyObjectWrapper[BOARD_SIZE][BOARD_SIZE];


    public BoardGameModel() {
        startingTime = LocalDateTime.now();
        System.out.println("Starting Time: " + startingTime);
        for(int i = 0; i < BOARD_SIZE; i++){
            for(int j = 0; j < BOARD_SIZE;j++){
                board[i][j] = new ReadOnlyObjectWrapper<Square>(Square.EMPTY);
            }
        }
        board[1][1] = new ReadOnlyObjectWrapper<Square>(Square.FILLED);
        board[1][2] = new ReadOnlyObjectWrapper<Square>(Square.FILLED);
        board[2][1] = new ReadOnlyObjectWrapper<Square>(Square.FILLED);
        board[2][2] = new ReadOnlyObjectWrapper<Square>(Square.FILLED);
    }

    public ReadOnlyObjectProperty<Square> squareProperty(int i, int j) {
        return board[i][j].getReadOnlyProperty();
    }

    public Square getSquare(int i, int j) {
        return board[i][j].get();
    }

    /**
     * The method is designed to handle the movement of the coins from one square to another
     * @param previ Previous row
     * @param prevj Previous column
     * @param i initial row
     * @param j initial column
     */

    public void move(int previ, int prevj, int i, int j) {
        if(adjacentCoins(previ,prevj)){
          board[i][j].set(Square.FILLED);
          board[previ][prevj].set(Square.EMPTY);
          removeGoodMoves();
        }else{
            System.out.println("not adjacent");
        }
    }

    /**
     * returns the model which is in string
     * @return string model
     */

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var i = 0; i < BOARD_SIZE; i++) {
            for (var j = 0; j < BOARD_SIZE; j++) {
                sb.append(board[i][j].get().ordinal()).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * Function which tells the user if a coin is adjacent to the coin selected
     * @param row rows
     * @param col columns
     * @return returns true if coin is adjacent
     */

    public Boolean adjacentCoins(int row, int col){
        for (var i = 0; i < BOARD_SIZE; i++){
            for (var j = 0; j < BOARD_SIZE; j++){

                if(board[i][j].get() == Square.FILLED && i == row){
                    if(j - col == 1 || j - col == -1){
                        return true;
                    }
                }
                else if(board[i][j].get() == Square.FILLED && j == col){
                    if(i - row == 1 || i - row == -1){
                        return true;
                    }
                }

            }
        }
        return false;

    }

    /**
     * A function which tells the player if he/she has reached the goal state or not. It is true if all coins are in each corner
     * @return it is true when all four coins are in each corner
     */

    public boolean goalState(){
        if(board[0][0].get() == Square.FILLED && board[3][0].get() == Square.FILLED && board[0][3].get() == Square.FILLED && board[3][3].get() == Square.FILLED){
            return true;
        }
        return false;
    }

    /**
     * Method which shows the possible moves for a coin but removes the possible moves for the previous coin when you click on another one
     */

    public void removeGoodMoves(){
        for (var i = 0; i < BOARD_SIZE; i++){
            for (var j = 0; j < BOARD_SIZE; j++ ){
                if(board[i][j].get() == Square.POSSIBLE){
                    board[i][j].set(Square.EMPTY);
                }
            }
        }
    }

    /**
     * Method which shows the valid places where the coin can move when you click on it
     * @param row rows
     * @param col columns
     */

    public void goodMoves(int row, int col){
        if(row == 0 && col == 0){
            if(board[row+1][col].get() == Square.EMPTY) {
                board[row + 1][col].set(Square.POSSIBLE);
            }
            if(board[row+2][col].get() == Square.EMPTY) {
                board[row + 2][col].set(Square.POSSIBLE);
            }
            if(board[row+3][col].get() == Square.EMPTY) {
                board[row + 3][col].set(Square.POSSIBLE);
            }
            if(board[row][col+1].get() == Square.EMPTY){
                board[row][col+1].set(Square.POSSIBLE);
            }
            if(board[row][col+2].get() == Square.EMPTY) {
                board[row][col + 2].set(Square.POSSIBLE);
            }
            if(board[row][col+3].get() == Square.EMPTY) {
                board[row][col + 3].set(Square.POSSIBLE);
            }

        }
        else if(row == 1 && col == 0 ){
            if(board[row+1][col].get() == Square.EMPTY) {
                board[row + 1][col].set(Square.POSSIBLE);
            }
            if(board[row][col+1].get() == Square.EMPTY){
                board[row][col+1].set(Square.POSSIBLE);
            }
            if(board[row-1][col].get() == Square.EMPTY) {
                board[row-1][col].set(Square.POSSIBLE);
            }
            if(board[row+2][col].get() == Square.EMPTY) {
                board[row + 2][col].set(Square.POSSIBLE);
            }
            if(board[row][col+2].get() == Square.EMPTY){
                board[row][col+2].set(Square.POSSIBLE);
            }
            if(board[row][col+3].get() == Square.EMPTY){
                board[row][col+3].set(Square.POSSIBLE);
            }
        }
        else if(row == 2 && col == 0){
            if(board[row+1][col].get() == Square.EMPTY) {
                board[row + 1][col].set(Square.POSSIBLE);
            }
            if(board[row][col+1].get() == Square.EMPTY){
                board[row][col+1].set(Square.POSSIBLE);
            }
            if(board[row-1][col].get() == Square.EMPTY) {
                board[row-1][col].set(Square.POSSIBLE);
            }
            if(board[row-2][col].get() == Square.EMPTY) {
                board[row-2][col].set(Square.POSSIBLE);
            }
            if(board[row][col+2].get() == Square.EMPTY){
                board[row][col+2].set(Square.POSSIBLE);
            }
            if(board[row][col+3].get() == Square.EMPTY){
                board[row][col+3].set(Square.POSSIBLE);
            }

        }
        else if(row == 3 && col == 0){
            if(board[row-1][col].get() == Square.EMPTY) {
                board[row-1][col].set(Square.POSSIBLE);
            }
            if(board[row][col+1].get() == Square.EMPTY){
                board[row][col+1].set(Square.POSSIBLE);
            }
            if(board[row-2][col].get() == Square.EMPTY) {
                board[row-2][col].set(Square.POSSIBLE);
            }
            if(board[row-3][col].get() == Square.EMPTY) {
                board[row-3][col].set(Square.POSSIBLE);
            }
            if(board[row][col+2].get() == Square.EMPTY){
                board[row][col+2].set(Square.POSSIBLE);
            }
            if(board[row][col+3].get() == Square.EMPTY){
                board[row][col+3].set(Square.POSSIBLE);
            }
        }
        else if(row == 0 && col == 1){
            if(board[row+1][col].get() == Square.EMPTY) {
                board[row + 1][col].set(Square.POSSIBLE);
            }
            if(board[row][col+1].get() == Square.EMPTY){
                board[row][col+1].set(Square.POSSIBLE);
            }
            if(board[row][col-1].get() == Square.EMPTY){
                board[row][col-1].set(Square.POSSIBLE);
            }
            if(board[row+2][col].get() == Square.EMPTY) {
                board[row + 2][col].set(Square.POSSIBLE);
            }
            if(board[row+3][col].get() == Square.EMPTY) {
                board[row + 3][col].set(Square.POSSIBLE);
            }
            if(board[row][col+2].get() == Square.EMPTY){
                board[row][col+2].set(Square.POSSIBLE);
            }
        }
        else if(row == 1 && col == 1){
            if(board[row+1][col].get() == Square.EMPTY) {
                board[row + 1][col].set(Square.POSSIBLE);
            }
            if(board[row][col+1].get() == Square.EMPTY){
                board[row][col+1].set(Square.POSSIBLE);
            }
            if(board[row-1][col].get() == Square.EMPTY) {
                board[row-1][col].set(Square.POSSIBLE);
            }
            if(board[row][col-1].get() == Square.EMPTY){
                board[row][col-1].set(Square.POSSIBLE);
            }
            if(board[row+2][col].get() == Square.EMPTY) {
                board[row + 2][col].set(Square.POSSIBLE);
            }
            if(board[row][col+2].get() == Square.EMPTY){
                board[row][col+2].set(Square.POSSIBLE);
            }
        }
        else if(row == 2 && col == 1){
            if(board[row+1][col].get() == Square.EMPTY) {
                board[row+1][col].set(Square.POSSIBLE);
            }
            if(board[row][col+1].get() == Square.EMPTY){
                board[row][col+1].set(Square.POSSIBLE);
            }
            if(board[row-1][col].get() == Square.EMPTY) {
                board[row-1][col].set(Square.POSSIBLE);
            }
            if(board[row][col-1].get() == Square.EMPTY){
                board[row][col-1].set(Square.POSSIBLE);
            }
            if(board[row-2][col].get() == Square.EMPTY) {
                board[row-2][col].set(Square.POSSIBLE);
            }
            if(board[row][col+2].get() == Square.EMPTY){
                board[row][col+2].set(Square.POSSIBLE);
            }
        }
        else if(row == 3 && col == 1){
            if(board[row-1][col].get() == Square.EMPTY) {
                board[row-1][col].set(Square.POSSIBLE);
            }
            if(board[row][col+1].get() == Square.EMPTY){
                board[row][col+1].set(Square.POSSIBLE);
            }
            if(board[row][col-1].get() == Square.EMPTY){
                board[row][col-1].set(Square.POSSIBLE);
            }
            if(board[row-2][col].get() == Square.EMPTY) {
                board[row-2][col].set(Square.POSSIBLE);
            }
            if(board[row-3][col].get() == Square.EMPTY) {
                board[row-3][col].set(Square.POSSIBLE);
            }
            if(board[row][col+2].get() == Square.EMPTY){
                board[row][col+2].set(Square.POSSIBLE);
            }
        }
        else if(row == 0 && col == 2){
            if(board[row+1][col].get() == Square.EMPTY) {
                board[row + 1][col].set(Square.POSSIBLE);
            }
            if(board[row][col+1].get() == Square.EMPTY){
                board[row][col+1].set(Square.POSSIBLE);
            }
            if(board[row][col-1].get() == Square.EMPTY){
                board[row][col-1].set(Square.POSSIBLE);
            }
            if(board[row+2][col].get() == Square.EMPTY) {
                board[row + 2][col].set(Square.POSSIBLE);
            }
            if(board[row+3][col].get() == Square.EMPTY) {
                board[row + 3][col].set(Square.POSSIBLE);
            }
            if(board[row][col-2].get() == Square.EMPTY){
                board[row][col-2].set(Square.POSSIBLE);
            }
        }
        else if(row == 1 && col == 2){
            if(board[row+1][col].get() == Square.EMPTY) {
                board[row+1][col].set(Square.POSSIBLE);
            }
            if(board[row][col+1].get() == Square.EMPTY){
                board[row][col+1].set(Square.POSSIBLE);
            }
            if(board[row-1][col].get() == Square.EMPTY) {
                board[row-1][col].set(Square.POSSIBLE);
            }
            if(board[row][col-1].get() == Square.EMPTY){
                board[row][col-1].set(Square.POSSIBLE);
            }
            if(board[row+2][col].get() == Square.EMPTY) {
                board[row+2][col].set(Square.POSSIBLE);
            }
            if(board[row][col-2].get() == Square.EMPTY){
                board[row][col-2].set(Square.POSSIBLE);
            }
        }
        else if(row == 2 && col == 2){
            if(board[row+1][col].get() == Square.EMPTY) {
                board[row + 1][col].set(Square.POSSIBLE);
            }
            if(board[row][col+1].get() == Square.EMPTY){
                board[row][col+1].set(Square.POSSIBLE);
            }
            if(board[row-1][col].get() == Square.EMPTY) {
                board[row-1][col].set(Square.POSSIBLE);
            }
            if(board[row][col-1].get() == Square.EMPTY){
                board[row][col-1].set(Square.POSSIBLE);
            }
            if(board[row-2][col].get() == Square.EMPTY) {
                board[row - 2][col].set(Square.POSSIBLE);
            }
            if(board[row][col-2].get() == Square.EMPTY){
                board[row][col-2].set(Square.POSSIBLE);
            }
        }
        else if(row == 3 && col == 2){
            if(board[row-1][col].get() == Square.EMPTY) {
                board[row-1][col].set(Square.POSSIBLE);
            }
            if(board[row][col+1].get() == Square.EMPTY){
                board[row][col+1].set(Square.POSSIBLE);
            }
            if(board[row][col-1].get() == Square.EMPTY){
                board[row][col-1].set(Square.POSSIBLE);
            }
            if(board[row-2][col].get() == Square.EMPTY) {
                board[row-2][col].set(Square.POSSIBLE);
            }
            if(board[row-3][col].get() == Square.EMPTY) {
                board[row-3][col].set(Square.POSSIBLE);
            }
            if(board[row][col-2].get() == Square.EMPTY){
                board[row][col-2].set(Square.POSSIBLE);
            }
        }
        else if(row == 0 && col == 3){
            if(board[row+1][col].get() == Square.EMPTY) {
                board[row + 1][col].set(Square.POSSIBLE);
            }
            if(board[row][col-1].get() == Square.EMPTY){
                board[row][col-1].set(Square.POSSIBLE);
            }
            if(board[row+2][col].get() == Square.EMPTY) {
                board[row + 2][col].set(Square.POSSIBLE);
            }
            if(board[row+3][col].get() == Square.EMPTY) {
                board[row + 3][col].set(Square.POSSIBLE);
            }
            if(board[row][col-2].get() == Square.EMPTY){
                board[row][col-2].set(Square.POSSIBLE);
            }
            if(board[row][col-3].get() == Square.EMPTY){
                board[row][col-3].set(Square.POSSIBLE);
            }

        }
        else if(row == 1 && col == 3){
            if(board[row+1][col].get() == Square.EMPTY) {
                board[row + 1][col].set(Square.POSSIBLE);
            }
            if(board[row][col-1].get() == Square.EMPTY){
                board[row][col-1].set(Square.POSSIBLE);
            }
            if(board[row-1][col].get() == Square.EMPTY) {
                board[row-1][col].set(Square.POSSIBLE);
            }
            if(board[row+2][col].get() == Square.EMPTY) {
                board[row + 2][col].set(Square.POSSIBLE);
            }
            if(board[row][col-2].get() == Square.EMPTY){
                board[row][col-2].set(Square.POSSIBLE);
            }
            if(board[row][col-3].get() == Square.EMPTY){
                board[row][col-3].set(Square.POSSIBLE);
            }
        }
        else if(row == 2 && col == 3){
            if(board[row+1][col].get() == Square.EMPTY) {
                board[row + 1][col].set(Square.POSSIBLE);
            }
            if(board[row][col-1].get() == Square.EMPTY){
                board[row][col-1].set(Square.POSSIBLE);
            }
            if(board[row-1][col].get() == Square.EMPTY) {
                board[row-1][col].set(Square.POSSIBLE);
            }
            if(board[row-2][col].get() == Square.EMPTY) {
                board[row-2][col].set(Square.POSSIBLE);
            }
            if(board[row][col-2].get() == Square.EMPTY){
                board[row][col-2].set(Square.POSSIBLE);
            }
            if(board[row][col-3].get() == Square.EMPTY){
                board[row][col-3].set(Square.POSSIBLE);
            }
        }
        else if(row == 3 && col == 3){
            if(board[row-1][col].get() == Square.EMPTY) {
                board[row-1][col].set(Square.POSSIBLE);
            }
            if(board[row][col-1].get() == Square.EMPTY){
                board[row][col-1].set(Square.POSSIBLE);
            }
            if(board[row-2][col].get() == Square.EMPTY) {
                board[row-2][col].set(Square.POSSIBLE);
            }
            if(board[row-3][col].get() == Square.EMPTY) {
                board[row-3][col].set(Square.POSSIBLE);
            }
            if(board[row][col-2].get() == Square.EMPTY){
                board[row][col-2].set(Square.POSSIBLE);
            }
            if(board[row][col-3].get() == Square.EMPTY){
                board[row][col-3].set(Square.POSSIBLE);
            }
        }
    }

    /**
     * Shows board game model
     * @param args
     */
    public static void main(String[] args) {
        var model = new BoardGameModel();
        System.out.println(model);
    }



}