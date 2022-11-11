package com.computer_graphics;
import java.awt.*;

public class GridDrawer {
    Graphics g;
    int originX,originY;
    public GridDrawer(Graphics g,int originX,int originY){
        this.g=g;
        this.originX=originX;
        this.originY=originY;
    }
    public void drawGrid(int gridGap,int maxX, int maxY,Color gridColor) {
        g.setColor(gridColor);
        int curY = originY - gridGap;
        int markY = 1;
        while (curY >= 0) {
            g.drawLine(0, curY, maxX, curY);
            g.drawString(Integer.toString(markY++), originX, curY);
            curY -= gridGap;
        }
        markY = -1;
        curY = originY + gridGap;
        while (curY <= maxY) {
            g.drawLine(0, curY, maxX, curY);
            g.drawString(Integer.toString(markY--), originX, curY);
            curY += gridGap;
        }
        int markX = -1;
        int curX = originX - gridGap;
        while (curX >= 0) {
            g.drawLine(curX, 0, curX, maxY);
            g.drawString(Integer.toString(markX--), curX, originY);
            curX -= gridGap;
        }
        markX = 1;
        curX = originX + gridGap;
        while (curX <= maxX) {
            g.drawLine(curX, 0, curX, maxY);
            g.drawString(Integer.toString(markX++), curX, originY);
            curX += gridGap;
        }
    }
}
