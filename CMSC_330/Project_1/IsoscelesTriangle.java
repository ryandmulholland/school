// IsoscelesTriangle.java
// Student: Ryan Mulholland
// Project 1 - CMSC 330
// Date: 2025-06-10
// Represents an isosceles triangle using a SolidPolygon

import java.awt.*;

public class IsoscelesTriangle extends SolidPolygon {

    public IsoscelesTriangle(int[] rgb, int[] topVertex, int height, int width) {
        super(3, rgb);

        int xTop = topVertex[0];
        int yTop = topVertex[1];

        x[0] = xTop;
        y[0] = yTop;

        x[1] = xTop - width / 2;
        y[1] = yTop + height;

        x[2] = xTop + width / 2;
        y[2] = yTop + height;

        // ✅ This is the missing piece
        createPolygon(x, y);
    }
}


