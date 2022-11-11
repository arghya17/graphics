// package assignment4;

import draw.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import com.computer_graphics.*;
import java.util.concurrent.ThreadLocalRandom;

public class line_clipping extends Applet implements MouseListener, MouseMotionListener, ActionListener {
    public Button button1, button2;
    public int offset;
    public int originX;
    public int originY;
    protected TextField t1, t2, t3;
    public draw obj;
    // Defining region codes
    public final int INSIDE = 0; // 0000
    public final int LEFT = 1; // 0001
    public final int RIGHT = 2; // 0010
    public final int BOTTOM = 4; // 0100
    public final int TOP = 8; // 1000

    // Defining x_max, y_max and x_min, y_min for
    // clipping rectangle. Since diagonal points are
    // enough to define a rectangle
    public int x_max = 10;
    public int y_max = 10;
    public int x_min = -10;
    public int y_min = -10;

    public void init() {
        button1 = new Button("Zoom out");
        add(button1);
        button1.addActionListener(this);
        button2 = new Button("Zoom in");
        add(button2);
        button2.addActionListener(this);
        offset = 40;
        addMouseListener(this);
        addMouseMotionListener(this);

        this.setSize(new Dimension(1920, 980));
        originX = (getX() + getWidth()) / 2;
        originY = (getY() + getHeight()) / 2;
        t1 = new TextField("10");
        t2 = new TextField("10");
        t3 = new TextField("20");
        t3.setText("40");
        t1.setText("0");
        t2.setText("0");
        add(t1);
        add(t2);
        add(t3);
        obj = new draw();
        obj.originX = originX;
        obj.originY = originY;
        obj.offset = offset;
        // p = new PointPlotter(g, offset, new int[] { originX, originY }, 120);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            System.out.println("zoom out is pressed");
            if (offset - 10 > 0) {
                offset = offset - 10;
            } else if (offset - 1 > 0) {
                offset = offset - 1;
            }
        } else if (e.getSource() == button2) {
            if (offset < 10) {
                offset = offset + 1;
            } else if (offset + 10 <= 80) {
                offset = offset + 10;
            }
            System.out.println("Zoom in is pressed");
        }
        // System.out.println(offset + "main");
        update();
        repaint();
    }

    public void mouseEntered(MouseEvent m) {
        // repaint();

    }

    public void mouseExited(MouseEvent m) {
        // repaint();
    }

    public void update() {
        originX = (getX() + getWidth()) / 2;
        originY = (getY() + getHeight()) / 2;
        obj.offset = offset;
        obj.originX = originX;
        obj.originY = originY;
    }

    public void mousePressed(MouseEvent m) {
        int x = (int) m.getX();
        int y = (int) m.getY();
        switch (m.getModifiers()) {
            case InputEvent.BUTTON1_MASK: {
                System.out.println("This is the left button");
                update();
                break;
            }
            case InputEvent.BUTTON2_MASK: {
                System.out.println("This is the middle button");
                update();
                break;
            }
            case InputEvent.BUTTON3_MASK: {
                System.out.println("This is the right button");
                break;
            }

        }
        update();
        repaint();
    }

    public void mouseReleased(MouseEvent m) {
        // repaint();
    }

    public void mouseDragged(MouseEvent m) {
        // repaint();
    }

    public void mouseMoved(MouseEvent m) {
        // repaint();
    }

    public void mouseClicked(MouseEvent m) {
        update();
        repaint();
    }

    double convert_to_radian(double angle) {
        return Math.PI * angle / 180;
    }

    public int computeCode(double x, double y) {
        // initialized as being inside
        int code = INSIDE;

        if (x < x_min) // to the left of rectangle
            code |= LEFT;
        else if (x > x_max) // to the right of rectangle
            code |= RIGHT;
        if (y < y_min) // below the rectangle
            code |= BOTTOM;
        else if (y > y_max) // above the rectangle
            code |= TOP;

        return code;
    }

    // Implementing Cohen-Sutherland algorithm
    // Clipping a line from P1 = (x2, y2) to P2 = (x2, y2)
    public int[] cohenSutherlandClip(int x1, int y1,
            int x2, int y2) {
        // Compute region codes for P1, P2
        int code1 = computeCode(x1, y1);
        int code2 = computeCode(x2, y2);

        // Initialize line as outside the rectangular window
        boolean accept = false;

        while (true) {
            if ((code1 == 0) && (code2 == 0)) {
                // If both endpoints lie within rectangle
                accept = true;
                break;
            } else if ((code1 & code2) != 0) {
                // If both endpoints are outside rectangle,
                // in same region
                break;
            } else {
                // Some segment of line lies within the
                // rectangle
                int code_out;
                int x = 0, y = 0;

                // At least one endpoint is outside the
                // rectangle, pick it.
                if (code1 != 0)
                    code_out = code1;
                else
                    code_out = code2;

                // Find intersection point;
                // using formulas y = y1 + slope * (x - x1),
                // x = x1 + (1 / slope) * (y - y1)
                if ((code_out & TOP) != 0) {
                    // point is above the clip rectangle
                    x = x1
                            + (x2 - x1) * (y_max - y1)
                                    / (y2 - y1);
                    y = y_max;
                } else if ((code_out & BOTTOM) != 0) {
                    // point is below the rectangle
                    x = x1
                            + (x2 - x1) * (y_min - y1)
                                    / (y2 - y1);
                    y = y_min;
                } else if ((code_out & RIGHT) != 0) {
                    // point is to the right of rectangle
                    y = y1
                            + (y2 - y1) * (x_max - x1)
                                    / (x2 - x1);
                    x = x_max;
                } else if ((code_out & LEFT) != 0) {
                    // point is to the left of rectangle
                    y = y1
                            + (y2 - y1) * (x_min - x1)
                                    / (x2 - x1);
                    x = x_min;
                }

                // Now intersection point x, y is found
                // We replace point outside rectangle
                // by intersection point
                if (code_out == code1) {
                    x1 = x;
                    y1 = y;
                    code1 = computeCode(x1, y1);
                } else {
                    x2 = x;
                    y2 = y;
                    code2 = computeCode(x2, y2);
                }
            }
        }
        if (accept) {
            System.out.println("Line accepted from " + x1
                    + ", " + y1 + " to " + x2
                    + ", " + y2);
            // Here the user can add code to display the
            // rectangle along with the accepted (portion
            // of) lines
            return new int[] { x1, y1, x2, y2 };
        }
        System.out.println("Line rejected");

        return new int[] {};

    }

    public void paint(Graphics g) {
        if (offset >= 10) {
            obj.plotgrid(getX(), getY(), getWidth(), getHeight(), g);
        }
        int x1 = Integer.parseInt(t1.getText());
        int y1 = Integer.parseInt(t2.getText());
        Color c = Color.red;
        obj.drawline(x_max, y_max, x_max, y_min, c, g);
        obj.drawline(x_max, y_min, x_min, y_min, c, g);
        obj.drawline(x_max, y_max, x_min, y_max, c, g);
        obj.drawline(x_min, y_min, x_max, y_min, c, g);
        obj.drawline(x_min, y_max, x_min, y_min, c, g);
        int x2 = -20;
        int y2 = -11;
        int x3 = 17;
        int y3 = -19;
        c = Color.blue;
        obj.drawline(x1, y1, x2, y2, c, g);
        obj.drawline(x2, y2, x3, y3, c, g);
        obj.drawline(x1, y1, x3, y3, c, g);
        int[] arr = cohenSutherlandClip(x1, y1, x2, y2);
        c = Color.yellow;
        if (arr.length != 0) {
            obj.drawline(arr[0], arr[1], arr[2], arr[3], c, g);
        }
        arr = cohenSutherlandClip(x2, y2, x3, y3);
        c = Color.yellow;
        if (arr.length != 0) {
            obj.drawline(arr[0], arr[1], arr[2], arr[3], c, g);
        }
        arr = cohenSutherlandClip(x1, y1, x3, y3);
        c = Color.yellow;
        if (arr.length != 0) {
            obj.drawline(arr[0], arr[1], arr[2], arr[3], c, g);
        }
    }
}