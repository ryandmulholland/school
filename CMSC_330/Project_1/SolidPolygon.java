// SolidPolygon.java
// Represents a colored polygon with fill

import java.awt.*;

public class SolidPolygon extends Polygon_ {
    protected int[] x;
    protected int[] y;

    public SolidPolygon(int sides, int[] rgb) {
        super(new Color(rgb[0], rgb[1], rgb[2]), sides);
        this.x = new int[sides];
        this.y = new int[sides];
    }

    @Override
    public void drawPolygon(Graphics g, Polygon p) {
        g.setColor(color);  // inherited from Image
        g.fillPolygon(x, y, x.length);
    }
}
