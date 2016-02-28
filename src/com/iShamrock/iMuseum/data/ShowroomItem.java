package com.iShamrock.iMuseum.data;

import com.iShamrock.iMuseum.acvitity.Showroom;

import java.util.List;

/**
 * Created by mayezhou on 16/1/29.
 */
public class ShowroomItem {
    private int id;
    private String name;
    private String imgId;
    private String englishName;
    private int floor;
    private List<DataItem> exhibits;

    public ShowroomItem() {

    }

    public ShowroomItem(String name, String englishName, int floor, List<DataItem> exhibits, String imgId) {
        this.name = name;
        this.englishName = englishName;
        this.floor = floor;
        this.exhibits = exhibits;
        this.imgId = imgId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getImgId() {
        return imgId;
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

    public List<DataItem> getExhibits() {
        return exhibits;
    }

    public void setExhibits(List<DataItem> exhibits) {
        this.exhibits = exhibits;
    }
}
