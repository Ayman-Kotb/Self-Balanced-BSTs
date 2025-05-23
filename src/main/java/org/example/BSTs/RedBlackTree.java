package org.example.BSTs;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RedBlackTree<T extends Comparable<T>> implements BSTOperations<T> {
    protected RBNode<T> root;
    private int size;

    public RedBlackTree() {
        this.root = null;
        this.size = 0;
    }
//---------------------------------------------------------------------
    //INSERT
    public boolean insert(T key) {
        RBNode<T> newNode = new RBNode<>(key, "red");
        RBNode<T> current = root;
        boolean inserted = false;

        if (root == null) {
            root = newNode;
            newNode.parent=null;
            size++;
            return true;
        }

        while (!inserted) {
            int cmp = key.compareTo(current.key);

            if (cmp == 0) {
                // Key already exists
                return false;
            }
            else if (cmp < 0) {
                if (current.left == null) {
                    newNode.parent = current;
                    current.left = newNode;
                    inserted = true;
                }
                else current = current.left;
            }
            else {
                if (current.right == null) {
                    newNode.parent = current;
                    current.right = newNode;
                    inserted = true;
                }
                else current = current.right;
            }

            if (inserted) {
                size++;
                insertFixUp(newNode);
                return true;
            }
        }
        return false;
    }

    //----------------------------------------------------------------------------
@Override
    public boolean delete(T key) {
        RBNode<T> z = findNode(key); // custom helper to locate the node
        if (z == null) {
            return false;
        }

        RBNode<T> y = z;
        String yOriginalColor = y.getColor();
        RBNode<T> x;

        if (z.left == null) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == null) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.getColor();
            x = y.right;

            if (y.parent == z) {
                if (x != null) x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                if (y.right != null) y.right.parent = y;
            }

            transplant(z, y);
            y.left = z.left;
            if (y.left != null) y.left.parent = y;
            y.setColor(z.getColor());
        }

        if ("black".equals(yOriginalColor)) {
            deleteFixUp(x);
        }

        size--;
        return true;
    }




    private void deleteFixUp(RBNode<T> x) {
        while (x != root && x != null && x.parent != null && x.getColor().equals("black")) {
            if (x == x.parent.left) {
                RBNode<T> w = x.parent.right;

                // Case 1
                if (w != null && w.getColor().equals("red")) {
                    w.setColor("black");
                    x.parent.setColor("red");
                    leftRotate(x.parent);
                    w = x.parent.right;
                }

                // Case 2
                if ((w.left == null || w.left.getColor().equals("black")) &&
                        (w.right == null || w.right.getColor().equals("black"))) {
                    if (w != null) w.setColor("red");
                    x = x.parent;
                } else {
                    // Case 3
                    if (w.right == null || w.right.getColor().equals("black")) {
                        if (w.left != null) w.left.setColor("black");
                        if (w != null) {
                            w.setColor("red");
                            rightRotate(w);
                        }
                        w = x.parent.right;
                    }

                    // Case 4
                    if (w != null) {
                        w.setColor(x.parent.getColor());
                        x.parent.setColor("black");
                        if (w.right != null) w.right.setColor("black");
                        leftRotate(x.parent);
                    }
                    x = root;
                }
            } else {
                RBNode<T> w = x.parent.left;

                if (w != null && w.getColor().equals("red")) {
                    w.setColor("black");
                    x.parent.setColor("red");
                    rightRotate(x.parent);
                    w = x.parent.left;
                }

                if ((w.right == null || w.right.getColor().equals("black")) &&
                        (w.left == null || w.left.getColor().equals("black"))) {
                    if (w != null) w.setColor("red");
                    x = x.parent;
                } else {
                    if (w.left == null || w.left.getColor().equals("black")) {
                        if (w.right != null) w.right.setColor("black");
                        if (w != null) {
                            w.setColor("red");
                            leftRotate(w);
                        }
                        w = x.parent.left;
                    }

                    if (w != null) {
                        w.setColor(x.parent.getColor());
                        x.parent.setColor("black");
                        if (w.left != null) w.left.setColor("black");
                        rightRotate(x.parent);
                    }
                    x = root;
                }
            }
        }

        if (x != null) x.setColor("black");
    }







    private RBNode<T> findNode(T key) {
        RBNode<T> current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp == 0) return current;
            else if (cmp < 0) current = current.left;
            else current = current.right;
        }
        return null;
    }


    //-------------------------------------------------------------------------------------

    @Override
    public boolean search(T key) {
        return searchRecursive(root, key);
    }


    private boolean searchRecursive(RBNode<T> root,T key) {
        if (root == null) return false;
        int cmp = key.compareTo(root.key);
        if(cmp==0)return true;
        else if(cmp>0) return searchRecursive(root.right, key);
        else return searchRecursive( root.left, key);
    }


