// Polygon_.java
// Student: Ryan Mulholland
// Project 1 - CMSC 330
// Date: 2025-06-10
// Abstract base class for polygons with a color and drawing hook

import java.awt.*;

public abstract class Polygon_ extends Image {
    protected Polygon polygon;

    public Polygon_(Color color, int sides) {
        super(color);
        polygon = new Polygon(new int[sides], new int[sides], sides);
    }

    public void createPolygon(int[] x, int[] y) {
        polygon.xpoints = x;
        polygon.ypoints = y;
        polygon.npoints = x.length;
    }

    @Override
    public void draw(Graphics g) {
        drawPolygon(g, polygon);
    }

    public abstract void drawPolygon(Graphics g, Polygon p);
}

