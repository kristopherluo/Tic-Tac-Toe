package cpsc2150.extendedTicTacToe;

public class BoardPosition {
    /**
     * @invariant 0 <= row <= implementation.getNumRows and
     *            0 <= col <= implementation.getNumColumns
     */

    private int row, col;

    /**
     * constructor that creates a type boardPosition with an row and col
     *
     * @param newRow the row number of a position
     * @param newCol the column number of a position
     * @pre 0 <= row <= implementation.getNumRows and
     *      0 <= col <= implementation.getNumColumns
     * @post row = nRow and
     *       col = nCol
     */
    public BoardPosition(int newRow, int newCol){
        row = newRow;
        col = newCol;
    }


    /**
     * returns the row of a position
     *
     * @return the row of a position
     * @post getRow = this.row
     */
    public int getRow(){
        return this.row;
    }

    /**
     * returns the column of a position
     *
     * @return the column of a position
     * @post getColumn = this.col
     */
    public int getColumn(){
        return this.col;
    }

    /**
     * checks if two boardPositions are equal
     *
     * @param obj one of the boardPositions to be compared
     * @pre obj must be of type boardPosition
     * @return if the two boardPositions are equal
     */
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof BoardPosition)) {
            return false;
        }
        BoardPosition pos = (BoardPosition) obj;
        return this.row == pos.row && this.col == pos.col;
    }

    /**
     * creates a string of "<row>,<column>" format
     *
     * @return the new string version of the object
     * @post toString = "<this.row>,<this.col>"
     */
    @Override
    public String toString(){
        String row = Integer.toString(this.row);
        String col = Integer.toString(this.col);
        return row + "," + col;
    }

    /**
     * @param amt amount to change the row to
     * @pre 0 <= amt + this.row <= implementation.getNumRows
     * @post position row is changed to amt
     */
    public void incrementRow(int amt){
        this.row = amt;
    }

    /**
     * @param amt amount to change the col to
     * @pre 0 <= amt + this.col <= implementation.getNumColumns
     * @post position col is changed to amt
     */
    public void incrementCol(int amt){
        this.col = amt;
    }
}
