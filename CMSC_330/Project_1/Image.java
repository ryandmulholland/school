// Image.java
// Abstract base class for drawable images

import java.awt.*;

public abstract class Image {
    protected Color color;  // was private, now protected

    public Image(Color color) {
        this.color = color;
    }

    public abstract void draw(Graphics g);
}
