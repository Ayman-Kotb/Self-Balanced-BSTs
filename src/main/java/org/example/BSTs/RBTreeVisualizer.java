package org.example.BSTs;

import javax.swing.*;
import java.awt.*;

public class RBTreeVisualizer<T extends Comparable<T>> extends JPanel {
    private final RBNode<T> root;
    private final int nodeRadius = 30;
    private final int verticalSpacing = 80;

    public RBTreeVisualizer(RBNode<T> root) {
        this.root = root;
        setPreferredSize(new Dimension(1000, 700));
        setBackground(Color.LIGHT_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawTree(g, root, getWidth() / 2, 50, getWidth() / 4);
    }

    private void drawTree(Graphics g, RBNode<T> node, int x, int y, int xOffset) {
        if (node == null) return;

        // Draw lines to children
        g.setColor(Color.BLACK);
        if (node.left != null) {
            int childX = x - xOffset;
            int childY = y + verticalSpacing;
            g.drawLine(x, y, childX, childY);
            drawTree(g, node.left, childX, childY, xOffset / 2);
        }

        if (node.right != null) {
            int childX = x + xOffset;
            int childY = y + verticalSpacing;
            g.drawLine(x, y, childX, childY);
            drawTree(g, node.right, childX, childY, xOffset / 2);
        }

        // Choose color based on node.color string
        if ("red".equalsIgnoreCase(node.color) || "1".equals(node.color)) {
            g.setColor(Color.RED);
        } else if ("black".equalsIgnoreCase(node.color) || "0".equals(node.color)) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.GRAY);
        }

        // Draw node circle
        g.fillOval(x - nodeRadius, y - nodeRadius, 2 * nodeRadius, 2 * nodeRadius);

        // Draw key
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 16));
        String text = node.key.toString();
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();
        g.drawString(text, x - textWidth / 2, y + textHeight / 4);
    }
}
