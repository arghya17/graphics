
// // package assignment4;
// import java.util.*;
// import draw.*;
// import java.applet.*;
// import java.awt.*;
// import java.awt.event.*;
// import com.computer_graphics.*;
// import java.util.concurrent.ThreadLocalRandom;
// // import java.util.Arrays;

// public class b_spline extends Applet implements MouseListener, MouseMotionListener, ActionListener {
//     public Button button1, button2;
//     public int offset;
//     public int originX;
//     public int originY;
//     protected TextField t1, t2, t3;
//     public draw obj;

//     double[][] P;
//     double[] T;
//     int _k = 3;

//     public void init() {
//         button1 = new Button("Zoom out");
//         add(button1);
//         button1.addActionListener(this);
//         button2 = new Button("Zoom in");
//         add(button2);
//         button2.addActionListener(this);
//         offset = 40;
//         addMouseListener(this);
//         addMouseMotionListener(this);

//         this.setSize(new Dimension(1920, 980));
//         originX = (getX() + getWidth()) / 2;
//         originY = (getY() + getHeight()) / 2;
//         t1 = new TextField("10");
//         t2 = new TextField("10");
//         t3 = new TextField("20");
//         t3.setText("40");
//         t1.setText("0");
//         t2.setText("0");
//         add(t1);
//         add(t2);
//         add(t3);
//         obj = new draw();
//         obj.originX = originX;
//         obj.originY = originY;
//         obj.offset = offset;

//         P = new double[4][2];
//         P[0][0] = 10;
//         P[0][1] = 10;
//         P[1][0] = 5;
//         P[1][1] = 5;
//         P[2][0] = 10;
//         P[2][1] = 20;
//         P[3][0] = 5;
//         P[3][1] = 20;
//         update(0);

//         // p = new PointPlotter(g, offset, new int[] { originX, originY }, 120);
//     }

//     private void update(int f) {
//         if (P.length < 2)
//             return;
//         T = new double[_k + P.length + 1];
//         double d = 1.0 / (T.length - 1);
//         for (int i = 0; i < T.length; i++) {
//             T[i] = i * d;
//         }
//         for (int i = 0; i < P.length; i++) {
//             P[i][0] = originX + P[i][0] * offset;
//             P[i][1] = originY - P[i][1] * offset;
//         }
//         System.out.println(Arrays.toString(T));
//         System.out.println("This is update");

//     }

//     public void actionPerformed(ActionEvent e) {
//         if (e.getSource() == button1) {
//             System.out.println("zoom out is pressed");
//             if (offset - 10 > 0) {
//                 offset = offset - 10;
//             } else if (offset - 1 > 0) {
//                 offset = offset - 1;
//             }
//         } else if (e.getSource() == button2) {
//             if (offset < 10) {
//                 offset = offset + 1;
//             } else if (offset + 10 <= 80) {
//                 offset = offset + 10;
//             }
//             System.out.println("Zoom in is pressed");
//         }
//         // System.out.println(offset + "main");
//         update();
//         repaint();
//     }

//     public void mouseEntered(MouseEvent m) {
//         // repaint();

//     }

//     public void mouseExited(MouseEvent m) {
//         // repaint();
//     }

//     public void update() {
//         originX = (getX() + getWidth()) / 2;
//         originY = (getY() + getHeight()) / 2;
//         obj.offset = offset;
//         obj.originX = originX;
//         obj.originY = originY;
//         update(0);
//     }

//     public void mousePressed(MouseEvent m) {
//         int x = (int) m.getX();
//         int y = (int) m.getY();
//         switch (m.getModifiers()) {
//             case InputEvent.BUTTON1_MASK: {
//                 System.out.println("This is the left button");
//                 update();
//                 break;
//             }
//             case InputEvent.BUTTON2_MASK: {
//                 System.out.println("This is the middle button");
//                 update();
//                 break;
//             }
//             case InputEvent.BUTTON3_MASK: {
//                 System.out.println("This is the right button");
//                 break;
//             }

//         }
//         update();
//         repaint();
//     }

//     public void mouseReleased(MouseEvent m) {
//         // repaint();
//     }

//     public void mouseDragged(MouseEvent m) {
//         // repaint();
//     }

//     public void mouseMoved(MouseEvent m) {
//         // repaint();
//     }

