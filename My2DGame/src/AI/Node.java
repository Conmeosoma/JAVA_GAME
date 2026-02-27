package AI;

public class Node {

    Node parent;
    public int col;
    public int row;
    int gCost;
    int hCost;
    boolean solid;
    boolean open;
    boolean checked;
    public int fCost;

    public Node(int col, int row) {
        this.col = col;
        this.row = row;
    }

}
