// package assignment4;

import draw.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import com.computer_graphics.*;
import animal.features.*;
import animal.bodyParts.*;
import java.util.concurrent.ThreadLocalRandom;

public class mouse extends Applet implements MouseListener, MouseMotionListener, ActionListener {
    public Button button1, button2;
    public int offset;
    public int originX;
    public int originY;
    protected TextField t1, t2, t3;
    public PointPlotter p;
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

    public void beak(int choice_beak, int radius, int x_centre, int y_centre, Graphics g) {
        // 1: small beak without teeth
        // 2: smalll beak with teeth
        // 3: large beak without teeth
        // 4: large beak with teeth
        int pivot_x, pivot_y;
        int x1, y1, x2, y2, x3, y3, x4, y4;
        x1 = x_centre - (int) (radius * Math.cos(convert_to_radian(10)));
        y1 = y_centre + (int) (radius * Math.sin(convert_to_radian(10)));
        x2 = x_centre - (int) ((radius * Math.cos(convert_to_radian(35))));
        y2 = y_centre + (int) (radius * Math.sin(convert_to_radian(35)));
        x3 = x_centre - (int) (radius * Math.cos(convert_to_radian(10)));
        y3 = y_centre - (int) (radius * Math.sin(convert_to_radian(10)));
        x4 = x_centre - (int) ((radius * Math.cos(convert_to_radian(35))));
        y4 = y_centre - (int) (radius * Math.sin(convert_to_radian(35)));
        pivot_y = y1;
        pivot_x = x1 - (int) (radius * 0.7);
        if (choice_beak <= 2) {
            obj.drawline(x1, y1, pivot_x, pivot_y, g);
            obj.drawline(x2, y2, pivot_x, pivot_y, g);
            if (choice_beak % 2 == 0) {
                tooth(pivot_x, pivot_y, x1, y1, radius, -1, g);
            }
            pivot_y = y3;
            obj.drawline(x3, y3, pivot_x, pivot_y, g);
            obj.drawline(x4, y4, pivot_x, pivot_y, g);
            if (choice_beak % 2 == 0) {
                tooth(pivot_x + 3, pivot_y, x3, y3, radius, 1, g);
            }
        } else {
            pivot_x = x1 - (int) (radius * 1.5);
            obj.drawline(x1, y1, pivot_x, pivot_y, g);
            obj.drawline(x2, y2, pivot_x, pivot_y, g);
            if (choice_beak % 2 == 0) {
                tooth(pivot_x, pivot_y, x1, y1, radius, -1, g);
            }
            pivot_y = y3;
            obj.drawline(x3, y3, pivot_x, pivot_y, g);
            obj.drawline(x4, y4, pivot_x, pivot_y, g);
            if (choice_beak % 2 == 0) {
                tooth(pivot_x + 3, pivot_y, x3, y3, radius, 1, g);
            }
        }
    }

