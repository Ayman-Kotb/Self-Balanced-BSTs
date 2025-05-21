package BSTs;

public class AVLTree<T extends Comparable<T>> implements BSTOperations<T> {
    private Node<T> root;
    private int size;
    private int height;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public boolean insert(T key) {
        // To be implemented
        return false;
    }

    @Override
    public boolean delete(T key) {
        // To be implemented
        return false;
    }

    @Override
    public boolean search(T key) {
        // To be implemented
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int height() {
        return root == null ? 0 : root.height;
    }
}
