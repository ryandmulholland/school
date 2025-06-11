// RegularPolygon.java
// Student: Ryan Mulholland
// Project 1 - CMSC 330
// Date: 2025-06-10
// Draws a regular polygon (equal sides, equal angles) given center, sides, and radius

public class RegularPolygon extends SolidPolygon {

    public RegularPolygon(int[] rgb, int[] center, int sides, int radius) {
        super(sides, rgb);

        double angleStep = 2 * Math.PI / sides;
        int cx = center[0];
        int cy = center[1];

        for (int i = 0; i < sides; i++) {
            x[i] = cx + (int)(radius * Math.cos(i * angleStep));
            y[i] = cy + (int)(radius * Math.sin(i * angleStep));
        }
    }
}
