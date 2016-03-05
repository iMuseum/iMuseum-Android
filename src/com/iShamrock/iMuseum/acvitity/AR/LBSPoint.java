package com.iShamrock.iMuseum.acvitity.AR;

/**
 * Created by lifengshuang on 3/5/16.
 */
public class LBSPoint {
    //我现在就放了x，y坐标，你们可以再加入展馆名称、id什么的。
    private double x;
    private double y;

    public LBSPoint() {
    }

    public LBSPoint(double x, double y) {

        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
