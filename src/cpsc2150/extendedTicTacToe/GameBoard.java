package cpsc2150.extendedTicTacToe;

public class GameBoard extends AbsGameBoard implements IGameBoard {
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

    private final char[][] board;
    public static int ROWS, COLS, NUM_TO_WIN;

    /**
     * creates 2D array of board and makes it empty
     *
     * @post empty 2D board array is created
     */
    public GameBoard(int row, int col, int win){
        ROWS = row - 1;
        COLS = col - 1;
        NUM_TO_WIN = win;
        board = new char[MAX_DIM][MAX_DIM];
        for(int r = 0 ; r <= ROWS; r++) {
            for (int c = 0; c <= COLS; c++){
                board[r][c] = ' ';
            }
        }
    }

    public void placeMarker(BoardPosition marker, char player)
    {
        board[marker.getRow()][marker.getColumn()] = player;
    }

    public char whatsAtPos(BoardPosition pos)
    {
        return board[pos.getRow()][pos.getColumn()];
    }

    public boolean checkHorizontalWin(BoardPosition lastPos, char player)
    {
        int count = 0;
        int tempCol = 0;
        int tempRow = lastPos.getRow();
        BoardPosition temp = new BoardPosition(tempRow, tempCol);
        //goes through the entire row to check for WIN parameter player's in a row
        while((temp.getColumn() <= COLS) && (count < NUM_TO_WIN)){
            if(isPlayerAtPos(temp, player)){
                count++;
            }else{
                count = 0;
            }
            tempCol++;
            temp.incrementCol(tempCol);
        }
        return count == NUM_TO_WIN;
    }

    public boolean checkVerticalWin(BoardPosition lastPos, char player)
    {
        int count = 0;
        int tempRow = 0;
        int tempCol = lastPos.getColumn();
        BoardPosition temp = new BoardPosition(tempRow, tempCol);
        //goes through the entire column to check for WIN parameter player's in a row
        while((temp.getRow() <= ROWS) && (count < NUM_TO_WIN)){
            if(isPlayerAtPos(temp, player)){
                count++;
            }else{
                count = 0;
            }
            tempRow++;
            temp.incrementRow(tempRow);
        }
        return count == NUM_TO_WIN;
    }

    public boolean checkDiagonalWin(BoardPosition lastPos, char player)
    {
        int count = 0;
        int tempRow = lastPos.getRow();
        int tempCol = lastPos.getColumn();
        while(tempRow > 0 && tempCol > 0){
            tempRow--;
            tempCol--;
        }
        BoardPosition temp = new BoardPosition(tempRow, tempCol);
        while(tempRow <= ROWS && tempCol <= COLS && count < NUM_TO_WIN){
            if(isPlayerAtPos(temp, player)){
                count++;
            }else{
                count = 0;
            }
            tempRow++;
            tempCol++;
            temp.incrementRow(tempRow);
            temp.incrementCol(tempCol);
        }
        if(count == NUM_TO_WIN)
            return true;
        count = 0;
        tempRow = lastPos.getRow();
        tempCol = lastPos.getColumn();
        while(tempRow > 0 && tempCol < ROWS){
            tempRow--;
            tempCol++;
        }
        temp = new BoardPosition(tempRow, tempCol);
        while(tempRow <= ROWS && tempCol >= 0 && count < NUM_TO_WIN){
            if(isPlayerAtPos(temp, player)){
                count++;
            }else{
                count = 0;
            }
            tempRow++;
            tempCol--;
            temp.incrementRow(tempRow);
            temp.incrementCol(tempCol);
        }
        return count == NUM_TO_WIN;
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
