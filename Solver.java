import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
   Solver is a class that contains the methods used to search for and print solutions
   plus the data structures needed for the search.
 */

public class Solver {

    ArrayList<Node> unexpanded = new ArrayList<Node>(); // Holds unexpanded nodes
    ArrayList<Node> expanded = new ArrayList<Node>();   // Holds expanded nodes
    Node rootNode;                                      // Node representing initial state

    /*
       Solver is a constructor that sets up an instance of the class with a node corresponding
       to the initial state as the root node.
     */
    public Solver(char[] initialBoard) {
        PuzzleState initialState = new PuzzleState(initialBoard);
        rootNode = new Node(initialState);
    }

    /*
        The algorithm used below is called breadth first search which is optimal for searching
        through the moves.
     */
    public void solve(PrintWriter output) {
        unexpanded.add(rootNode);
        while (unexpanded.size() > 0) {
            Node n = unexpanded.get(0);
            System.out.println(n);
            expanded.add(n);
            unexpanded.remove(0);
            if (n.state.isGoal()) {
                reportSolution(n, output);
                return;
            } else {
                ArrayList<PuzzleState> moveList = n.state.possibleMoves();
                for (PuzzleState gs : moveList) {
                    if ((Node.findNodeWithState(unexpanded, gs) == null) &&
                            (Node.findNodeWithState(expanded, gs) == null)) {
                        int newCost = n.getCost()+ 1;
                        Node newNode = new Node(gs, n, newCost);
                        unexpanded.add(newNode);
                    }
                }
            }
        }
        output.println("No solution found");
    }

    /*
       printSolution is a recursive method that prints all the states in a solution.
       It uses the parent links to trace from the goal to the initial state then prints
       each state as the recursion unwinds.
       Node n should be a node representing the goal state.
     */
    public void printSolution(Node n, PrintWriter output) {
        if (n.parent != null) printSolution(n.parent, output);
        output.println(n.state);
    }

    /*
       The number of expanded and unexpanded nodes along with solution with statistics are being printed
       by reportSolution function.
       The Node argument n should be a node representing the goal state.
     */
    public void reportSolution(Node n, PrintWriter output) {
        output.println("Solution found!");
        printSolution(n, output);
        output.println(n.getCost() + " Moves");
        output.println("Nodes expanded: " + this.expanded.size());
        output.println("Nodes unexpanded: " + this.unexpanded.size());
        output.println();
    }


    public static void main(String[] args) throws Exception {
        Solver problem = new Solver(PuzzleState.INITIAL_BOARD);
        File outFile = new File("output.txt");
        PrintWriter output = new PrintWriter(outFile);
        problem.solve(output);
        output.close();
    }
}
