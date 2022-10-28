package com.computer_graphics;
import java.awt.*;

public class CirclePainter {
    public static void paintCircle( int r, int x_centre, int y_centre,PointPlotter p) {
        int x = r, y = 0;
        p.plotPoint(x + x_centre, y + y_centre);

        if (r > 0) {
            p.plotPoint(x + x_centre, -y + y_centre);
            p.plotPoint(y + x_centre, x + y_centre);
            p.plotPoint(-y + x_centre, x + y_centre);
        }

        int P = 1 - r;
        while (x > y) {
            y++;
            if (P <= 0) {
                P = P + 2 * y + 1;
            } else {
                x--;
                P = P + 2 * y - 2 * x + 1;
            }
            if (x < y)
                break;

            p.plotPoint(x + x_centre, y + y_centre);
            p.plotPoint(-x + x_centre, y + y_centre);

            p.plotPoint(x + x_centre, -y + y_centre);

            p.plotPoint(-x + x_centre, -y + y_centre);

            if (x != y) {
                p.plotPoint(y + x_centre, x + y_centre);
                p.plotPoint(-y + x_centre, x + y_centre);

                p.plotPoint(y + x_centre, -x + y_centre);

                p.plotPoint(-y + x_centre, -x + y_centre);
            }
        }
    }
}
