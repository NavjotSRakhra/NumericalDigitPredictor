package io.github.NavjotSRakhra.prediction;

import java.awt.*;

public class CircleToPaint {
    private int radius;
    private int x;
    private int y;

    public CircleToPaint(int radius, int x, int y) {
        this.radius = radius;
        this.x = x;
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void paintTo(Graphics g) {
        g.setColor(new Color(0, 0, 0, 0xff));
        g.fillOval(getX(), getY(), getRadius(), getRadius());
    }
}
