package com.iShamrock.iMuseum.entity;

import android.graphics.Rect;

/**
 * Created by lifengshuang on 11/24/15.
 */
public class RecognizedExhibit {
    private String name;
    private Rect rect;
    private int distance;

    public RecognizedExhibit(String name, Rect rect, int distance) {
        this.name = name;
        this.rect = rect;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