//     public void mouseClicked(MouseEvent m) {
//         update();
//         repaint();
//     }

//     double convert_to_radian(double angle) {
//         return Math.PI * angle / 180;
//     }

//     // int calc_ti(int i) {
//     // if (i < k) {
//     // return 0;
//     // } else if (k < i && i < n) {
//     // return i - k + 1;
//     // } else if (i > n) {
//     // return n - k + 2;
//     // }
//     // return 0;
//     // }

//     // double b_spline_func(int i, int k, int u) {

//     // }

//     private double basisFunc(int i, int k, double t) {
//         if (k == 0) {
//             if (T[i] <= t && t < T[i + 1])
//                 return 1;
//             return 0;
//         }
//         double a = (t - T[i]) / (T[i + k] - T[i]);
//         double b = (T[i + k + 1] - t) / (T[i + k + 1] - T[i + 1]);
//         return a * basisFunc(i, k - 1, t) + b * basisFunc(i + 1, k - 1, t);
//     }

//     private double[] DeBoor(double t) {
//         double[] V = new double[2];
//         for (int i = 0; i < P.length; i++) {
//             double scale = basisFunc(i, _k, t);
//             V[0] += P[i][0] * scale;
//             V[1] += P[i][1] * scale;
//         }
//         return V;
//     }

//     public void calculatePath(Graphics g) {
//         if (P.length < 2) {
//             return;
//         }
//         double[] v = null;
//         double delta = 1 / 32.0;
//         for (double t = T[2]; t < T[5]; t += delta) {
//             double[] p = DeBoor(t);
//             if (v != null) {
//                 Color c = new Color((int) (t * 255), 0, 0);
//                 System.out.println("Point to plot endpoints " + (int) v[0] + " ," + (int) v[1] + "   " + (int) p[0]
//                         + "," + (int) p[1]);
//                 g.drawLine((int) v[0], (int) v[1], (int) p[0], (int) p[1]);
//                 obj.drawline((int) v[0], (int) v[1], (int) p[0], (int) p[1], c, g);
//             }
//             v = p;
//         }

//         for (int i = 0; i < P.length; i++) {
//             int x = (int) P[i][0];
//             int y = (int) P[i][1];
//             g.setColor(Color.RED);
//             g.fillOval(x - 2, y - 2, 4, 4);
//             obj.plotPoint(x, y + 15, Color.red, g);
//             g.drawString(String.valueOf(i), x, y + 15);
//         }
//     }

//     public void paint(Graphics g) {
//         if (offset >= 10) {
//             obj.plotgrid(getX(), getY(), getWidth(), getHeight(), g);
//         }
//         int x1 = Integer.parseInt(t1.getText());
//         int y1 = Integer.parseInt(t2.getText());
//         Color c = Color.red;
//         calculatePath(g);
//     }
// }

// // class Bspline {
// // double[][] P;
// // double[] T;
// // int _k = 3;

// // public Bspline(draw obj1) {
// // P = new double[4][2];
// // P[0][0] = 10;
// // P[0][1] = 10;
// // P[1][0] = 5;
// // P[1][1] = 5;
// // P[2][0] = 10;
// // P[2][1] = 20;
// // P[3][0] = 5;
// // P[3][1] = 20;
// // update();
// // }

// // private void update() {
// // if (P.length < 2)
// // return;
// // T = new double[_k + P.length + 1];
// // double d = 1.0 / (T.length - 1);
// // for (int i = 0; i < T.length; i++) {
// // T[i] = i * d;
// // }
// // System.out.println(Arrays.toString(T));

// // }

// // private double basisFunc(int i, int k, double t) {
// // if (k == 0) {
// // if (T[i] <= t && t < T[i + 1])
// // return 1;
// // return 0;
// // }
// // double a = (t - T[i]) / (T[i + k] - T[i]);
// // double b = (T[i + k + 1] - t) / (T[i + k + 1] - T[i + 1]);
// // return a * basisFunc(i, k - 1, t) + b * basisFunc(i + 1, k - 1, t);
// // }

// // private double[] DeBoor(double t) {
// // double[] V = new double[2];
// // for (int i = 0; i < P.length; i++) {
// // double scale = basisFunc(i, _k, t);
// // V[0] += P[i][0] * scale;
// // V[1] += P[i][1] * scale;
// // }
// // return V;
// // }

