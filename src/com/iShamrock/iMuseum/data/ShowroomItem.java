package com.iShamrock.iMuseum.data;

import com.iShamrock.iMuseum.acvitity.Showroom;

/**
 * Created by mayezhou on 16/1/29.
 */
public class ShowroomItem {
//    private int id;
    private String name;
//    private int imgId;
    private String englishName;
    private int floor;

    public ShowroomItem() {

    }

    public ShowroomItem(String name, String englishName, int floor) {
        this.name = name;
        this.englishName = englishName;
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public ShowroomItem setName(String name) {
        this.name = name;
        return this;
    }

    public String getEnglishName() {
        return englishName;
    }

    public ShowroomItem setEnglishName(String englishName) {
        this.englishName = englishName;
        return this;
    }

    public int getFloor() {
        return floor;
    }

    public ShowroomItem setFloor(int floor) {
        this.floor = floor;
        return this;
    }
}
