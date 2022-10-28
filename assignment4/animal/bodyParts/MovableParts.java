package animal.bodyParts;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;
import animal.features.*;
import com.computer_graphics.*;
import java.util.*;

public class MovableParts {
    Body upperHalf, lowerHalf, fist;
    Size sz;

    public MovableParts(PointPlotter p, int topX, int topY, int width, int height, SpotType st, HairType ht,
            int[] angles) {
        int rx1 = (int) (0.8 * width);
        int ry1 = (int) (0.4 * height);
        int cy1 = topY - ry1;
        int rx2 = (int) (0.6 * width);
        int ry2 = (int) (0.6 * height);
        int cy2 = cy1 - ry1 - ry2 + 3;
        Rotator rt = new Rotator(topX, topY, angles[0]);
        int[] p2 = rt.rotate(topX, cy2 + ry2);
        upperHalf = new Body(p, topX, cy1, rx1, ry1, st, ht, rt);
        Rotator rt2 = new Rotator(p2[0], p2[1], angles[1]);
        int c22x = p2[0];
        int c22y = p2[1] - ry2;
        lowerHalf = new Body(p, c22x, c22y, rx2, ry2, st, ht, rt2);
        this.sz = sz;
        p2[1] -=2*ry2;
        p2=rt2.rotate(p2[0],p2[1]);
        rt.setAngle(0);
        fist=new Body(p,p2[0]-8,p2[1],10,4,st,ht,rt);
    }
}
