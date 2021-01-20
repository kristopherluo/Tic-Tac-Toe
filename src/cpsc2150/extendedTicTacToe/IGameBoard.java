package cpsc2150.extendedTicTacToe;

/**
 * Abstractly, IGameBoard contains the actual game board, which is a 2-dimensional 8 x 8 array of characters.
 * Each position will be null until a player plays on that position.
 * At that point, the character will change to either X or O to represent that player.
 * IGameBoard will control a majority of calculations for checking for draws, wins, and position availability
 *
 * Defines: Board: two dimensional character array with MAX_DIM and MAX_DIM as it's dimensions
 * Initialization ensures: each position of the array is empty
 * Constraints: Board: char[MAX_DIM][MAX_DIM]
 */

public interface IGameBoard {
    static final int MAX_DIM = 100;
    static final int MAX_WIN = 25;
    static final int MAX_PLAYER = 10;
    static final int MIN_PLAYER = 2;
    static final int MIN_DIM = 3;
    static final int TWO_DIGIT = 10;

    /**
     * places the character in marker on the position specified by
     * marker, and should not be called if the space is not available.
     *
     * @param marker position to insert player parameter
     * @param player the player symbol will be placed at marker parameter
     * @pre 0 <= marker.row <= getNumRows and
     *      0 <= marker.col <= getNumColumns and
     *      player = char
     * @post player char will be added to board at position marker
     */
    void placeMarker(BoardPosition marker, char player);

    /**
     * returns what is in the GameBoard at position pos
     *
     * @param pos position to be checked for what's inside
     * @return what is inside of the position passed in
     * @pre 0 <= pos.row <= getNumRows and
     *      0 <= pos.col <= getNumColumns
     * @post whatsAtPos = the char at pos
     */
    char whatsAtPos(BoardPosition pos);

    /**
     * checks to see if the last marker placed resulted in 5 in a row
     * horizontally. Returns true if it does, otherwise false
     *
     * @param lastPos last position to be checked if it builds into a
     *                horizontal win for a player
     * @param player 'X' or 'O' to be checked for horizontal row
     * @return if there is a horizontal win
     * @pre 0 <= lastPos.row <= getNumRows and
     *      0 <= lastPos.col <= getNumColumns
     *      player = 'X' || player = 'O'
     * @post checkHorizontalWin iff(horizontal getNumToWin player chars are connected)
     */
    boolean checkHorizontalWin(BoardPosition lastPos, char player);

    /**
     * checks to see if the last marker placed resulted in 5 in a row
     * vertically. Returns true if it does, otherwise false
     *
     * @param lastPos position to be checked if it builds into a
     *             vertical win for a player
     * @param player 'X' or 'O' to be checked for vertical row
     * @return if there is a vertical win
     * @pre 0 <= lastPos.row <= getNumRows and
     *      0 <= lastPos.col <= getNumColumns
     *      player = 'X' || player = 'O'
     * @post checkVerticalWin iff(vertical getNumToWin player chars are connected)
     */
    boolean checkVerticalWin(BoardPosition lastPos, char player);

    /**
     * checks to see if the last marker placed resulted in 5 in a row
     * diagonally. Returns true if it does, otherwise false
     *
     * @param lastPos position to be checked if it builds into a
     *             diagonal win for a player
     * @param player 'X' or 'O' to be checked for diagonal row
     * @return if there is a diagonal win
     * @pre 0 <= lastPos.row <= getNumRows and
     *      0 <= lastPos.col <= getNumColumns
     *      player = 'X' || player = 'O'
     * @post checkDiagonalWin iff(diagonal getNumToWin player chars are connected)
     */
    boolean checkDiagonalWin(BoardPosition lastPos, char player);

    /**
     * returns the number of rows in the GameBoard
     *
     * @return number of rows in GameBoard
     * @post getNumRows = number of rows in GameBoard
     */
    int getNumRows();

    /**
     * returns the number of columns in the GameBoard
     *
     * @return number of columns in GameBoard
     * @post getNumColumns = number of columns in GameBoard
     */
    int getNumColumns();

    /**
     * returns the number of tokens in a row needed to win the game
     *
     * @return number of tokens in a row to win game
     * @post getNumToWin = number of tokens to win game
     */
    int getNumToWin();

    /**
     * returns true if the position specified in pos is available,
     * false otherwise. If a space is not in bounds, then it is not available
     *
     * @param pos position to be checked if it is available
     * @return if pos is empty or not
     * @pre 0 <= pos.row <= getNumRows and
     *      0 <= pos.col <= getNumColumns
     * @post checkSpace iff(position pos on board == ' ')
     */
    default boolean checkSpace(BoardPosition pos){
        if(pos.getRow() >= 0 && pos.getRow() <= getNumRows() && pos.getColumn() >= 0 && pos.getColumn() <= getNumColumns()) {
            return whatsAtPos(pos) == ' ';
        }
        return false;
    }

    /**
     * this function will check to see if the game has resulted in a
     * tie. A game is tied if there are no free board positions remaining.
     *
     * @return if it a draw
     * @post checkForDraw iff(all positions = ' ')
     */
    default boolean checkForDraw()
    {
        BoardPosition temp = new BoardPosition(0, 0);
        //goes through each row of board
        for(int tempRow = 0; tempRow <= getNumRows(); tempRow++) {
            temp.incrementRow(tempRow);
            //goes through each column of board
            for(int tempCol = 0; tempCol <= getNumColumns(); tempCol++) {
                temp.incrementCol(tempCol);
                //if a position is empty, there is no draw
                if((whatsAtPos(temp) == ' ')){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * this function will check to see if the lastPos placed resulted
     * in a winner. It so it will return true, otherwise false.
     *
     * @param lastPos last position to be checked if it builds into a win for a player
     * @return if there a winner
     * @pre 0 <= lastPos.row <= getNumRows and
     *      0 <= lastPos.col <= getNumColumns
     * @post checkForWinner iff(checkHorizontalWin(lastPos) ||
     *       checkVerticalWin(lastPos) || checkDiagonalWin(lastPos))
     */
    default boolean checkForWinner(BoardPosition lastPos)
    {
        char player = whatsAtPos(lastPos);
        return checkHorizontalWin(lastPos, player) || checkVerticalWin(lastPos, player) || checkDiagonalWin(lastPos, player);
    }

    /**
     * returns true if the player is at pos, otherwise it return false
     *
     * @param pos position to be checked if the player parameter is there
     * @param player player 'X' or 'O' to be look for in pos parameter
     * @return if player passed in is at the position passed in
     * @pre 0 <= pos.row <= getNumRows and
     *      0 <= pos.col <= getNumColumns
     *      player = 'X' || player = 'O'
     * @post isPlayerAtPos iff(position pos on board = player)
     */
    default boolean isPlayerAtPos(BoardPosition pos, char player){
        return whatsAtPos(pos) == player;
    }
}