// // public void calculatePath(Graphics g) {
// // if (P.length < 2) {
// // return; // zu wenige punkte um ein pfad zu zeichnen
// // }
// // double[] v = null;
// // double delta = 1 / 32.0;
// // for (double t = T[2]; t < T[5]; t += delta) {
// // double[] p = DeBoor(t);
// // if (v != null) {
// // Color c = new Color((int) (t * 255), 0, 0);

// // g.drawLine((int) v[0], (int) v[1], (int) p[0], (int) p[1]);
// // }
// // v = p;
// // }

// // for (int i = 0; i < P.length; i++) {
// // int x = (int) P[i][0];
// // int y = (int) P[i][1];
// // g.setColor(Color.RED);
// // g.fillOval(x - 2, y - 2, 4, 4);
// // g.drawString(String.valueOf(i), x, y + 15);
// // }
// // }
// // }

import java.awt.*;
import java.applet.*;
import java.awt.event.*;

public class b_spline extends Applet implements ActionListener, MouseWheelListener {
    Button b1 = new Button(" Zoom In ");
    Button b2 = new Button(" Zoom Out ");
    Button gridb = new Button("GRID");
    TextField x1, y1, x2, y2, x3, y3, x4, y4;

    int gap = 10;
    boolean gridon = true;

    public void init() {
        // Color buttonColor1 = new Color(0, 255, 0);
        // Color buttonColor2 = new Color(255, 0, 0);
        Color bgColor = Color.lightGray;

        x1 = new TextField("20");
        x2 = new TextField("20");
        x3 = new TextField("20");
        x4 = new TextField("20");
        y1 = new TextField("20");
        y2 = new TextField("20");
        y3 = new TextField("20");
        y4 = new TextField("20");
        x1.setText("15");
        x2.setText("-25");
        x3.setText("-15");
        x4.setText("-30");
        y1.setText("20");
        y2.setText("30");
        y3.setText("-16");
        y4.setText("-40");

        // b1.setBackground(buttonColor1);
        // b2.setBackground(buttonColor2);

        add(gridb);
        add(b1);
        add(b2);
        add(x1);
        add(y1);
        add(x2);
        add(y2);
        add(x3);
        add(y3);
        add(x4);
        add(y4);

        addMouseWheelListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        gridb.addActionListener(this);

        setBackground(bgColor);
    }

    // for zoom using mouse wheel
    public void mouseWheelMoved(MouseWheelEvent e) {
        int z = e.getWheelRotation();
        zoom(z);
    }

