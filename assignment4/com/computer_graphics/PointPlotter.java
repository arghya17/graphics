package com.computer_graphics;

import java.awt.*;

public class PointPlotter {
    Graphics g;
    int[] origin;
    int pointDiameter;
    public int originX, originY, offset;

    public PointPlotter(Graphics g, int gridGap, int[] origin, int pointDiameter) {
        this.g = g;
        offset = gridGap;
        this.origin = origin;
        originX = origin[0];
        originY = origin[1];
    }

    public void setColor(Color c) {
        g.setColor(c);
    }

    public void plotPoint(int x, int y) {
        int incradius = 20;
        g.fillOval(originX + x * offset - (offset + incradius) / 8, originY - y * (offset) - (offset + incradius) / 8,
                (offset + incradius) / 4, (offset + incradius) / 4);
        System.out.println(offset);
    }

    public void plotPoint(int[] arr) {

        this.plotPoint(arr[0], arr[1]);
    }
}
