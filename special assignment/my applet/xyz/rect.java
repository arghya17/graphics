package xyz;

import java.applet.*;
import java.awt.*;

public class rect extends Applet {
    public void drawrect(Graphics g, int height, int width) {
        int x = (getX() + getWidth()) / 2;
        int y = (getY() + getHeight()) / 2;
        g.drawRect(x, y, width, height);

    }
}
