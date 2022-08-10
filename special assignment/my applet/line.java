import java.applet.*;
import java.awt.*;

public class line extends Applet {
    void drawcircle(Graphics g) {
        int originX = (getX() + getWidth()) / 2;
        int originY = (getY() + getHeight()) / 2;
        g.drawOval(originX, originY, 100, 100);
    }
}
