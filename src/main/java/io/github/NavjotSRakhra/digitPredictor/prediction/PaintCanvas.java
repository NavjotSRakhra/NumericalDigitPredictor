package io.github.NavjotSRakhra.digitPredictor.prediction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class PaintCanvas extends JPanel {
    private final int circleRadius;
    private final List<CircleToPaint> circlesPainted;
    private int x, y;

    public PaintCanvas() {
        x = -1;
        y = -1;
        circleRadius = 50;
        circlesPainted = new ArrayList<>();
        setFocusable(true);
        requestFocusInWindow();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                paintArea(e.getX(), e.getY());
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                paintArea(e.getX(), e.getY());
            }
        });
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                resetCanvas(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    private void resetCanvas(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_R) {
            circlesPainted.clear();
            repaint();
        }
    }

    private void paintArea(int x, int y) {
        this.x = x;
        this.y = y;
        repaint(x - circleRadius, y - circleRadius, circleRadius * 2, circleRadius * 2);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(700, 700);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (x == -1 || y == -1) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 700, 700);
            for (CircleToPaint circle : circlesPainted) {
                circle.paintTo(g);
            }
            return;
        }
        g.setColor(new Color(255, 255, 255, 0xff));
        g.fillRect(x - circleRadius, y - circleRadius, circleRadius * 2, circleRadius * 2);

        circlesPainted.forEach(circle -> circle.paintTo(g));

        CircleToPaint circle = new CircleToPaint(circleRadius, x, y);
        circle.paintTo(g);
        circlesPainted.add(circle);
        x = -1;
        y = -1;
    }
}
