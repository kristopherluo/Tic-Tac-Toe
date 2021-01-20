package cpsc2150.extendedTicTacToe;

/**
 * The TicTacToe controller class will handle communication between our TicTacToeView and our Model (IGameBoard and BoardPosition)
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your BoardPosition class, the IGameBoard interface
 * and the implementations from previous homeworks
 * If your code was correct you will not need to make any changes to your IGameBoard classes
 */
public class TicTacToeController {

    //our current game that is being played
    private IGameBoard curGame;

    //The screen that provides our view
    private TicTacToeView screen;
    private boolean newGame;
    private int playerNum;
    private char[] characters;
    private static char player;
    private static int moveCounter;
    private char[] letters;
    public static final int MAX_PLAYERS = 10;

    /**
     * @param model the board implementation
     * @param view  the screen that is shown
     * @param np    The number of players for the game
     *
     * @post the controller will respond to actions on the view using the model.
     */
    public TicTacToeController(IGameBoard model, TicTacToeView view, int np) {
        this.curGame = model;
        this.screen = view;
        newGame = false;
        characters = new char[MAX_PLAYERS];
        playerNum = np;
        moveCounter = 0;
        letters = new char[] {'X', 'O', 'A', 'M', 'H', 'I', 'K', 'Y', 'Z', 'E'};
        //sets characters array to the symbols based on the total number of players
        for(int i = 0; i < np; i++){
            characters[i] = letters[i];
        }
    }

    /**
     * @param row the row of the activated button
     * @param col the column of the activated button
     *
     * @pre row and col are in the bounds of the game represented by the view
     * @post The button pressed will show the right token and check if a player has won.
     */
    public void processButtonClick(int row, int col) {
        //runs to get next player move if there hasn't been a winner
        if(!newGame) {
            BoardPosition lastPos = new BoardPosition(row, col);
            while (true) {
                player = characters[moveCounter % playerNum];
                //checks if space is available and in bounds
                if (curGame.checkSpace(lastPos)) {
                    moveCounter++;
                    break;
                } else {
                    screen.setMessage("This Space is not available");
                    return;
                }
            }
            //places the player's marker onto the board
            curGame.placeMarker(lastPos, player);
            screen.setMarker(row, col, player);

            //checks for winner and draw and prints the board out
            if (curGame.checkForWinner(lastPos)) {
                screen.setMessage("Player " + player + " wins! Click any button to start a new game.");
                newGame = true;
            } else if (curGame.checkForDraw()) {
                screen.setMessage("Draw! Click any button to start a new game.");
                newGame = true;
            } else {
                //prints who's turn it is
                player = characters[moveCounter % playerNum];
                screen.setMessage("It is " + player + "'s turn");
            }
        }else{
            //starts new game
            newGame();
        }
    }

    private void newGame() {
        // You do not need to make any changes to this code.
        screen.dispose();
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
    }
}