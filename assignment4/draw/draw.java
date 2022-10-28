package draw;

import java.applet.*;
import java.awt.*;

public class draw {

    public int originX;
    public int originY;
    public int offset;

    public void plotgrid(int X, int Y, int Width, int Height, Graphics g) {
        originX = (X + Width) / 2;
        originY = (Y + Height) / 2;
        g.setColor(Color.red);
        Font f = new Font("Nano", 4, 24);
        g.drawLine(-Width + originX, 0 + originY, Width + originX, 0 + originY);
        g.drawLine(0 + originX, -Height + originY, 0 + originX, Height + originY);
        g.drawString("(0,0)", originX, originY);
        g.setColor(Color.black);
        int yCoord = 0;
        for (int i = originX; i < originX * 2 + Width; i += offset) {
            g.setColor(Color.DARK_GRAY);
            g.drawLine(i, originY * 2 - Height, i, originY * 2 + Height);
            if (offset > 30 && i != originX) {
                g.drawString(String.valueOf(-1 * yCoord), i - 15, originY + 15);

            }
            yCoord--;
        }
        yCoord = 0;
        for (int i = originX; i > originX * 2 - Width; i -= offset) {
            g.setColor(Color.DARK_GRAY);
            g.drawLine(i, originY * 2 - Height, i, originY * 2 + Height);
            if (offset > 30 && i != originX) {
                g.drawString(String.valueOf(-1 * yCoord), i - 15, originY + 15);

            }
            yCoord++;
        }

        int xCoord = 0;
        for (int i = originY; i < originY * 2 + Height; i += offset) {
            g.setColor(Color.DARK_GRAY);
            g.drawLine(originX * 2 - Width, i, originX * 2 + Width, i);
            if (offset > 30 && i != originY) {
                g.drawString(String.valueOf(-1 * xCoord), originX + 10, i + 15);
            }
            xCoord++;

        }
        xCoord = 0;
        for (int i = originY; i > originY * 2 - Height; i -= offset) {
            g.setColor(Color.DARK_GRAY);
            g.drawLine(originX * 2 - Width, i, originX * 2 + Width, i);
            if (offset > 30 && i != originY) {
                g.drawString(String.valueOf(-1 * xCoord), originX + 10, i + 15);
            }
            xCoord--;
        }
    }

    public void plotPoint(int x, int y, Color c, Graphics g) {
        g.setColor(c);
        int incradius = 20;
        // if (x == 10 && y == 10)
        // System.out.println(
        // offset + " plotPoint" + x + "x " + y + " y " + originX + " originX " +
        // originY + " originY ");
        int x1 = originX + x * offset - (offset + incradius) / 8;
        int y1 = originY - y * (offset) - (offset + incradius) / 8;
        g.fillOval(x1, y1,
                (offset + incradius) / 4, (offset + incradius) / 4);
        // if (x == 10 && y == 10)
        // System.out.println(x1 + " Plotpoint " + y1);

    }

    public void drawline(int x1, int y1, int x2, int y2, Graphics g) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        double stepx;
        double stepy;
        int n = 0;
        if (Math.abs(dx) > Math.abs(dy)) {
            stepx = dx / Math.abs(dx);
            stepy = (double) dy / Math.abs(dx);
            n = Math.abs(dx);
        } else {
            stepx = (double) dx / Math.abs(dy);
            stepy = dy / Math.abs(dy);
            n = Math.abs(dy);
        }
        int i = 0;
        double x, y;
        for (i = 0; i <= n; i++) {
            x = x1 + i * stepx;
            y = y1 + i * stepy;
            plotPoint((int) Math.round(x), (int) Math.round(y), Color.yellow, g);
        }

    }

    public void drawcircle(int r, int x_centre, int y_centre, Graphics g) {
        int x = r;
        int y = 0;
        int p = 1 - r;
        while (x > y) {
            y++;
            // Mid-point is inside or on the perimeter
            if (p <= 0)
                p = p + 2 * y + 1;

            // Mid-point is outside the perimeter
            else {
                x--;
                p = p + 2 * y - 2 * x + 1;
            }
            if (x < y) {
                break;
            }

            // Printing the generated point and its
            // reflection in the other octants after
            // translation
            plotPoint((x + x_centre), (y + y_centre), Color.blue, g);

            plotPoint((-x + x_centre), (y + y_centre), Color.blue, g);

            plotPoint((x + x_centre), (-y + y_centre), Color.blue, g);

            plotPoint((-x + x_centre), (-y + y_centre), Color.blue, g);

            // If the generated point is on the
            // line x = y then the perimeter points
            // have already been printed
            if (x != y) {

                plotPoint((y + x_centre), (x + y_centre), Color.BLUE, g);

                plotPoint((-y + x_centre), (x + y_centre), Color.blue, g);

                plotPoint((y + x_centre), (-x + y_centre), Color.blue, g);

                plotPoint((-y + x_centre), (-x + y_centre), Color.blue, g);
            }

        }
    }

    public void drawellipse(int rx, int ry, int xc, int yc, Graphics g) {
        float dx, dy, d1, d2, x, y;
        x = 0;
        y = ry;

        // Initial decision parameter of region 1
        d1 = (ry * ry) - (rx * rx * ry) +
                (0.25f * rx * rx);
        dx = 2 * ry * ry * x;
        dy = 2 * rx * rx * y;
        Color c = Color.yellow;
        // For region 1
        while (dx < dy) {

            // Print points based on 4-way symmetry
            plotPoint((int) (x + xc), (int) (y + yc), c, g);
            plotPoint((int) (-x + xc), (int) (y + yc), c, g);
            plotPoint((int) ((x + xc)), (int) ((-y + yc)), c, g);
            plotPoint((int) ((-x + xc)), (int) ((-y + yc)), c, g);

            // Checking and updating value of
            // decision parameter based on algorithm
            if (d1 < 0) {
                x++;
                dx = dx + (2 * ry * ry);
                d1 = d1 + dx + (ry * ry);
            } else {
                x++;
                y--;
                dx = dx + (2 * ry * ry);
                dy = dy - (2 * rx * rx);
                d1 = d1 + dx - dy + (ry * ry);
            }
        }

        // Decision parameter of region 2
        d2 = ((ry * ry) * ((x + 0.5f) * (x + 0.5f)))
                + ((rx * rx) * ((y - 1) * (y - 1)))
                - (rx * rx * ry * ry);

        // Plotting points of region 2
        while (y >= 0) {

            // printing points based on 4-way symmetry
            plotPoint((int) ((x + xc)), (int) ((y + yc)), c, g);
            plotPoint((int) ((-x + xc)), (int) ((y + yc)), c, g);
            plotPoint((int) ((x + xc)), (int) ((-y + yc)), c, g);
            plotPoint((int) ((-x + xc)), (int) ((-y + yc)), c, g);

            // Checking and updating parameter
            // value based on algorithm
            if (d2 > 0) {
                y--;
                dy = dy - (2 * rx * rx);
                d2 = d2 + (rx * rx) - dy;
            } else {
                y--;
                x++;
                dx = dx + (2 * ry * ry);
                dy = dy - (2 * rx * rx);
                d2 = d2 + dx - dy + (rx * rx);
            }
        }
    }
}