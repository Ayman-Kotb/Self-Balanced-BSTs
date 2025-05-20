package BSTs;

public class Node<T extends Comparable<T>> {
    public T key;
    public Node<T> left;
    public Node<T> right;
    public int height;

    public Node(T key) {
        this.key = key;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
}