    public void beak(int choice_beak, int radius, int x_centre, int y_centre, Color c, Graphics g) {
        // 1: small beak without teeth
        // 2: smalll beak with teeth
        // 3: large beak without teeth
        // 4: large beak with teeth
        int pivot_x, pivot_y;
        int x1, y1, x2, y2, x3, y3, x4, y4;
        x1 = x_centre - (int) (radius * Math.cos(convert_to_radian(10)));
        y1 = y_centre + (int) (radius * Math.sin(convert_to_radian(10)));
        x2 = x_centre - (int) ((radius * Math.cos(convert_to_radian(35))));
        y2 = y_centre + (int) (radius * Math.sin(convert_to_radian(35)));
        x3 = x_centre - (int) (radius * Math.cos(convert_to_radian(10)));
        y3 = y_centre - (int) (radius * Math.sin(convert_to_radian(10)));
        x4 = x_centre - (int) ((radius * Math.cos(convert_to_radian(35))));
        y4 = y_centre - (int) (radius * Math.sin(convert_to_radian(35)));
        pivot_y = y1;
        pivot_x = x1 - (int) (radius * 0.7);
        if (choice_beak <= 2) {
            obj.drawline(x1, y1, pivot_x, pivot_y, c, g);
            obj.drawline(x2, y2, pivot_x, pivot_y, c, g);
            if (choice_beak % 2 == 0) {
                tooth(pivot_x, pivot_y, x1, y1, radius, -1, c, g);
            }
            pivot_y = y3;
            obj.drawline(x3, y3, pivot_x, pivot_y, c, g);
            obj.drawline(x4, y4, pivot_x, pivot_y, c, g);
            if (choice_beak % 2 == 0) {
                tooth(pivot_x + 3, pivot_y, x3, y3, radius, 1, c, g);
            }
        } else {
            pivot_x = x1 - (int) (radius * 1.5);
            obj.drawline(x1, y1, pivot_x, pivot_y, c, g);
            obj.drawline(x2, y2, pivot_x, pivot_y, c, g);
            if (choice_beak % 2 == 0) {
                tooth(pivot_x, pivot_y, x1, y1, radius, -1, c, g);
            }
            pivot_y = y3;
            obj.drawline(x3, y3, pivot_x, pivot_y, c, g);
            obj.drawline(x4, y4, pivot_x, pivot_y, c, g);
            if (choice_beak % 2 == 0) {
                tooth(pivot_x + 3, pivot_y, x3, y3, radius, 1, c, g);
            }
        }
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
                pivot_x = (int) (x1 + 3 * Math.abs(x2)) / 2;
                System.out.println(pivot_x + " ");
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

    public void ear(int choice_ear, int radius, int x_centre, int y_centre, Color c, Graphics g) {
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
                int diff = Math.abs(x2 - x1);
                pivot_x = (int) (x1 + (3 * diff) / 2);
                // System.out.println(pivot_x + " " + diff + " " + x1);
                pivot_y = y_centre + radius;
                obj.drawline(x1, y1, pivot_x, pivot_y, c, g);
                obj.drawline(x2, y2, pivot_x, pivot_y, c, g);
                x1 = x_centre - 5 * radius;
                y1 = y_centre - 5 * radius;
                x1 = x1 - (int) (5 * radius * Math.cos(convert_to_radian(75)));
                y1 = y1 + (int) (5 * radius * Math.sin(convert_to_radian(75)));
                // diff = Math.abs(x2 - x1);
                pivot_x = (int) (x1 + x2) / 2;
                pivot_y = pivot_y + (int) 2 * radius;
                // System.out.println(pivot_x + " ");
                obj.drawline(x1, y1, pivot_x, pivot_y, c, g);
                obj.drawline(x2, y2, pivot_x, pivot_y, c, g);
                break;
            default:
                obj.drawcircle(radius + radius / 4, x_centre - radius, y_centre - radius, c, g);
                obj.drawcircle(radius + radius / 2, x_centre + radius, (int) (y_centre - 2 * radius), c, g);
                break;
        }
    }

    public void tooth(int x1, int y1, int x2, int y2, int radius, int up, Graphics g) {
        // x1, y1 is smaller
        // up=+1 or -1
        while (x1 <= x2) {
            obj.drawline(x1, y1, x1, y1 + (int) ((radius / 10) * up), g);
            x1 = x1 + 5;
        }
    }

    public void tooth(int x1, int y1, int x2, int y2, int radius, int up, Color c, Graphics g) {
        // x1, y1 is smaller
        // up=+1 or -1
        while (x1 <= x2) {
            obj.drawline(x1, y1, x1, y1 + (int) ((radius / 10) * up), c, g);
            x1 = x1 + 5;
        }
    }

    public void head(int radius, int x_centre, int y_centre, int choice_beak, int choice_ear, Graphics g) {
        obj.drawcircle(radius, x_centre, y_centre, g);
        // head
        int eye_r = (int) (radius / 4);
        // eye
        obj.drawcircle(eye_r, x_centre - eye_r, y_centre + eye_r / 2, g);
        ear(choice_ear, radius / 5, x_centre + radius, y_centre + radius, g);
        beak(choice_beak, radius, x_centre, y_centre, g);
    }

    public void head(int radius, int x_centre, int y_centre, int choice_beak, int choice_ear, Color c, Graphics g) {
        obj.drawcircle(radius, x_centre, y_centre, c, g);
        // head
        int eye_r = (int) (radius / 4);
        // eye
        obj.drawcircle(eye_r, x_centre - eye_r, y_centre + eye_r / 2, c, g);
        ear(choice_ear, radius / 5, x_centre + radius, y_centre + radius, c, g);
        beak(choice_beak, radius, x_centre, y_centre, c, g);
    }

