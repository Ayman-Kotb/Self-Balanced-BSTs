package BSTs;

public class Node<T extends Comparable<T>> {
    public T key;
    public Node<T> left;
    public Node<T> right;
    public int height;
    public String color; // "red", "black", or null for AVL

    // Constructor for AVL Tree (no color)
    public Node(T key) {
        this.key = key;
        this.left = null;
        this.right = null;
        this.height = 1;
        this.color = null; // default to null
    }

    // Constructor for Red-Black Tree (with color)
    public Node(T key, String color) {
        this.key = key;
        this.left = null;
        this.right = null;
        this.height = 1;
        this.color = color.toLowerCase(); // "red" or "black"
    }
}
