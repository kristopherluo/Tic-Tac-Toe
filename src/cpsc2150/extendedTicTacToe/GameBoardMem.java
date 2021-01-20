package cpsc2150.extendedTicTacToe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameBoardMem extends AbsGameBoard implements IGameBoard {
    /**
     * @invariant MIN_DIM <= pos.row <= ROWS; and
     *            MIN_DIM <= pos.col <= ROWS and
     *            player = 'X' || player = 'O' and
     *            MIN_DIM <= lastPos.row <= ROWS and
     *            MIN_DIM <= lastPos.col <= ROWS
     *            0 <= count <= WIN
     *
     * Correspondences self = board = Board
     */

    private final Map<Character, List<BoardPosition>> board;
    private static int ROWS, COLS, NUM_TO_WIN;

    /**
     * creates 2D array of board and makes it empty
     *
     * @post empty 2D board array is created
     */
    public GameBoardMem(int row, int col, int win){
        ROWS = row - 1;
        COLS = col - 1;
        NUM_TO_WIN = win;
        board = new HashMap<>();
    }

    public void placeMarker(BoardPosition marker, char player)
    {
        BoardPosition tempMarker = new BoardPosition(marker.getRow(), marker.getColumn());
        if(!board.containsKey(player)) board.put(player, new ArrayList<>());
        board.get(player).add(tempMarker);
    }

    public char whatsAtPos(BoardPosition pos) {
        for (Character tempPlayer : board.keySet()) {
            for(BoardPosition tempPos : board.get(tempPlayer)){
                if(tempPos.equals(pos)) {
                    return tempPlayer;
                }
            }
        }
        return ' ';
    }

    public boolean checkHorizontalWin(BoardPosition lastPos, char player)
    {
        int count = 0;
        int tempCol = lastPos.getColumn();
        int tempRow = lastPos.getRow();
        BoardPosition temp = new BoardPosition(tempRow, tempCol);
        //goes through the entire row to check for WIN parameter player's in a row
        while((tempCol <= COLS) && (count < NUM_TO_WIN)){
            if(isPlayerAtPos(temp, player)){
                count++;
            }else{
                break;
            }
            tempCol++;
            temp.incrementCol(tempCol);
        }
        tempCol = lastPos.getColumn();
        temp = new BoardPosition(tempRow, tempCol);
        while((tempCol >= 0) && (count < NUM_TO_WIN)){
            tempCol--;
            temp.incrementCol(tempCol);
            if(isPlayerAtPos(temp, player)){
                count++;
            }else{
                break;
            }
        }
        return count == NUM_TO_WIN;
    }

    public boolean checkVerticalWin(BoardPosition lastPos, char player)
    {
        int count = 0;
        int tempRow = lastPos.getRow();
        int tempCol = lastPos.getColumn();
        BoardPosition temp = new BoardPosition(tempRow, tempCol);
        //goes through the entire column to check for WIN parameter player's in a row
        while((tempRow <= ROWS) && (count < NUM_TO_WIN)){
            if(isPlayerAtPos(temp, player)){
                count++;
            }else{
                break;
            }
            tempRow++;
            temp.incrementRow(tempRow);
        }
        tempRow = lastPos.getRow();
        temp = new BoardPosition(tempRow, tempCol);
        while((tempRow >= 0) && (count < NUM_TO_WIN)){
            tempRow--;
            temp.incrementRow(tempRow);
            if(isPlayerAtPos(temp, player)){
                count++;
            }else{
                break;
            }
        }
        return count == NUM_TO_WIN;
    }

    public boolean checkDiagonalWin(BoardPosition lastPos, char player)
    {
        int count = 0;
        int tempRow = lastPos.getRow();
        int tempCol = lastPos.getColumn();

        //while loop to check how many consecutive parameter player's there are in the diagonal going down and right
        while(tempRow <= ROWS && tempCol <= COLS){
            BoardPosition temp = new BoardPosition(tempRow, tempCol);
            if(isPlayerAtPos(temp, player)){
                count++;
            }else{
                break;
            }
            if(count == NUM_TO_WIN + 1){return true;}
            tempRow++;
            tempCol++;
        }
        //resets tempRow and tempCol back to the values of lastPos
        tempRow = lastPos.getRow();
        tempCol = lastPos.getColumn();

        //while loop to check how many consecutive parameter player's there are in the diagonal going up and left
        //This adds on to the last while loop testing for the diagonal going down and right
        while(tempRow >= 0 && tempCol >= 0){
            BoardPosition temp = new BoardPosition(tempRow, tempCol);
            if(isPlayerAtPos(temp, player)){
                count++;
            }else{
                break;
            }
            if(count == NUM_TO_WIN + 1){return true;}
            tempRow--;
            tempCol--;
        }
        //resets count back to 0 to check for new diagonal
        count = 0;
        //resets tempRow and tempCol back to the values of lastPos
        tempRow = lastPos.getRow();
        tempCol = lastPos.getColumn();

        //while loop to check how many consecutive parameter player's there are in the diagonal going down and left
        while(tempRow <= ROWS && tempCol >= 0){
            BoardPosition temp = new BoardPosition(tempRow, tempCol);
            if(isPlayerAtPos(temp, player)){
                count++;
            }else{
                break;
            }
            if(count == NUM_TO_WIN + 1){return true;}
            tempRow++;
            tempCol--;
        }
        //resets tempRow and tempCol back to the values of lastPos
        tempRow = lastPos.getRow();
        tempCol = lastPos.getColumn();

        //while loop to check how many consecutive parameter player's there are in the diagonal going up and right
        //This adds on to the last while loop testing for the diagonal going down and left
        while(tempRow >= 0 && tempCol <= COLS){
            BoardPosition temp = new BoardPosition(tempRow, tempCol);
            if(isPlayerAtPos(temp, player)){
                count++;
            }else{
                break;
            }
            if(count == NUM_TO_WIN + 1){return true;}
            tempRow--;
            tempCol++;
        }
        return false;
    }

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player){
        if(board.containsKey(player))
            return board.get(player).contains(pos);
        return false;
    }

    public int getNumRows(){
        return ROWS;
    }

    public int getNumColumns(){
        return COLS;
    }

    public int getNumToWin(){
        return NUM_TO_WIN;
    }
}