    public void animal_draw(Graphics g, int x1, int y1, int radius, int spot, int hair, int choice_beak,
            int choice_ear) {
        p = new PointPlotter(g, offset, new int[] { originX, originY }, 10);
        update();
        SpotType sp = SpotType.SPOTTED;
        HairType h = HairType.HAIRY;
        if (spot == 0) {
            sp = SpotType.SPOTLESS;
        }
        if (hair == 0) {
            h = HairType.HAIRLESS;
        }
        p.setColor(Color.RED);
        Rotator rt = new Rotator(x1, y1 + radius, 35);
        Color c = Color.red;
        new Body(p, x1, y1, radius / 2, radius, sp, h, rt);// body
        head(radius / 2, x1, y1 + 3 * radius / 2, choice_beak, choice_ear, c, g);// ear
        int[] leg1p = rt.rotate(x1, y1 - radius);
        int[] leg2p = rt.rotate(x1 - 5, y1 - radius + 10);
        p.setColor(Color.RED);
        int radius1 = radius / 3;
        int radius2 = 3 * radius / 2;
        new MovableParts(p, leg1p[0], leg1p[1], radius / 3, 3 * radius / 2, sp, h,
                new int[] { 0, 5 });// leg1
        new MovableParts(p, leg2p[0], leg2p[1], radius / 3, 3 * radius / 2, sp, h,
                new int[] { -20, 5 });// leg2

        int[] hand1p = rt.rotate(x1 - radius / 2 + 10, y1 + radius / 2);
        int[] hand2p = rt.rotate(x1 - radius / 2 + 10, y1 + radius / 2 + 10);
        new MovableParts(p, hand1p[0], hand1p[1], radius / 4, radius, sp, h,
                new int[] { -45, -90 });// hand1
        new MovableParts(p, hand2p[0], hand2p[1], radius / 4, radius, sp, h,
                new int[] { -75, -120 });// hand2

        int[] tail = rt.rotate(x1 + 17, y1 - radius);
        rt.setPivot(tail);
        rt.setAngle(-10);

        new Body(p, tail[0], tail[1], radius / 3, radius / 8, sp, h, rt);
    }

    public void animal_draw(Graphics g, int x1, int y1, int radius, int spot, int hair, int choice_beak,
            int choice_ear, int arm, int leg) {
        p = new PointPlotter(g, offset, new int[] { originX, originY }, 10);
        update();
        SpotType sp = SpotType.SPOTTED;
        HairType h = HairType.HAIRY;
        if (spot == 0) {
            sp = SpotType.SPOTLESS;
        }
        if (hair == 0) {
            h = HairType.HAIRLESS;
        }
        p.setColor(Color.RED);
        Rotator rt = new Rotator(x1, y1 + radius, 35);
        Color c = Color.red;
        new Body(p, x1, y1, radius / 2, radius, sp, h, rt);// body
        head(radius / 2, x1, y1 + 3 * radius / 2, choice_beak, choice_ear, c, g);// ear
        int[] leg1p = rt.rotate(x1, y1 - radius);
        int[] leg2p = rt.rotate(x1 - 5, y1 - radius + 10);
        p.setColor(Color.RED);
        int radius1 = radius / 3;
        int radius2 = 3 * radius / 2;
        if (leg == 1) {
            radius1 = radius / 4;
            radius2 = radius;
        }
        if (leg == 3) {
            radius1 = radius / 2;
            radius2 = 5 * radius / 4;
        }
        new MovableParts(p, leg1p[0], leg1p[1], radius1, radius2, sp, h,
                new int[] { 0, 5 });// leg1
        new MovableParts(p, leg2p[0], leg2p[1], radius1, radius2, sp, h,
                new int[] { -20, 5 });// leg2

        int[] hand1p = rt.rotate(x1 - radius / 2 + 10, y1 + radius / 2);
        int[] hand2p = rt.rotate(x1 - radius / 2 + 10, y1 + radius / 2 + 10);
        radius1 = radius / 4;
        radius2 = radius;
        if (arm == 1) {
            radius1 = radius / 6;
            radius2 = radius / 2;
        }
        if (arm == 3) {
            radius1 = radius / 3;
            radius2 = 3 * radius / 2;
        }
        new MovableParts(p, hand1p[0], hand1p[1], radius1, radius2, sp, h,
                new int[] { -45, -90 });// hand1
        new MovableParts(p, hand2p[0], hand2p[1], radius1, radius2, sp, h,
                new int[] { -75, -120 });// hand2

        int[] tail = rt.rotate(x1 + 17, y1 - radius);
        rt.setPivot(tail);
        rt.setAngle(-10);

        new Body(p, tail[0], tail[1], radius / 3, radius / 8, sp, h, rt);
    }

