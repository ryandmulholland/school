import java.awt.*;

public class RightTriangle extends Polygon_ {

    public RightTriangle(Color color, Point topLeft, int height, int width) {
        super(color, 3);

        int[] x = new int[3];
        int[] y = new int[3];

        x[0] = topLeft.x;
        y[0] = topLeft.y;

        x[1] = topLeft.x + width;
        y[1] = topLeft.y;

        x[2] = topLeft.x;
        y[2] = topLeft.y + height;

        createPolygon(x, y);
    }

    @Override
    public void drawPolygon(Graphics g, Polygon p) {
        g.setColor(color);
        g.drawPolygon(p);
    }
}
