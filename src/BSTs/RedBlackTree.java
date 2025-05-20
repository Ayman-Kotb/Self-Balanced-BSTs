package BSTs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RedBlackTree<T extends Comparable<T>> implements BSTOperations<T> {
    private ColoredNode<T> root;
    private int size;
    private int height;

    public RedBlackTree() {
        this.root = null;
        this.size = 0;
    }

    public boolean insert(T key) {
        if (root == null) {
            root = new ColoredNode<>(key, "red");
            size++;
            return true;
        }

        Node<T> current = root;
        while (true) {
            int cmp = key.compareTo(current.key);

            if (cmp == 0) {
                // Key already exists
                return false;
            } else if (cmp < 0) {
                if (current.left == null) {
                    current.left = new ColoredNode<>(key, "black");
                    size++;
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new ColoredNode<>(key, "red");
                    size++;
                    return true;
                }
                current = current.right;
            }
        }
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
    public int Height() {
        return root == null ? 0 : root.height;
    }


    public void printPrettyTree() {
        int maxLevel = getHeight((ColoredNode<T>) root);
        printNodes(Collections.singletonList((ColoredNode<T>) root), 1, maxLevel);
    }

    private void printNodes(List<ColoredNode<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || allNull(nodes)) return;

        int floor = maxLevel - level;
        int edgeLines = (int) Math.pow(2, Math.max(floor - 1, 0));
        int firstSpaces = (int) Math.pow(2, floor) - 1;
        int betweenSpaces = (int) Math.pow(2, floor + 1) - 1;

        printWhitespaces(firstSpaces);

        List<ColoredNode<T>> newNodes = new ArrayList<>();
        for (ColoredNode<T> node : nodes) {
            if (node != null) {
                String color = "red".equals(node.color) ? "\u001B[31m" : "\u001B[30m"; // ANSI red or black
                System.out.print(color + node.key + "\u001B[0m");
                newNodes.add((ColoredNode<T>) node.left);
                newNodes.add((ColoredNode<T>) node.right);
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
                ColoredNode<T> node = nodes.get(j);

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

    private boolean allNull(List<ColoredNode<T>> list) {
        for (ColoredNode<T> node : list) {
            if (node != null) return false;
        }
        return true;
    }

    private int getHeight(ColoredNode<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeight((ColoredNode<T>) node.left), getHeight((ColoredNode<T>) node.right));
    }

}

