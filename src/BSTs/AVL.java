package BSTs;


public class AVL implements BSTOperations {
    Node root ;
    int size = 0 ;
    public Node RRotation(Node x) {
        Node y = x.left;
        Node z = y.right;
        y.right = x;
        x.left = z;
        y.height = Math.max(y.left.height, y.right.height) + 1;
        x.height = Math.max(x.left.height, x.right.height) + 1;
        return y ;

    }
    public Node LRotation(Node x) {
        Node y = x.right;
        Node z = y.left;
        y.left = x;
        x.right = z;
        y.height = Math.max(y.left.height, y.right.height) + 1;
        x.height = Math.max(x.left.height, x.right.height) + 1;
        return y ;
    }
    public boolean isBalanced(Node x) {
        return (Math.abs(x.left.height - x.right.height) <= 1);
    }
    @Override
    public boolean insert(Node x) {
        if (x == null){
            throw new RuntimeException("Error , the node you want to insert is null");
            return false ;
        }
        root = recursiveInsert(root, x);
        size++ ;
        return true;
    }
    public Node recursiveInsert(Node root, Node y) {
        if (root == null) {
            root = y;
            return y;
        }
        if (root.key > y.key) {
            root.left = recursiveInsert(root.left, y);

        }
        else if (root.key < y.key){
            root.right = recursiveInsert(root.right, y);
        }
        else {
            return root;
        }
        root.height = Math.max(root.left.height, root.right.height) + 1;
        int balance = root.left.height - root.right.height;
        if (balance > 1 && root.left.key > y.key) { // LL
            return RRotation(root);
        }
        else if (balance < -1 && root.right.key < y.key) { // RR
            return LRotation(root);
        }
        else if (balance > 1 && root.left.key < y.key) { // LR
            root.left = LRotation(root.left);
            return RRotation(root);
        }
        else if (balance < -1 && root.right.key > y.key) { // RL
            root.right = RRotation(root.right);
            return LRotation(root);
        }
        return root;
    }

    @Override
    public boolean delete(Node x) {
        if (x == null){
            throw new RuntimeException("Error , the node you want to delete is null");
            return false ;
        }
        root = recursiveDelete(root , x);
        size-- ;
        return false;
    }
    public Node findMin(Node root) {
        if (root.left == null) {
            return root;
        }
        return findMin(root.left);
    }
    public Node recursiveDelete(Node root , Node x) {
        if (root == null){
            return null ;
        }
        if (root.key > x.key){
            root.left = recursiveDelete(root.left , x);
        }
        else if (root.key < x.key){
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
                Node min = findMin(root.right);
                root.key = min.key;
                root.right = recursiveDelete(root.right , min);
            }
        }
        root.height = Math.max(root.left.height, root.right.height) + 1;
        int balance = root.left.height - root.right.height;
        if (balance > 1 && root.left.left.height > root.left.right.height) { // LL
            return RRotation(root);
        }
        else if (balance < -1 && root.right.right.height > root.right.left.height) { // RR
            return LRotation(root);
        }
        else if (balance > 1 && root.left.right.height > root.left.left.height) { // LR
            root.left = LRotation(root.left);
            return RRotation(root);
        }
        else if (balance < -1 && root.right.left.height > root.right.right.height) { // RL
            root.right = RRotation(root.right); // RR
            return LRotation(root);           // LL
        }
        return root;
    }

    @Override
    public boolean search(Node x) {
        if (x == null){
            throw new RuntimeException("Error , the node you want to search is null");
            return false ;
        }
        return recursiveSearch(root , x);
    }
    public boolean recursiveSearch(Node root , Node x) {
        if (root == null){
            return false ;
        }
        if (x.key == root.key){
            return true ;
        }
        else if (x.key < root.key){
            return recursiveSearch(root.left , x);
        }
        else {
            return recursiveSearch(root.right , x);
        }
    }

    @Override
    public int size() {
        return size ;
    }

    @Override
    public int Height() {
        return root.height;
    }
    // eb3d yaad mn hna (-i-)
    // melkea khasa ll mon
}