    // button action listener
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1)
            zoom(+10);
        else if (e.getSource() == b2)
            zoom(-10);
        else if (e.getSource() == gridb) {
            gridon = !gridon;
            repaint();
        }

    }

    // function to make a grid with respect to standard cartesian coordinates
    public void makeGrid(Graphics g, int gap) {
        if (gridon == false)
            return;
        if (gap <= 0 || gap > getHeight())
            return;
        int originX = (getX() + getWidth()) / 2;
        int originY = (getY() + getHeight()) / 2;
        g.setColor(Color.black);
        g.drawLine(originX, originY - getHeight() / 2, originX, originY + getHeight() / 2);
        g.drawLine(originX - getWidth() / 2, originY, originX + getWidth() / 2, originY);
        g.setColor(Color.black);
        for (int x = gap; x <= getWidth(); x += gap) {
            g.drawLine(originX + x, 0, originX + x, getHeight());
            g.drawLine(originX - x, 0, originX - x, getHeight());
        }
        for (int y = gap; y <= getHeight(); y += gap) {
            g.drawLine(0, originY + y, getWidth(), originY + y);
            g.drawLine(0, originY - y, getWidth(), originY - y);
        }
    }

    // for zoom feature
    public void zoom(int i) {
        if (i > 0)
            gap += gap / 10 + 1;
        else if (i < 0)
            gap -= gap / 10 + 1;
        if (gap < 0)
            gap = 1000;
        if (gap > 1600)
            gap = 2;
        repaint();

    }

    // plots a square at point (Square) x,y in grid
    public void plotpoint(Graphics g, int x, int y, Color c) {
        int originX = (getX() + getWidth()) / 2;
        int originY = (getY() + getHeight()) / 2;
        g.setColor(c);
        g.fillRect(originX + (gap * x) - (gap / 2), originY - (gap * y) - (gap / 2), gap, gap);
    }

    // plots a point in x,y (Circle)
    public void plotCircle(Graphics g, int x, int y, Color c) {
        int originX = (getX() + getWidth()) / 2;
        int originY = (getY() + getHeight()) / 2;
        g.setColor(c);
        g.fillOval(originX + (gap * x) - (gap / 8), originY - (gap * y) - (gap / 8), gap / 4, gap / 4);
    }

    public void plotLine(Graphics g, int x1, int y1, int x2, int y2, Color col) {
        double x = x1;
        double y = y1;
        int dx = x2 - x1;
        int dy = y2 - y1;
        int step;
        if (Math.abs(dx) > Math.abs(dy))
            step = Math.abs(dx);
        else
            step = Math.abs(dy);

        for (int i = 0; i < step; i++) {
            plotpoint(g, (int) x, (int) y, col);
            x = x + (double) dx / step;
            y = y + (double) dy / step;
        }
    }

    public void plotPoints(int[] x, int[] y) {
        for (int i = 0; i < x.length; i++) {
            plotpoint(getGraphics(), x[i], y[i], Color.blue);
        }
    }

    public void plotPoints(int[] x, int[] y, Color c) {
        for (int i = 0; i < x.length; i++) {
            plotpoint(getGraphics(), x[i], y[i], c);
        }
    }

    public void bezierCurve(int[] x, int[] y) {
        double xu = 0.0, yu = 0.0, u = 0.0;

        for (u = 0.0; u <= 1.0; u += 0.0001) {
            xu = Math.pow(1 - u, 3) * x[0] + 3 * u * Math.pow(1 - u, 2) * x[1] + 3 * Math.pow(u, 2) * (1 - u) * x[2]
                    + Math.pow(u, 3) * x[3];
            yu = Math.pow(1 - u, 3) * y[0] + 3 * u * Math.pow(1 - u, 2) * y[1] + 3 * Math.pow(u, 2) * (1 - u) * y[2]
                    + Math.pow(u, 3) * y[3];
            plotpoint(getGraphics(), (int) xu, (int) yu, Color.yellow);
        }
    }

    public void bezierCurve(int[] x, int[] y, Color c) {
        double xu = 0.0, yu = 0.0, u = 0.0;

        for (u = 0.0; u <= 1.0; u += 0.0001) {
            xu = Math.pow(1 - u, 3) * x[0] + 3 * u * Math.pow(1 - u, 2) * x[1] + 3 * Math.pow(u, 2) * (1 - u) * x[2]
                    + Math.pow(u, 3) * x[3];
            yu = Math.pow(1 - u, 3) * y[0] + 3 * u * Math.pow(1 - u, 2) * y[1] + 3 * Math.pow(u, 2) * (1 - u) * y[2]
                    + Math.pow(u, 3) * y[3];
            plotpoint(getGraphics(), (int) xu, (int) yu, c);
        }
    }

    public void paint(Graphics g) {
        makeGrid(g, gap);
        int k = 1;
        // while (k < 6) {
        // int[] x0 = { -100 * k, -80 * k, -60 * k, -40 * k };
        // int[] y0 = { 0, -20 * k, -30 * k, 0 };
        // int[] x1 = { -40 * k, -20 * k, 0, 20 * k };
        // int[] y1 = { 0, 20 * k, 25 * k, 0 };
        // int[] x2 = { 20 * k, 40 * k, 80 * k, 60 * k };
        // int[] y2 = { 0, -20 * k, -25 * k, 0 };

        // bezierCurve(x0, y0);
        // bezierCurve(x1, y1);
        // bezierCurve(x2, y2);

        // plotPoints(x0, y0);
        // plotPoints(x1, y1);
        // plotPoints(x2, y2);
        // k++;
        // }
        int[] x = { Integer.parseInt(x1.getText()), Integer.parseInt(x2.getText()), Integer.parseInt(x3.getText()),
                Integer.parseInt(x4.getText()) };
        int[] y = { Integer.parseInt(y1.getText()), Integer.parseInt(y2.getText()), Integer.parseInt(y3.getText()),
                Integer.parseInt(y4.getText()) };

        plotPoints(x, y, Color.CYAN);
        bezierCurve(x, y, Color.red);

        plotCircle(g, 0, 0, Color.blue);
    }
}