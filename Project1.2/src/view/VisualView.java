package view;

import entity.BinaryTree;
import entity.Node;
import utility.Utility;

import javax.swing.*;
import java.awt.*;

public class VisualView extends JFrame {
    private VisualView() {
        BinaryTree.countIndexDraw = 0;
        Utility.computeIndexDraw(BinaryTree.root);

        add(new Surface());
        setTitle("VisualView");
        setBounds(400, 100, 400, 400);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            VisualView visualView = new VisualView();
            visualView.setVisible(true);
        });
    }
}

class Surface extends JPanel {
    private final static int O_COORDINATE = 50;
    private final static int WIDTH = 80;
    private final static int HEIGHT = 40;
    private final static int GAP = 50;
    private final static int PADDING = 10;
    private final static int CORNER = 10;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        Font font = new Font("Arial", Font.BOLD, 20);
        g2d.setFont(font);

        drawAllNode(BinaryTree.root, g2d);
    }

    private void drawNode(Node node, Graphics2D g2d) {
        int a = O_COORDINATE + (node.getIndexDraw() - 1) * WIDTH;
        int b = O_COORDINATE + node.getDepth() * (HEIGHT + GAP);

        g2d.setColor(Color.GREEN);
        g2d.fillRoundRect(a, b, WIDTH, HEIGHT, CORNER, CORNER);

        g2d.setColor(Color.WHITE);
        g2d.drawString(node.getContent().getName(), a + PADDING, b + HEIGHT - PADDING);

        if (node.getParent() != null) {
            g2d.setColor(Color.BLUE);
            int c = O_COORDINATE + (node.getParent().getIndexDraw() - 1) * WIDTH;
            int d = O_COORDINATE + node.getParent().getDepth() * (HEIGHT + GAP);
            g2d.drawLine(a + WIDTH / 2, b, c + WIDTH / 2, d + HEIGHT);
        }
    }

    private void drawAllNode(Node node, Graphics2D g2d) {
        if (node != null) {
            drawNode(node, g2d);
            drawAllNode(node.getLeft(), g2d);
            drawAllNode(node.getRight(), g2d);
        }
    }
}
