import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class grid extends Applet implements MouseListener, MouseMotionListener, ActionListener {
    Button button1, button2;
    int offset;

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
        this.setSize(new Dimension(600, 800));

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            System.out.println("zoom out is pressed");
            if (offset - 10 > 0) {
                offset = offset - 10;
            }
        } else {
            if (offset + 10 <= 80) {
                offset = offset + 10;
            }
            System.out.println("Zoom in is pressed");
        }
        repaint();
    }

    public void mouseEntered(MouseEvent m) {
        repaint();

    }

    public void mouseExited(MouseEvent m) {
        repaint();
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
        repaint();
    }

    public void mouseDragged(MouseEvent m) {
        repaint();
    }

    public void mouseMoved(MouseEvent m) {
        repaint();
    }

    public void mouseClicked(MouseEvent m) {
        repaint();
    }

    public void paint(Graphics g) {
        g.setColor(Color.red);
        int originX = (getX() + getWidth()) / 2;
        int originY = (getX() + getHeight()) / 2;
        Font f = new Font("Nano", 4, 24);
        g.drawLine(-getWidth() + originX, 0 + originY, getWidth() + originX, 0 + originY);
        g.drawLine(0 + originX, -getHeight() + originY, 0 + originX, getHeight() + originY);
        g.drawString("(0,0)", originX, originY);
        g.setColor(Color.black);
        for (int i = 1; i <= getWidth(); i++) {
            g.drawLine(-getWidth() + originX, offset * i + originY, getWidth() + originX, offset * i + originY);
            g.drawLine(-getWidth() + originX, -offset * i + originY, getWidth() + originX, -offset * i + originY);
            g.drawLine(offset * i + originX, -getHeight() + originY, offset * i + originX, getHeight() + originY);
            g.drawLine(-1 * offset * i + originX, -getHeight() + originY, -1 * offset * i + originX,
                    getHeight() + originY);
        }
    }

}