// Parallelogram.java
// Student: Ryan Mulholland
// Project 1 - CMSC 330
// Date: 2025-06-10
// Represents a slanted four-sided polygon using two corners and an x-offset

public class Parallelogram extends SolidPolygon {

    public Parallelogram(int[] rgb, int[] upperLeft, int[] lowerRight, int offset) {
        super(4, rgb);

        int x1 = upperLeft[0];
        int y1 = upperLeft[1];
        int x2 = lowerRight[0];
        int y2 = lowerRight[1];

        // Four corners of the parallelogram
        x[0] = x1;
        y[0] = y1;

        x[1] = x2;
        y[1] = y1;

        x[2] = x2 + offset;
        y[2] = y2;

        x[3] = x1 + offset;
        y[3] = y2;
    }
}
