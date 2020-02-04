import java.util.ArrayList;

/*
The class Node represents nodes.
Node class represents nodes that are essential features to the gameState class and a reference to
the node's parent node. The latter is used to assemble and output the solution path once the goal sate has been reached.
 */

public class Node {
    PuzzleState state;    // The state associated with the node
    Node parent;        // The node from which this node was reached.
    private int cost;   // The cost of reaching this node from the initial node.

    /*
      New node created by the constructor.
     */
    public Node(PuzzleState state, Node parent, int cost) {
        this.state = state;
        this.parent = parent;
        this.cost = cost;
    }

    /*
      Constructor used to create initial node.
     */
    public Node(PuzzleState state) {
        this(state,null,0);
    }

    public int getCost() {
        return cost;
    }

    public String toString() {
        return "Node:" + state + " ";
    }

    /*
       A list of nodes are given as first argument. findNodeWithState searches the list and finds if any node
       is specified as second argument.
       If such a node exists in the list, the first argument is returned.
       Otherwise null is returned.
     */
    public static Node findNodeWithState(ArrayList<Node> nodeList, PuzzleState gs) {
        for (Node n : nodeList) {
            if (gs.sameBoard(n.state)) return n;
        }
        return null;
    }

}
