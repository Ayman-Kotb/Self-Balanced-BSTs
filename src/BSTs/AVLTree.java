package BSTs;

import javax.swing.*;

public class AVLTree<T extends Comparable<T>> implements BSTOperations<T> {
    private Node<T> root;
    private int size;
    public AVLTree() {
        this.root = null;
        this.size = 0;
    }
    private int height(Node<T> node) {
        return node == null ? 0 : node.height;
    }
    private int getBalance(Node<T> node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }
    public Node<T> RRotation(Node<T> x) {
        Node<T> y = x.left;
        Node<T> z = y.right;
        y.right = x;
        x.left = z;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height =  Math.max(height(y.left), height(y.right)) + 1;
        return y ;
    }

    public Node<T> LRotation(Node<T> x) {
        Node<T> y = x.right;
        Node<T> z = y.left;
        y.left = x;
        x.right = z;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y ;
    }

    public boolean isBalanced(Node<T> x) {
        if (x == null) return true;
        return Math.abs(height(x.left) - height(x.right)) <= 1;
    }


    @Override
    public boolean insert(T key) {
        // To be implemented
        if (key == null){
            return false ;
        }
        if (!search(key)){
            Node<T> x = new Node<>(key);
            root = recursiveInsert(root, x);
            size++ ;
            return true;
        }
        return false;
    }
    public Node<T> recursiveInsert(Node<T> root, Node<T> y) {
        if (root == null) {
            root = y;
            return y;
        }
        int cmp = y.key.compareTo(root.key);
        if (cmp < 0) {
            root.left = recursiveInsert(root.left, y);

        }
        else if (cmp > 0) {
            root.right = recursiveInsert(root.right, y);
        }
        else {
            return root;
        }
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);
        if (balance > 1 && root.left.key.compareTo(y.key) >0) { // LL
            return RRotation(root);
        }
        else if (balance < -1 && root.right.key.compareTo(y.key) <0) { // RR
            return LRotation(root);
        }
        else if (balance > 1 && root.left.key.compareTo(y.key)<0) { // LR
            root.left = LRotation(root.left);
            return RRotation(root);
        }
        else if (balance < -1 && root.right.key.compareTo( y.key)>0) { // RL
            root.right = RRotation(root.right);
            return LRotation(root);
        }
        return root;
    }


    @Override
    public boolean delete(T key) {
        // To be implemented
        if (key == null){
            return false ;
        }
        if (search(key)){
            Node<T> x = new Node<>(key);
            root = recursiveDelete(root , x);
            size-- ;
            return true;
        }
        return false;
    }
    public Node<T> findMin(Node<T> root) {
        if (root.left == null) {
            return root;
        }
        return findMin(root.left);
    }
    public Node<T> recursiveDelete(Node<T> root , Node<T> x) {
        if (root == null){
            return null ;
        }
        int cmp = x.key.compareTo(root.key);
        if (cmp < 0){
            root.left = recursiveDelete(root.left , x);
        }
        else if (cmp>0){
            root.right = recursiveDelete(root.right , x);
        }
        else { // equal keys
            if (root.left == null && root.right == null){ // no children
                return null ;
            }
            else if (root.left == null ){ // only  right child
                return root.right ;
            }
            else if (root.right == null){ // only left child
                return root.left ;
            }
            else { // 2 children
                Node<T> min = findMin(root.right);
                root.key = min.key;
                root.right = recursiveDelete(root.right , min);
            }
        }
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);
        if (balance > 1 && height(root.left.left) > height(root.left.right)) { // LL
            return RRotation(root);
        }
        else if (balance < -1 && height(root.right.right) > height(root.right.left)) { // RR
            return LRotation(root);
        }
        else if (balance > 1 && height(root.left.right) > height(root.left.left)) { // LR
            root.left = LRotation(root.left);
            return RRotation(root);
        }
        else if (balance < -1 && height(root.right.left) > height(root.right.right)) { // RL
            root.right = RRotation(root.right); // RR
            return LRotation(root);           // LL
        }
        return root;
    }

    @Override
    public boolean search(T key) {
        if (key == null){
            return false ;
        }
        Node<T> x = new Node<>(key);
        return recursiveSearch(root , x);
    }
    public boolean recursiveSearch(Node<T> root , Node<T> x) {
        if (root == null){
            return false ;
        }
        int cmp = x.key.compareTo(root.key);
        if (cmp == 0){
            return true ;
        }
        else if (cmp < 0){
            return recursiveSearch(root.left , x);
        }
        else {
            return recursiveSearch(root.right , x);
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int height() {
        return root == null ? 0 : root.height;
    }
    public void visualize() {
        JFrame frame = new JFrame("AVL Tree Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new TreeVisualizer<>(root));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
