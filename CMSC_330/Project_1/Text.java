// Text.java
// Displays a string label at a specific location

import java.awt.*;

public class Text extends Image {
    private int x, y;
    private String label;

    public Text(int[] rgb, int[] position, String text) {
        super(new Color(rgb[0], rgb[1], rgb[2]));
        this.x = position[0];
        this.y = position[1];
        this.label = text;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);  // fixed visibility issue
        g.drawString(label, x, y);
    }
}
