package com.iShamrock.iMuseum.acvitity.AR;

import com.ids.sdk.android.model.Location;

/**
 * Created by lifengshuang on 3/5/16.
 */
public class LBSPoint {
    //我现在就放了x，y坐标，你们可以再加入展馆名称、id什么的。
    private static int count = 0;
    private float x;
    private float y;
    private int number;
    private String name;

    public LBSPoint(Location location) {
        this.x = location.getX();
        this.y = location.getY();
        count++;
        number = count;
    }

    public int getNumber() {
        return number;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {// TODO: get name from exhibition hall
        this.name = name;
    }

    public static void reset() {
        count = 0;
    }
}
