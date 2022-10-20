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

    public void ear(int choice_ear, int radius, int x_centre, int y_centre, Graphics g) {
        // 1: triangle ear
        // 2: circle ear
        switch (choice_ear) {
            case 1:
                obj.drawcircle(radius, x_centre + radius, y_centre + radius, g);
                break;
        }
    }

    public void head(int radius, int x_centre, int y_centre, int choice_beak, int choice_ear, Graphics g) {
        obj.drawcircle(radius, x_centre, y_centre, g);
        // head
        int eye_r = (int) (radius / 4);
        obj.drawcircle(eye_r, x_centre - eye_r / 2, y_centre - eye_r / 2, g);
        ear(choice_ear, radius / 10, x_centre + radius, y_centre + radius, g);
    }

    public void paint(Graphics g) {
        obj.plotgrid(getX(), getY(), getWidth(), getHeight(), g);
        int x1 = Integer.parseInt(t1.getText());
        int y1 = Integer.parseInt(t2.getText());
        // obj.plotPoint(x1, y1, Color.BLUE, g);
        head(10, x1, y1, 1, 1, g);
    }
}
