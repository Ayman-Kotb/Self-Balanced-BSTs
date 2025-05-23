package org.example.BSTs;

public class RBNode<T extends Comparable<T>>{
    public T key;
    public RBNode parent;
    public RBNode<T> left;
    public RBNode<T> right;
    public String color; // 0 for black, 1 for red


    public RBNode(T key, String color) {
        this.key = key;
        this.parent = null;
        this.left = null;
        this.right = null;
        this.color = color; // default to null
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    // a7la mesa 3lek ya janjooon
}
