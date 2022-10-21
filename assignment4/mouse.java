// package assignment4;

import draw.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class mouse extends Applet implements MouseListener, MouseMotionListener, ActionListener {
    public Button button1, button2;
    public int offset;
    public int originX;
    public int originY;
    protected TextField t1, t2;
    public draw obj;

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
        t1.setText("0");
        t2.setText("0");
        add(t1);
        add(t2);
        obj = new draw();
        obj.originX = originX;
        obj.originY = originY;
        obj.offset = offset;
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
                offset = 10;
            } else if (offset + 10 <= 80) {
                offset = offset + 10;
            }
            System.out.println("Zoom in is pressed");
        }
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

    public void beak(int choice_beak, int radius, int x_centre, int y_centre, Graphics g) {

    }

    public void ear(int choice_ear, int radius, int x_centre, int y_centre, Graphics g) {
        // 1: triangle ear
        // 2: circle ear
        int pivot_x, pivot_y;
        int x1, y1, x2, y2;
        switch (choice_ear) {
            case 1:
                x1 = x_centre - 5 * radius;
                y1 = y_centre - 5 * radius;
                x2 = x1;
                y2 = y1;
                x1 = x1 + (int) (5 * radius * Math.cos(convert_to_radian(5)));
                y1 = y1 + (int) (5 * radius * Math.sin(convert_to_radian(5)));
                x2 = x2 + (int) (5 * radius * Math.cos(convert_to_radian(55)));
                y2 = y2 + (int) (5 * radius * Math.sin(convert_to_radian(55)));
                pivot_x = (int) (x1 + 3 * x2) / 2;
                pivot_y = y_centre + radius;
                obj.drawline(x1, y1, pivot_x, pivot_y, g);
                obj.drawline(x2, y2, pivot_x, pivot_y, g);
                x1 = x_centre - 5 * radius;
                y1 = y_centre - 5 * radius;
                x1 = x1 - (int) (5 * radius * Math.cos(convert_to_radian(75)));
                y1 = y1 + (int) (5 * radius * Math.sin(convert_to_radian(75)));
                pivot_x = (int) (x1 + x2) / 2;
                pivot_y = pivot_y + (int) 2 * radius;
                obj.drawline(x1, y1, pivot_x, pivot_y, g);
                obj.drawline(x2, y2, pivot_x, pivot_y, g);
                break;
            default:
                obj.drawcircle(radius + radius / 4, x_centre - radius, y_centre - radius, g);
                obj.drawcircle(radius + radius / 2, x_centre + radius, (int) (y_centre - 2 * radius), g);
                break;
        }
    }

    public void head(int radius, int x_centre, int y_centre, int choice_beak, int choice_ear, Graphics g) {
        obj.drawcircle(radius, x_centre, y_centre, g);
        // head
        int eye_r = (int) (radius / 4);
        // eye
        obj.drawcircle(eye_r, x_centre - eye_r, y_centre + eye_r / 2, g);
        ear(choice_ear, radius / 5, x_centre + radius, y_centre + radius, g);
    }

    public void paint(Graphics g) {
        obj.plotgrid(getX(), getY(), getWidth(), getHeight(), g);
        int x1 = Integer.parseInt(t1.getText());
        int y1 = Integer.parseInt(t2.getText());
        // obj.plotPoint(x1, y1, Color.BLUE, g);
        head(20, x1, y1, 1, 2, g);
    }
}
