import java.util.ArrayList;
import java.util.Arrays;

public class PuzzleState {
    final char[] board;
    private int spacePos;
    static final char[] INITIAL_BOARD = {'8','7','6',
                                        '5','4','3',
                                        '2','1',' '}; //Initial GameState
    static final char[] GOAL_BOARD = {'1','2','3',
                                    '4','5','6',
                                    '7','8',' '}; //Goal GameState

    /*
        GameState holds the board configuration as argument.

     */
    public PuzzleState(char[] board) {
        this.board = board;
        for (int j = 0; j < 8; j++){
            if (board[j] == ' ') {
                this.spacePos = j;
                break;
            }
        }
    }

    /*
        A new gameState with the same board configuration as the current gameState is returned by the clone function.
     */
    public PuzzleState clone() {
        char[] clonedBoard;
        clonedBoard = Arrays.copyOf(this.board, this.board.length);
        PuzzleState state = new PuzzleState(clonedBoard);
        return state;
    }

    public int getSpacePos() {
        int j ;
        for (j = 0; j < 8; j++){
            if (board[j] == ' ') {
                break;
            }
        }
        return j;
    }

    /*
        The toString method returns the GameState as a printable string.
    */
    public String toString() {
        String s = "[";
        for (char c : this.board) s = s + c;
        return s + "]";
    }

    /*
        The function isGoal() returns true only if the board configuration of the current GameState is the goal
        configuration.
    */
    public boolean isGoal() {
        for (int j = 0; j < 9; j++) {
            if (this.board[j] != GOAL_BOARD[j]) return false;
        }
        return true;
    }

    /*
        The function sameBoard() returns true only if the gameState supplied as argument has the same board
         configuration as the current GameState.
     */
    public boolean sameBoard (PuzzleState gs) {
        for (int j = 0; j < 9; j++) {
            if (this.board[j] != gs.board[j]) return false;
        }
        return true;
    }

    /*
        A list of all gameStates that can be reached in a single move from the current state is returned by the possibleMove function.
     */

    public ArrayList<PuzzleState> possibleMoves() {
        ArrayList<PuzzleState> moves = new ArrayList<>();

        int blankPos = this.getSpacePos();

        // check if there's a elemnt up
        if (blankPos - 3 >= 0){
            char tempSwap;
            PuzzleState newState = this.clone();
            tempSwap = newState.board[blankPos - 3];
            newState.board[blankPos] = tempSwap;
            newState.board[blankPos - 3] = ' ';
            moves.add(newState);
        }

        // check if there's an elemnt down
        if(blankPos + 3 <= 8){
            char tempSwap;
            PuzzleState newState = this.clone();
            tempSwap = newState.board[blankPos + 3];
            newState.board[blankPos] = tempSwap;
            newState.board[blankPos + 3] = ' ';
            moves.add(newState);

        }

        // check if there's an elemnet on left
        if( (blankPos - 1) != - 1 && (blankPos - 1) != 2 && (blankPos - 1) != 5){
            char tempSwap;
            PuzzleState newState = this.clone();
            tempSwap = newState.board[blankPos - 1];
            newState.board[blankPos] = tempSwap;
            newState.board[blankPos - 1] = ' ';
            moves.add(newState);
        }

        // check if theres an element right
        if((blankPos + 1) != 3 && (blankPos + 1) != 6 && (blankPos + 1) != 9){
            char tempSwap;
            PuzzleState newState = this.clone();
            tempSwap = newState.board[blankPos + 1];
            newState.board[blankPos] = tempSwap;
            newState.board[blankPos + 1] = ' ';
            moves.add(newState);
        }

        // 0 , 1 , 2
        // 3 , 4 , 5
        // 6 , 7 , 8

        // 8 , 7 , 6
        // 5 , 4 , 3
        // 2 , - , 1

        // 8 , 7 , 6
        // 5 , 4 , -
        // 2 , 1 , -


        return moves;

    }

}

