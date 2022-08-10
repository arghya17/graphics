import xyz.rect;
import java.applet.*;
import java.awt.*;

public class test2 extends rect {
    public void init() {
        this.setSize(new Dimension(800, 800));
    }

    public void paint(Graphics g) {
        drawrect(g, 100, 80);
    }

}
