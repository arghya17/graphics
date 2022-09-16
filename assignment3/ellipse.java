import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class ellipse extends Applet implements MouseListener, MouseMotionListener, ActionListener {
    public Button button1, button2;
    public int offset;
    public int originX;
    public int originY;
    protected TextField t1, t2, t3, t4;

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
        originX = (getX() + getWidth()) / 2;
        originY = (getY() + getHeight()) / 2;
        this.setSize(new Dimension(1920, 980));
        t1 = new TextField("10");
        t2 = new TextField("10");
        t3 = new TextField("10");
        t4 = new TextField("10");
        t1.setText("0");
        t2.setText("0");
        t3.setText("8");
        // t4.setText("8");
        add(t1);
        add(t2);
        add(t3);
        add(t4);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            System.out.println("zoom out is pressed");
            if (offset - 10 > 0) {
                offset = offset - 10;
            } else if (offset - 1 > 0) {
                offset = offset - 1;
            }
        } else {
            if (offset < 10) {
                offset = 10;
            } else if (offset + 10 <= 80) {
                offset = offset + 10;
            }
            System.out.println("Zoom in is pressed");
        }
        repaint();
    }

    public void mouseEntered(MouseEvent m) {
        // repaint();

    }

    public void mouseExited(MouseEvent m) {
        // repaint();
    }

    public void mousePressed(MouseEvent m) {
        int x = (int) m.getX();
        int y = (int) m.getY();
        switch (m.getModifiers()) {
            case InputEvent.BUTTON1_MASK: {
                System.out.println("This is the left button");
                break;
            }
            case InputEvent.BUTTON2_MASK: {
                System.out.println("This is the middle button");
                break;
            }
            case InputEvent.BUTTON3_MASK: {
                System.out.println("This is the right button");
                break;
            }

        }
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
        repaint();
    }

    public void plotgrid(Graphics g) {
        originX = (getX() + getWidth()) / 2;
        originY = (getY() + getHeight()) / 2;
        g.setColor(Color.red);
        Font f = new Font("Nano", 4, 24);
        g.drawLine(-getWidth() + originX, 0 + originY, getWidth() + originX, 0 + originY);
        g.drawLine(0 + originX, -getHeight() + originY, 0 + originX, getHeight() + originY);
        g.drawString("(0,0)", originX, originY);
        g.setColor(Color.black);
        int yCoord = 0;
        for (int i = originX; i < originX * 2 + getWidth(); i += offset) {
            g.setColor(Color.DARK_GRAY);
            g.drawLine(i, originY * 2 - getHeight(), i, originY * 2 + getHeight());
            if (offset > 30 && i != originX) {
                g.drawString(String.valueOf(-1 * yCoord), i - 15, originY + 15);

            }
            yCoord--;
        }
        yCoord = 0;
        for (int i = originX; i > originX * 2 - getWidth(); i -= offset) {
            g.setColor(Color.DARK_GRAY);
            g.drawLine(i, originY * 2 - getHeight(), i, originY * 2 + getHeight());
            if (offset > 30 && i != originX) {
                g.drawString(String.valueOf(-1 * yCoord), i - 15, originY + 15);

            }
            yCoord++;
        }

        int xCoord = 0;
        for (int i = originY; i < originY * 2 + getHeight(); i += offset) {
            g.setColor(Color.DARK_GRAY);
            g.drawLine(originX * 2 - getWidth(), i, originX * 2 + getWidth(), i);
            if (offset > 30 && i != originY) {
                g.drawString(String.valueOf(-1 * xCoord), originX + 10, i + 15);
            }
            xCoord++;

        }
        xCoord = 0;
        for (int i = originY; i > originY * 2 - getHeight(); i -= offset) {
            g.setColor(Color.DARK_GRAY);
            g.drawLine(originX * 2 - getWidth(), i, originX * 2 + getWidth(), i);
            if (offset > 30 && i != originY) {
                g.drawString(String.valueOf(-1 * xCoord), originX + 10, i + 15);
            }
            xCoord--;
        }
    }

    public void paint(Graphics g) {
        plotgrid(g);
        int x1, y1, x2, y2;
        x1 = Integer.parseInt(t1.getText());
        y1 = Integer.parseInt(t2.getText());
        x2 = Integer.parseInt(t3.getText());
        y2 = Integer.parseInt(t4.getText());
        // System.out.println("Enter 2 endpoints of the line segment");
        // x1 = sc.nextInt();
        // y1 = sc.nextInt();
        // x2 = sc.nextInt();
        // y2 = sc.nextInt();
        drawellipse(g, x1, y1, x2, y2);
    }

    public void plotPoint(int x, int y, Color c, Graphics g) {
        g.setColor(c);
        int incradius = 20;
        g.fillOval(originX + x * offset - (offset + incradius) / 8, originY - y * (offset) - (offset + incradius) / 8,
                (offset + incradius) / 4, (offset + incradius) / 4);
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

    public void drawellipse(Graphics g, int rx, int ry, int xc, int yc) {
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
