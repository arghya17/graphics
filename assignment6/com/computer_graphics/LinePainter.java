package com.computer_graphics;
import java.awt.*;
import com.computer_graphics.*;

public class LinePainter {
    public static void paintLine(int[] p0, int[] p1,PointPlotter p) {
        int x0 = p0[0], y0 = p0[1];
        int x1 = p1[0], y1 = p1[1];
        int dx = x1 - x0;
        int dy = y1 - y0;
        int total_steps = Math.max(Math.abs(dx), Math.abs(dy));
        float x_incr = (float) dx / total_steps;
        float y_incr = (float) dy / total_steps;
        float x = x0;
        float y = y0;
        for (int number_of_steps = 0; number_of_steps <= total_steps; number_of_steps++) {
            p.plotPoint(new int[]{Math.round(x), Math.round(y)});
            x += x_incr;
            y += y_incr;
        }
    }
    
}
