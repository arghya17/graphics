import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Mygraphics extends Applet implements ActionListener {
	int gridSize = 20;
	static int R_O = 12, R_P = 12;
	Button button1, button2;
	Color color = Color.red;

	public void init() {
		int a = 255, b = 255, c = 255;
		Color mycolor = new Color(a, b, c);
		setBackground(mycolor);
		button1 = new Button("+");
		add(button1);
		button1.addActionListener(this);
		button2 = new Button("-");
		add(button2);
		button2.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			gridSize += 4;
		} else {
			if (gridSize - 4 > 0) {
				gridSize -= 4;
			}
		}
		repaint();
	}

	void plotpoint(int x, int y, Color C, Graphics g) {
		int Ox = (getX() + getWidth()) / 2;
		int Oy = (getY() + getHeight()) / 2;
		g.setColor(C);
		int Shift = R_P / 2, r = R_P, tx = x * gridSize, ty = y * gridSize;
		g.fillOval(Ox + tx - Shift, Oy - ty - Shift, r, r);
	}

	void DDA(int X0, int Y0, int X1, int Y1, Graphics g) {
		int p1, p2, k, x, y, steps;
		double Dx, Dy, i, j;
		y = Y1 - Y0;
		x = X1 - X0;
		if (Math.abs(y) > Math.abs(x)) {
			steps = Math.abs(y);
		} else {
			steps = Math.abs(x);
		}
		Dx = (double) x / steps;
		Dy = (double) y / steps;
		i = X0;
		j = Y0;
		for (k = 0; k <= steps; k++) {
			p1 = (int) Math.round(i);
			p2 = (int) Math.round(j);
			i += Dx;
			j += Dy;
			plotpoint(p1, p2, color, g);
		}
	}

	float Bfn(int i, int k, double u, int n, int t[], Graphics g) {
		float temp1, temp2;

		if (k == 1) {
			if (u >= t[i] && u <= t[i + 1]) {
				return 1;
			} else {
				return 0;
			}
		} else {
			if ((t[i + k - 1] - t[i]) == 0) {
				temp1 = 0;
			} else {
				temp1 = (float) (((u - t[i]) * Bfn(i, k - 1, u, n, t, g)) / (t[i + k - 1] - t[i]));
			}
			if ((t[i + k] - t[i + 1]) == 0) {
				temp2 = 0;
			} else {
				temp2 = (float) (((t[i + k] - u) * Bfn(i + 1, k - 1, u, n, t, g)) / (t[i + k] - t[i + 1]));
			}
			return temp1 + temp2;
		}
	}

	void BSplineCurve(int p[][], int n, int k, Graphics g) {
		int i, j, x, y;
		double u;
		int t[] = new int[n + k + 1];
		for (i = 0; i <= n + k; i++) {
			if (i < k) {
				t[i] = 0;
			} else if (i >= k && i <= n) {
				t[i] = i - k + 1;
			} else {
				t[i] = n - k + 2;
			}
		}
		for (i = 0; i <= n - k + 1; i++) {
			for (u = i; u <= i + 1; u = u + 0.01) {
				x = 0;
				y = 0;
				for (j = i; j <= k + i - 1; j++) {
					x += (int) (Bfn(j, k, u, n, t, g) * p[j][0]);
					y += (int) (Bfn(j, k, u, n, t, g) * p[j][1]);
				}
				plotpoint(x, y, color, g);
			}
		}
	}

	public void paint(Graphics g) {
		int i = 0, j = 0, m = 100, n = 100, p = 250, N, K;
		int Ox = (getX() + getWidth()) / 2;
		int Oy = (getY() + getHeight()) / 2;
		Color mycolor = new Color(m, n, p);
		g.setColor(mycolor);
		for (i = Oy, j = Oy; i <= getHeight(); i = i + gridSize, j = j - gridSize) {
			g.drawLine(getX(), i, getWidth(), i);
			g.drawLine(getX(), j, getWidth(), j);
		}
		for (i = Ox, j = Ox; i <= getWidth(); i = i + gridSize, j = j - gridSize) {
			g.drawLine(i, getY(), i, getHeight());
			g.drawLine(j, getY(), j, getHeight());
		}
		for (i = -Ox; i <= Ox; i++) {
			plotpoint(i, 0, Color.green, g);
		}
		for (i = -Oy; i <= Oy; i++) {
			plotpoint(0, i, Color.green, g);
		}
		int Shift = R_O / 2, r = R_O;
		g.fillOval(Ox - Shift, Oy - Shift, r, r);

		N = 3;
		K = 3;
		int[][] P = new int[N + 1][2];
		P[0][0] = -40;
		P[0][1] = 0;
		P[1][0] = -20;
		P[1][1] = 40;
		P[2][0] = 0;
		P[2][1] = 20;
		P[3][0] = 20;
		P[3][1] = 40;

		BSplineCurve(P, N, K, g);

		for (i = 0; i <= N; i++) {
			plotpoint(P[i][0], P[i][1], Color.blue, g);
		}
	}
}