    public void paint(Graphics g) {
        if (offset >= 10) {
            obj.plotgrid(getX(), getY(), getWidth(), getHeight(), g);
        }
        int x1 = Integer.parseInt(t1.getText());
        int y1 = Integer.parseInt(t2.getText());
        // obj.plotPoint(10, 10, Color.BLUE, g);
        int radius = Integer.parseInt(t3.getText());
        // p = new PointPlotter(g, offset, new int[] { originX, originY }, 10);
        // update();
        // p.setColor(Color.RED);
        // Rotator rt = new Rotator(x1, y1 + radius, 35);
        // Color c = Color.red;
        // new Body(p, x1, y1, radius / 2, radius, SpotType.SPOTTED, HairType.HAIRLESS,
        // rt);// body
        // head(radius / 2, x1, y1 + 3 * radius / 2, 4, 2, c, g);// ear
        // int[] leg1p = rt.rotate(x1, y1 - radius);
        // int[] leg2p = rt.rotate(x1 - 5, y1 - radius + 10);
        // p.setColor(Color.RED);
        // new MovableParts(p, leg1p[0], leg1p[1], radius / 3, 3 * radius / 2,
        // SpotType.SPOTTED, HairType.HAIRLESS,
        // new int[] { 0, 5 });// leg1
        // new MovableParts(p, leg2p[0], leg2p[1], radius / 3, 3 * radius / 2,
        // SpotType.SPOTTED, HairType.HAIRLESS,
        // new int[] { -20, 5 });// leg2

        // int[] hand1p = rt.rotate(x1 - radius / 2 + 10, y1 + radius / 2);
        // int[] hand2p = rt.rotate(x1 - radius / 2 + 10, y1 + radius / 2 + 10);
        // new MovableParts(p, hand1p[0], hand1p[1], radius / 4, radius,
        // SpotType.SPOTLESS, HairType.HAIRLESS,
        // new int[] { -45, -90 });// leg2
        // new MovableParts(p, hand2p[0], hand2p[1], radius / 4, radius,
        // SpotType.SPOTTED, HairType.HAIRLESS,
        // new int[] { -75, -120 });// leg2

        // int[] tail = rt.rotate(x1 + 17, y1 - radius);
        // rt.setPivot(tail);
        // rt.setAngle(-10);

        // new Body(p, tail[0], tail[1], radius / 3, radius / 8, SpotType.SPOTLESS,
        // HairType.HAIRY, rt);
        // p.setColor(Color.yellow);
        // p.plotPoint(10, 10);
        // 1: small beak without teeth
        // 2: smalll beak with teeth
        // 3: large beak without teeth
        // 4: large beak with teeth
        // 1: triangle ear
        // 2: circle ear
        int feature[][] = { { ThreadLocalRandom.current().nextInt(0, 2), ThreadLocalRandom.current().nextInt(0, 2),
                ThreadLocalRandom.current().nextInt(1, 5), ThreadLocalRandom.current().nextInt(1, 3),
                ThreadLocalRandom.current().nextInt(1, 4), ThreadLocalRandom.current().nextInt(1, 4) },
                { ThreadLocalRandom.current().nextInt(0, 2), ThreadLocalRandom.current().nextInt(0, 2),
                        ThreadLocalRandom.current().nextInt(1, 5), ThreadLocalRandom.current().nextInt(1, 3),
                        ThreadLocalRandom.current().nextInt(1, 4), ThreadLocalRandom.current().nextInt(1, 4) } };
        int[] feature1 = new int[6];
        int i;
        for (i = 0; i < 4; i++) {
            int choice = ThreadLocalRandom.current().nextInt(0, 2);
            feature1[i] = feature[choice][i];
        }
        animal_draw(g, x1 - 300, y1, radius, feature[0][0], feature[0][1], feature[0][2], feature[0][3], feature[0][4],
                feature[0][5]);
        animal_draw(g, x1, y1, radius, feature[1][0], feature[1][1], feature[1][2], feature[1][3], feature[1][4],
                feature[1][5]);
        animal_draw(g, x1 + 300, y1, radius, feature1[0], feature1[1], feature1[2], feature1[3], feature1[4],
                feature1[5]);

    }
}