//---------------------------------------------------------------------------------------------------


    @Override
    public int size() {
        return this.size;
    }


//---------------------------------------------------------------------------------------------------

    @Override
    public int height() {
        return getHeight(root);
    }

    public int getHeight(RBNode<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

//---------------------------------------------------------------------------------------------------

    public void printPrettyTree() {
        int maxLevel = getHeight(root);
        printNodes(Collections.singletonList( root), 1, maxLevel);
    }

    private void printNodes(List<RBNode<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || allNull(nodes)) return;

        int floor = maxLevel - level;
        int edgeLines = (int) Math.pow(2, Math.max(floor - 1, 0));
        int firstSpaces = (int) Math.pow(2, floor) - 1;
        int betweenSpaces = (int) Math.pow(2, floor + 1) - 1;

        printWhitespaces(firstSpaces);

        List<RBNode<T>> newNodes = new ArrayList<>();
        for (RBNode<T> node : nodes) {
            if (node != null) {
                String color = "red".equals(node.color) ? "\u001B[31m" : "\u001B[30m"; // ANSI red or black
                System.out.print(color + node.key + "\u001B[0m");
                newNodes.add((RBNode<T>) node.left);
                newNodes.add((RBNode<T>) node.right);
            } else {
                System.out.print(" ");
                newNodes.add(null);
                newNodes.add(null);
            }
            printWhitespaces(betweenSpaces);
        }
        System.out.println();

        // Print branches
        for (int i = 1; i <= edgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                RBNode<T> node = nodes.get(j);

                if (node == null) {
                    printWhitespaces(edgeLines * 2 + i + 1);
                    continue;
                }

                System.out.print(node.left != null ? "/" : " ");
                printWhitespaces(i * 2 - 1);
                System.out.print(node.right != null ? "\\" : " ");
                printWhitespaces(edgeLines * 2 - i);
            }
            System.out.println();
        }

        printNodes(newNodes, level + 1, maxLevel);
    }

    private void printWhitespaces(int count) {
        for (int i = 0; i < count; i++) System.out.print(" ");
    }

    private boolean allNull(List<RBNode<T>> list) {
        for (RBNode<T> node : list) {
            if (node != null) return false;
        }
        return true;
    }

    //-------------------------------------------------------------
    //Helper Rotate Functions
    private void leftRotate(RBNode<T> x){
        RBNode<T> y = x.right;
        x.right = y.left;
        if(y.left!=null){
            y.left.parent = x;
        }
        y.parent = x.parent;

        if(x.parent == null){
            root=y;
        }
        else if(x==x.parent.left){
            x.parent.left=y;
        }
        else{
            x.parent.right=y;
        }
        y.left=x;
        x.parent=y;
    }

    private void rightRotate(RBNode<T> y) {
        RBNode<T> x = y.left;
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }
        x.parent = y.parent;

        if (y.parent == null) {
            root = x;
        } else if (y == y.parent.right) {
            y.parent.right = x;
        } else {
            y.parent.left = x;
        }

        x.right = y;
        y.parent = x;
    }

    //----------------------------------------------------------------
    //Helper INSERT Fixup Functions
    public void insertFixUp(RBNode<T> z) {
        while (z != root && z.parent != null && z.parent.parent != null && "red".equals(z.parent.getColor())) {
            if (z.parent == z.parent.parent.left) {
                RBNode<T> y = z.parent.parent.right; // uncle
                if (y != null && "red".equals(y.getColor())) {
                    z.parent.setColor("black");
                    y.setColor("black");
                    z.parent.parent.setColor("red");
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right) {
                        z = z.parent;
                        leftRotate(z);
                    }
                    z.parent.setColor("black");
                    z.parent.parent.setColor("red");
                    rightRotate(z.parent.parent);
                }
            } else {
                RBNode<T> y = z.parent.parent.left; // uncle
                if (y != null && "red".equals(y.getColor())) {
                    z.parent.setColor("black");
                    y.setColor("black");
                    z.parent.parent.setColor("red");
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.setColor("black");
                    z.parent.parent.setColor("red");
                    leftRotate(z.parent.parent);
                }
            }
        }
        root.setColor("black");
    }
    //----------------------------------------------------------------
    //HELPER DELETE FUNCTIONS
    public void transplant(RBNode<T> u, RBNode<T> v){
        if (u.parent==null) root=v;
        else if(u==u.parent.left) u.parent.left=v;
        else u.parent.right=v;
        if (v != null) {
            v.parent = u.parent;
        }
    }

    public RBNode<T> minimum(RBNode<T> x) {
        while (x.left != null)
            x = x.left;
        return x;
    }
    //----------------------------------------------------
    //kero is here
    @Override
    public JPanel draw(){
        return new RBTreeVisualizer<>(root);
    }
}

