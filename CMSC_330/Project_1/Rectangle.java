// CMSC 330 Advanced Programming Languages
// Project 1 Skeleton
// UMGC CITE
// August 2021

import java.awt.*;

public class Rectangle extends Polygon_ {

    public Rectangle(Color color, Point topLeft, int height, int width) {
        super(color, 4);

        int x1 = topLeft.x;
        int y1 = topLeft.y;
        int x2 = x1 + width;
        int y2 = y1 + height;

        int[] x = { x1, x2, x2, x1 };
        int[] y = { y1, y1, y2, y2 };

        createPolygon(x, y);
    }

    @Override
    public void drawPolygon(Graphics g, Polygon p) {
        g.setColor(color);
        g.drawPolygon(p);
    }
}
