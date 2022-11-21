package com.computer_graphics;

import com.computer_graphics.*;

public class TrianglePainter {
    public static void paintTriangle(int []p0,int []p1,int []p2,PointPlotter p){
        LinePainter.paintLine(p0, p1, p);
        LinePainter.paintLine(p1, p2, p);
        LinePainter.paintLine(p2, p0, p);
    }
}
