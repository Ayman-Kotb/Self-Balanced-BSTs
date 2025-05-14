package BSTs;

public class Node {
    public int key;
    public Node left;
    public Node right;
    public int height;

    public Node(int key) {
        this.key = key;
        this.left = null;
        this.right = null;
        this.height = 0;
    }
}
