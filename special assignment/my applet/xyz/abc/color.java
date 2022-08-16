package xyz.abc;

import java.applet.*;
import java.awt.*;

public class color extends Applet {
    public void fill(Graphics g) {
        g.setColor(Color.BLUE);
        int x = (getX() + getWidth()) / 2;
        int y = (getY() + getHeight()) / 2;
        g.drawOval(x, y, 100, 200);
    }
}
