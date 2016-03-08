package com.iShamrock.iMuseum.acvitity.AR;

import android.util.DisplayMetrics;

import java.util.Map;

/**
 * Created by 逢双 on 14-2-22.
 */
public class Angle {
    //这里面是一坨算角度的代码，解释不清楚。。。反正他能跑对就是了。。。
    //
    //你们可以修改下高度height的计算，我本来是根据距离的，近的在屏幕上方，远的再在屏幕下方
    //你们可以改成默认在中间，然后根据手机的上下方向改高度
    private boolean show;
    private double angle;
    private double oriental;
    private double xPercent;
    private double dAngle;
    private LBSPoint here;
    private LBSPoint there;
    private double distance;
    private DisplayMetrics dm = ARActivity.dm;
    private String text;

    public String getText() {
        return text;
    }

    public Angle(double oriental, LBSPoint here, LBSPoint there, String text){
//        System.out.println("oriental is :" + oriental);
        this.oriental = oriental;
        this.here = here;
        this.there = there;
        this.text = text;

//        height = Math.random() * dm.heightPixels * 0.8 + dm.heightPixels * 0.1;
        calculateAngle();
        calculateDAngle();
        toShow();
        calculateXPercent();
        calculateDistance();
    }

    private void calculateAngle(){
        double dLatitude = there.getX() - here.getX();
        double dLongitude = there.getY() - here.getY();
        angle = Math.atan(dLongitude / dLatitude);
        if(dLatitude < 0){
            angle += Math.PI;
        }
//        System.out.println("angle is :" + angle);
    }

    private void calculateDAngle(){
        //angle & oriental 弧度制
        //todo : maybe need a angle to adjust
        double a = angle - oriental;
        double b = angle - oriental + 2 * Math.PI;//limits
        double c = angle - oriental - 2 * Math.PI;
        //dAngle should between 0 & 2PI
        if(Math.abs(a) < Math.abs(b) && Math.abs(a) < Math.abs(c)){
            dAngle = a;
        }
        else if(Math.abs(b) < Math.abs(a) && Math.abs(b) < Math.abs(c)){
            dAngle = b;
        }
        else {
            dAngle = c;
        }
//        System.out.println("dAngle is :" + dAngle);
    }

    private void toShow(){
        if(Math.abs(dAngle) * 2 < Math.PI){
            show = true;
        }
    }

    private void calculateXPercent(){
        xPercent = dAngle / Math.PI + 0.5;
    }

    private void calculateDistance(){
        distance = Math.pow(Math.pow(here.getX() - there.getX(), 2) + Math.pow(here.getY() - there.getY(), 2), 0.5);
    }

    public void reset(double oriental, LBSPoint here){
        this.oriental = oriental;
        this.here = here;
        calculateAngle();
        calculateDAngle();
        toShow();
        calculateXPercent();
        calculateDistance();
    }

    public boolean isShow() {
        return show;
    }

    public double getAngle() {
        return angle;
    }

    public double getOriental() {
        return oriental;
    }

    public double getxPercent() {
        return xPercent;
    }

    public double getHeight() {
        return there.getNumber() * dm.heightPixels / 10.0 + dm.heightPixels * 0.15;// - dm.heightPixels * 1.3;
    }

    public double getDistance() {
        return distance;
    }
}
