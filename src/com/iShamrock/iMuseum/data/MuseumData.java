package com.iShamrock.iMuseum.data;

import com.iShamrock.iMuseum.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lifengshuang on 11/25/15.
 */
public class MuseumData {

    private static HashMap<Integer, DataItem> data;

    public static HashMap<Integer, DataItem> getAllData() {
        if (data == null) {
            initializeData();
        }
        return data;
    }

    public static DataItem getDataById(int id) {
        if (data == null) {
            initializeData();
        }
        return data.get(id);
    }

    private static void initializeData() {
        data = new HashMap<>();
        DataItem i1 = new DataItem();
        i1
                .id(1)
                .name("景德镇窑青花山水图板")
                .imgId(R.drawable.i1)
                .dynasty("清嘉庆十八年")
                .type("瓷器")
                .author("")
                .description("    瓷板方形中空，正面绘日本江州琵琶湖八景图，远山近林，烟波浩渺，署名皇朝狩野缝殿助金门画史藤原永岳绘，背面饰波涛纹，中间圆形开光内书“大日本  州桥本景留居士嘱  清嘉庆昭阳作噩岁宦工陶贞恭制十五板”， 侧面书咏景七言诗八首。《尔雅》：“太岁在癸曰昭阳”，“太岁在酉曰作噩”，嘉庆十八年癸酉，即1813年。目前在日本有数件类似瓷板传世，推测当时制作了十五枚。\n" +
                        "    琵琶湖八景又名近江八景，位于日本滋贺县，其八景仿照洞庭湖潇湘八景选定，将洞庭潇湘八景“平沙落雁、远浦归帆、山市晴岚、漁村夕照、洞庭秋月、潇湘夜雨、烟寺晚钟、江天暮雪”改为琵琶湖畔的“坚田落雁、矢桥归帆、粟津晴岚、濑田夕照、石山秋月、唐崎夜雨、三井晚钟、比良暮雪”。十七世纪后半开始，日本出现了许多“近江八景”绘画，葛饰北斋、安藤广重等都创作过关于近江八景的绘画，深受日本民众喜爱。瓷板订制于清代嘉庆年间，反映了日本独特的审美趣味及其与中国文化的密切关联。");
        data.put(1, i1);
        DataItem i2 = new DataItem();
        i2
                .id(2)
                .name("景德镇窑广彩人物图大碗")
                .dynasty("清乾隆")
                .author("")
                .type("瓷器")
                .imgId(R.drawable.i2)
                .description("    碗敞口，深腹，圈足。通体以广彩为饰，里心绘人物图，口沿一周金地上四面开光，开光内绘山水、花鸟图。外壁对称长方形开光，内绘人物故事图，间以上下相对的不规则开光，内绘山水、花鸟图。圈足边饰金彩花纹一周，足内白向无款。\n" +
                        "    广彩，是广州织金彩瓷的简称，始见于康熙时期，盛于乾隆一朝。主要作为外销瓷的商品之一，远销欧洲。其制作工艺是将景德镇白瓷运至广州，仿照西洋或我国传统图案进行彩绘加工低温二次烧成，其釉面鲜艳华丽，金碧辉煌。");
        data.put(2, i2);
        DataItem i3 = new DataItem();
        i3
                .id(3)
                .name("景德镇窑广彩徽章纹汤盆")
                .imgId(R.drawable.i3)
                .dynasty("清乾隆")
                .type("瓷器")
                .author("")
                .description("    汤盆附盖及托盘，盆敞口、直腹、圈足，盖面隆起，宝珠形钮，托盘宽折沿，浅腹，圈足。器身通体以金彩绘缠枝花卉，盖面、器身及托盘边沿各有四个开光，开光内饰鸟雀栖枝纹及英国波内（Powney）家族徽章。\n" +
                        "    波内家族居住在伯克郡温莎，于1661年5月获得这一纹章。家族中有多名成员在东印度公司任职。传世器皿中，共有三套这个家族订制的纹章瓷器。\n" +
                        "    此盆应该是成套餐具中的汤盆，是西方人用餐时盛汤的器皿，十八世纪中期，外销瓷中成套餐具的数量增加，汤盆开始流行，在一套餐具中，汤盆往往是最为华丽，器形最复杂的器皿，有各类仿银器及动物造型。此件汤盆式样虽简，但纹样繁复，通体以金彩装饰，亦在整套餐具中最显夺目。");
        data.put(3, i3);
    }

}

class DataItem {
    private int id;
    private String name;
    private int imgId;
    private String description;
    private String dynasty;
    private String type;
    private String author;

    public DataItem() {
    }

    public DataItem(int id, String name, int imgId, String description, String dynasty, String type, String author) {
        this.id = id;
        this.name = name;
        this.imgId = imgId;
        this.description = description;
        this.dynasty = dynasty;
        this.type = type;
        this.author = author;
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
}