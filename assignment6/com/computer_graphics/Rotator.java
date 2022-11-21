package com.computer_graphics;

public class Rotator {
    int ox,oy,angle;
    public Rotator(int ox,int oy,int angle){
        this.ox=ox;
        this.oy=oy;
        this.angle=angle;
    }
    public int[] rotate(int x,int y){
        double angle=Math.PI/180*this.angle;
        double px = x*Math.cos(angle) - y*Math.sin(angle) + ox*(1.0 - Math.cos(angle)) + oy*Math.sin(angle);
        double py=x*Math.sin(angle) + y*Math.cos(angle) + oy*(1.0 - Math.cos(angle)) - ox*Math.sin(angle);
        return new int[]{(int)px,(int)py};
    }
    public boolean setAngle(int angle){
        if(angle<0||angle>360)return false;
        this.angle=angle;
        return true;
    }
    public boolean setPivot(int []pivot){
        this.ox=pivot[0];
        this.oy=pivot[1];
        return true;
    }
}
