import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class dda1 extends Canvas implements MouseListener, MouseMotionListener, ActionListener {
    public JButton button1, button2;
    public int offset;
    public int originX;
    public int originY;
    protected JTextField t1, t2, t3, t4;

    public dda1(JFrame frame) {
        button1 = new JButton("Zoom out");
        button1.setPreferredSize(new Dimension(30, 30));
        // button1.setBounds(50, 100, 50, 30);
        button1.addActionListener(this);
        button2 = new JButton("Zoom in");
        button2.setPreferredSize(new Dimension(30, 30));
        // button2.setBounds(100, 100, 95, 30);
        button2.addActionListener(this);
        offset = 40;
        addMouseListener(this);
        addMouseMotionListener(this);
        originX = (frame.getX() + frame.getWidth()) / 2;
        originY = (frame.getY() + frame.getHeight()) / 2;
        this.setSize(new Dimension(1920, 980));
        t1 = new JTextField("10");
        t2 = new JTextField("10");
        t3 = new JTextField("10");
        t4 = new JTextField("10");
        t1.setText("0");
        t2.setText("0");
        t3.setText("8");
        t4.setText("8");
        // the panel for buttons
        frame.add(new MenuPane(button1, button2, t1, t2, t3, t4));
    }

    public class MenuPane extends JPanel {

        public MenuPane(JButton button1, JButton button2, JTextField t1, JTextField t2, JTextField t3, JTextField t4) {
            setBorder(new EmptyBorder(10, 10, 10, 10));
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

            JPanel buttons = new JPanel(new BoxLayout(this, BoxLayout.LINE_AXIS));
            buttons.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
            buttons.add(Box.createHorizontalGlue());
            buttons.add(button1);
            buttons.add(Box.createRigidArea(new Dimension(10, 0)));
            buttons.add(button2);
            buttons.add(Box.createRigidArea(new Dimension(10, 0)));
            buttons.add(t1);
            buttons.add(Box.createRigidArea(new Dimension(10, 0)));
            buttons.add(t2);
            buttons.add(Box.createRigidArea(new Dimension(10, 0)));
            buttons.add(t3);
            buttons.add(Box.createRigidArea(new Dimension(10, 0)));
            buttons.add(t4);
            add(buttons);
        }

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

    public void paint(Graphics g) {
        g.setColor(Color.red);
        Font f = new Font("Nano", 4, 24);
        g.drawLine(-getWidth() + originX, 0 + originY, getWidth() + originX, 0 + originY);
        g.drawLine(0 + originX, -getHeight() + originY, 0 + originX, getHeight() + originY);
        g.drawString("(0,0)", originX, originY);
        g.setColor(Color.black);
        System.out.println(originX + " " + originY);
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
        drawline(x1, y1, x2, y2, g);
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

    public static void main(String args[]) {
        JFrame f = new JFrame();
        f.setSize(1920, 980);
        dda1 obj = new dda1(f);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.add(obj);
        f.setVisible(true);
        f.validate();
    }

}