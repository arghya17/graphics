import java.applet.*;
import java.awt.*;
import java.awt.event.*;
// import java.lang.*;

public class LineClipping extends Applet implements ActionListener {
    int gridSize = 20;
    static int R_O = 12, R_P = 12, x1 = -30, y1 = 20, x2 = 30, y2 = 20, x3 = 30, y3 = -20, x4 = -30, y4 = -20;
    Button button1, button2;
    Color color = Color.red;

    public void init() {
        int a = 255, b = 255, c = 255;
        Color mycolor = new Color(a, b, c);
        setBackground(mycolor);
        button1 = new Button("ZoomIn");
        add(button1);
        button1.addActionListener(this);
        button2 = new Button("ZoomOut");
        add(button2);
        button2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            gridSize += 4;
        } else {
            if (gridSize - 4 > 0) {
                gridSize -= 4;
            }
        }
        repaint();
    }

    void plotpoint(int x, int y, Color C, Graphics g) {
        int Ox = (getX() + getWidth()) / 2;
        int Oy = (getY() + getHeight()) / 2;
        g.setColor(C);
        int Shift = R_P / 2, r = R_P, tx = x * gridSize, ty = y * gridSize;
        g.fillOval(Ox + tx - Shift, Oy - ty - Shift, r, r);
    }

    void DDA(int X0, int Y0, int X1, int Y1, Graphics g) {
        int p1, p2, k, x, y, steps;
        double Dx, Dy, i, j;
        y = Y1 - Y0;
        x = X1 - X0;
        if (Math.abs(y) > Math.abs(x)) {
            steps = Math.abs(y);
        } else {
            steps = Math.abs(x);
        }
        Dx = (double) x / steps;
        Dy = (double) y / steps;
        i = X0;
        j = Y0;
        for (k = 0; k <= steps; k++) {
            p1 = (int) Math.round(i);
            p2 = (int) Math.round(j);
            i += Dx;
            j += Dy;
            plotpoint(p1, p2, color, g);
        }
    }

    void LineClip(int x5, int y5, int x6, int y6, Graphics g) {
        int c1 = 0, c2 = 0, dy = y6 - y5, dx = x6 - x5, c;
        double m = (double) dy / dx;
        c = y5 - (int) (m * x5);
        if (x5 < x1) {
            c1 = c1 | 1;
        } else if (x5 > x2) {
            c1 = c1 | 2;
        }
        if (y5 < y4) {
            c1 = c1 | 4;
        } else if (y5 > y1) {
            c1 = c1 | 8;
        }
        if (x6 < x1) {
            c2 = c2 | 1;
        } else if (x6 > x2) {
            c2 = c2 | 2;
        }
        if (y6 < y4) {
            c2 = c2 | 4;
        } else if (y6 > y1) {
            c2 = c2 | 8;
        }

        if (c1 == 0 && c2 == 0) {
            DDA(x5, y5, x6, y6, g);
        }

        else if ((c1 & c2) == 0) {
            if (c1 != 0) {
                if (c1 == 1) {
                    if (dy == 0) {
                        x5 = x1;
                    } else {
                        x5 = x1;
                        y5 = ((int) (m * x1) + c);
                    }
                } else if (c1 == 2) {
                    if (dy == 0) {
                        x5 = x2;
                    } else {
                        x5 = x2;
                        y5 = ((int) (m * x2) + c);
                    }
                } else if (c1 == 4) {
                    if (dx == 0) {
                        y5 = y3;
                    } else {
                        y5 = y3;
                        x5 = (int) ((y3 - c) / m);
                    }
                } else if (c1 == 8) {
                    if (dx == 0) {
                        y5 = y1;
                    } else {
                        y5 = y1;
                        x5 = (int) ((y1 - c) / m);
                    }
                } else if (c1 == 9) {
                    if (((int) (m * x1) + c) < y1) {
                        x5 = x1;
                        y5 = ((int) (m * x1) + c);
                    } else {
                        y5 = y1;
                        x5 = (int) ((y1 - c) / m);
                    }
                } else if (c1 == 10) {
                    if (((int) (m * x2) + c) < y2) {
                        x5 = x2;
                        y5 = ((int) (m * x2) + c);
                    } else {
                        y5 = y2;
                        x5 = (int) ((y2 - c) / m);
                    }
                } else if (c1 == 6) {
                    if (((int) (m * x3) + c) > y3) {
                        x5 = x3;
                        y5 = ((int) (m * x3) + c);
                    } else {
                        y5 = y3;
                        x5 = (int) ((y3 - c) / m);
                    }
                } else if (c1 == 5) {
                    if (((int) (m * x4) + c) > y4) {
                        x5 = x4;
                        y5 = ((int) (m * x4) + c);
                    } else {
                        y5 = y4;
                        x5 = (int) ((y4 - c) / m);
                    }
                }
            }
            if (c2 != 0) {
                if (c2 == 1) {
                    if (dy == 0) {
                        x6 = x1;
                    } else {
                        x6 = x1;
                        y6 = ((int) (m * x1) + c);
                    }
                } else if (c2 == 2) {
                    if (dy == 0) {
                        x6 = x2;
                    } else {
                        x6 = x2;
                        y6 = ((int) (m * x2) + c);
                    }
                } else if (c2 == 4) {
                    if (dx == 0) {
                        y6 = y3;
                    } else {
                        y6 = y3;
                        x6 = (int) ((y3 - c) / m);
                    }
                } else if (c2 == 8) {
                    if (dx == 0) {
                        y6 = y1;
                    } else {
                        y6 = y1;
                        x6 = (int) ((y1 - c) / m);
                    }
                } else if (c2 == 9) {
                    if (((int) (m * x1) + c) < y1) {
                        x6 = x1;
                        y6 = ((int) (m * x1) + c);
                    } else {
                        y6 = y1;
                        x6 = (int) ((y1 - c) / m);
                    }
                } else if (c2 == 10) {
                    if (((int) (m * x2) + c) < y2) {
                        x6 = x2;
                        y6 = ((int) (m * x2) + c);
                    } else {
                        y6 = y2;
                        x6 = (int) ((y2 - c) / m);
                    }
                } else if (c2 == 6) {
                    if (((int) (m * x3) + c) > y3) {
                        x6 = x3;
                        y6 = ((int) (m * x3) + c);
                    } else {
                        y6 = y3;
                        x6 = (int) ((y3 - c) / m);
                    }
                } else if (c2 == 5) {
                    if (((int) (m * x4) + c) > y4) {
                        x6 = x4;
                        y6 = ((int) (m * x4) + c);
                    } else {
                        y6 = y4;
                        x6 = (int) ((y4 - c) / m);
                    }
                }
            }
            DDA(x5, y5, x6, y6, g);
        }
    }

    void PolygonClip(int p[][], int n, Graphics g) {
        int i;
        for (i = 1; i < n; i++) {
            LineClip(p[i][0], p[i][1], p[i + 1][0], p[i + 1][1], g);
        }
        LineClip(p[1][0], p[1][1], p[n][0], p[n][1], g);
    }

    void Polygon(int p[][], int n, Graphics g) {
        int i;
        color = new Color(200, 200, 200);
        for (i = 1; i < n; i++) {
            DDA(p[i][0], p[i][1], p[i + 1][0], p[i + 1][1], g);
        }
        DDA(p[1][0], p[1][1], p[n][0], p[n][1], g);
        color = Color.red;
    }

    public void paint(Graphics g) {
        int i = 0, j = 0, m = 100, n = 100, p = 250;
        int Ox = (getX() + getWidth()) / 2;
        int Oy = (getY() + getHeight()) / 2;
        Color mycolor = new Color(m, n, p);
        g.setColor(mycolor);
        for (i = Oy, j = Oy; i <= getHeight(); i = i + gridSize, j = j - gridSize) {
            g.drawLine(getX(), i, getWidth(), i);
            g.drawLine(getX(), j, getWidth(), j);
        }
        for (i = Ox, j = Ox; i <= getWidth(); i = i + gridSize, j = j - gridSize) {
            g.drawLine(i, getY(), i, getHeight());
            g.drawLine(j, getY(), j, getHeight());
        }
        for (i = -Ox; i <= Ox; i++) {
            plotpoint(i, 0, Color.green, g);
        }
        for (i = -Oy; i <= Oy; i++) {
            plotpoint(0, i, Color.green, g);
        }
        int Shift = R_O / 2, r = R_O;
        g.fillOval(Ox - Shift, Oy - Shift, r, r);

        color = new Color(50, 150, 250);
        DDA(x1, y1, x2, y2, g);
        DDA(x2, y2, x3, y3, g);
        DDA(x3, y3, x4, y4, g);
        DDA(x4, y4, x1, y1, g);
        color = Color.red;

        int[][] P = new int[10][2];
        P[1][0] = -40;
        P[1][1] = 0;
        P[2][0] = 0;
        P[2][1] = 20;
        P[3][0] = 40;
        P[3][1] = 40;
        P[4][0] = 0;
        P[4][1] = -50;
        P[5][0] = -10;
        P[5][1] = -10;
        P[6][0] = -40;
        P[6][1] = 0;
        Polygon(P, 6, g);
        PolygonClip(P, 6, g);
    }
}