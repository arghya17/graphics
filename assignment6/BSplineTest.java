import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BSplineTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(1, 1));

        BSplineTestPanel bSplineTestPanel = new BSplineTestPanel();
        frame.getContentPane().add(bSplineTestPanel);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class BSplineTestPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Bspline bspline = new Bspline();
        bspline.calculatePath(g);
    }
}

class Bspline {
    double[][] P;
    double[] T;
    int _k = 3;

    public Bspline() {
        P = new double[4][2];
        P[0][0] = 100;
        P[0][1] = 100;
        P[1][0] = 50;
        P[1][1] = 50;
        P[2][0] = 100;
        P[2][1] = 200;
        P[3][0] = 50;
        P[3][1] = 200;
        update();
    }

    private void update() {
        if (P.length < 2)
            return;
        T = new double[_k + P.length + 1];
        double d = 1.0 / (T.length - 1);
        for (int i = 0; i < T.length; i++) {
            T[i] = i * d;
        }
        System.out.println(Arrays.toString(T));

    }

    private double basisFunc(int i, int k, double t) {
        if (k == 0) {
            if (T[i] <= t && t < T[i + 1])
                return 1;
            return 0;
        }
        double a = (t - T[i]) / (T[i + k] - T[i]);
        double b = (T[i + k + 1] - t) / (T[i + k + 1] - T[i + 1]);
        return a * basisFunc(i, k - 1, t) + b * basisFunc(i + 1, k - 1, t);
    }

    private double[] DeBoor(double t) {
        double[] V = new double[2];
        for (int i = 0; i < P.length; i++) {
            double scale = basisFunc(i, _k, t);
            V[0] += P[i][0] * scale;
            V[1] += P[i][1] * scale;
        }
        return V;
    }

    public void calculatePath(Graphics g) {
        if (P.length < 2) {
            return; // zu wenige punkte um ein pfad zu zeichnen
        }
        double[] v = null;
        double delta = 1 / 32.0;
        for (double t = T[2]; t < T[5]; t += delta) {
            double[] p = DeBoor(t);
            if (v != null) {
                g.setColor(new Color((int) (t * 255), 0, 0));
                g.drawLine((int) v[0], (int) v[1], (int) p[0], (int) p[1]);
            }
            v = p;
        }

        for (int i = 0; i < P.length; i++) {
            int x = (int) P[i][0];
            int y = (int) P[i][1];
            g.setColor(Color.RED);
            g.fillOval(x - 2, y - 2, 4, 4);
            g.drawString(String.valueOf(i), x, y + 15);
        }
    }
}
