import java.applet.*;
import java.awt.*;

public class test extends line {
	public void init() {
		this.setSize(new Dimension(600, 800));

	}

	public void paint(Graphics g) {
		int originX = (getX() + getWidth()) / 2;
		int originY = (getX() + getHeight()) / 2;
		g.drawLine(-getWidth() + originX, 0 + originY, getWidth() + originX, 0 + originY);
		g.drawLine(0 + originX, -getHeight() + originY, 0 + originX, getHeight() + originY);
		Font f = new Font("Time New Roman", 4, 25);
		g.setFont(f);
		g.drawString("(0,0)", originX, originY);
		drawcircle(g);
	}
}