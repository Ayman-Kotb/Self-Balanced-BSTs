import org.example.BSTs.AVLTree;
import org.example.BSTs.BSTOperations;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AVLDraw {
    @Test
    public void testSmallTreeVisualization() {
        BSTOperations<Integer> tree = new AVLTree<>();

        int count = 20;
        for (int i = 0; i < count; i++) {
            assertTrue(tree.insert(i));
        }

        for (int i = 0; i < count; i++) {
            assertTrue(tree.search(i));
        }

        System.out.println("Tree Size: " + tree.size());
        System.out.println("Tree Height: " + tree.height());

        JFrame frame = new JFrame("Small AVL Tree Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(tree.draw());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
