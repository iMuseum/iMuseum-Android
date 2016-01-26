package com.iShamrock.iMuseum.data;

/**
 * Created by lifengshuang on 11/25/15.
 */
public class DataItem {
    private int id;
    private String name;
    private int imgId;
    private String description;
    private String dynasty;
    private String type;
    private String author;
    private String location;
    private int floor;

    public DataItem() {
    }

    public DataItem(int id, String name, int imgId, String description, String dynasty, String type, String author, String location, int floor) {
        this.id = id;
        this.name = name;
        this.imgId = imgId;
        this.description = description;
        this.dynasty = dynasty;
        this.type = type;
        this.author = author;
        this.location = location;
        this.floor = floor;
    }

    public DataItem id(int id) {
        this.id = id;
        return this;
    }

    public DataItem name(String name) {
        this.name = name;
        return this;
    }

    public DataItem imgId(int imgId) {
        this.imgId = imgId;
        return this;
    }

    public DataItem description(String description) {
        this.description = description;
        return this;
    }

    public DataItem dynasty(String dynasty) {
        this.dynasty = dynasty;
        return this;
    }

    public DataItem type(String type) {
        this.type = type;
        return this;
    }

    public DataItem author(String author) {
        this.author = author;
        return this;
    }

    public DataItem location(String location) {
        this.location = location;
        return this;
    }

    public DataItem floor(int floor) {
        this.floor = floor;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImgId() {
        return imgId;
    }

    public String getDescription() {
        return description;
    }

    public String getDynasty() {
        return dynasty;
    }

    public String getType() {
        return type;
    }

    public String getAuthor() {
        return author;
    }

    public String getLocation() {
        return location;
    }

    public int getFloor() {
        return floor;
    }
}
