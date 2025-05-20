package BSTs;

public class ColoredNode<T extends Comparable<T>> extends Node<T> {
    public String color; // 0 for black, 1 for red

    public ColoredNode(T key, String color) {
        super(key);
        this.color = color;
    }
    // a7la mesa 3lek ya janjooon
}
