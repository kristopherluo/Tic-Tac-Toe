package cpsc2150.extendedTicTacToe;

public abstract class AbsGameBoard implements IGameBoard{
    /**
     * returns the current board
     *
     * @return the current board
     * @post toString = <full layout of board/printable version of 2d board array>
     */
    @Override
    public String toString(){
        StringBuilder product = new StringBuilder();
        //goes through each row of board
        for(int r = 0; r <= getNumRows() + 1; r++){
            //goes through each column of board
            for(int c = 0; c <= getNumColumns(); c++){
                if(r == 0) {
                    //prints the spaces between the 0's of board
                    if(c == 0) product.append("    ");
                    //prints the first row of numbers
                    if(c < TWO_DIGIT - 1) product.append(c).append("| ");
                    else product.append(c).append("|");
                } else {
                    BoardPosition temp = new BoardPosition(r - 1,c);
                    //prints empty spaces if position is empty and the player at position if not
                    char player = whatsAtPos(temp);
                    if(player == ' ') product.append("| " + " ");
                    else product.append("|").append(player).append(" ");
                }
            }
            //prints the last separator of each row
            if(r != 0) product.append("|");
            //skips line
            product.append("\n");
            //prints the row number
            if(r <= getNumRows()) {
                if (r < TWO_DIGIT) product.append(" ").append(r);
                else product.append(r);
            }
        }
        return product.toString();
    }
}
