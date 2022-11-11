// package assignment4;

import draw.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import com.computer_graphics.*;
import animal.features.*;
import animal.bodyParts.*;
import java.util.concurrent.ThreadLocalRandom;

public class line_clipping extends Applet implements MouseListener, MouseMotionListener, ActionListener {
    public Button button1, button2;
    public int offset;
    public int originX;
    public int originY;
    protected TextField t1, t2, t3;
    public PointPlotter p;
    public draw obj;
    // Defining region codes
    static final int INSIDE = 0; // 0000
    static final int LEFT = 1; // 0001
    static final int RIGHT = 2; // 0010
    static final int BOTTOM = 4; // 0100
    static final int TOP = 8; // 1000

    // Defining x_max, y_max and x_min, y_min for
    // clipping rectangle. Since diagonal points are
    // enough to define a rectangle
    static final int x_max = 10;
    static final int y_max = 8;
    static final int x_min = 4;
    static final int y_min = 4;

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
        p.offset = obj.offset;
        p.originX = obj.originX;
        p.originY = obj.originY;
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

}
