import org.example.BSTs.BSTOperations;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.BSTs.RedBlackTree;
import org.junit.jupiter.api.Test;

public class RedBlackDraw {
    @Test
    public void testSmallRBTreeVisualization() {
        BSTOperations<Integer> tree = new RedBlackTree<>();

        int count = 20;
        for (int i = 0; i < count; i++) {
            assertTrue(tree.insert(i));
        }

        for (int i = 0; i < count; i++) {
            assertTrue(tree.search(i));
        }

        System.out.println("Tree Size: " + tree.size());
        System.out.println("Tree Height: " + tree.height());

        JFrame frame = new JFrame("Small Red-Black Tree Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(tree.draw());  // افترض draw() ترجع JPanel من نوع RBTreeVisualizer
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        try {
            Thread.sleep(5000);  // عرض النافذة 5 ثواني
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
