package com.iShamrock.iMuseum.data;

import com.iShamrock.iMuseum.R;

import java.util.*;

/**
 * Created by mayezhou on 16/1/29.
 */
public class ShowroomData {

    private static ArrayList<ShowroomItem> data = new ArrayList<ShowroomItem>();

    static {
        ShowroomItem i1 = new ShowroomItem();
        i1
                .setName("第一展览馆")
                .setEnglishName("Exhibition Hall")
                .setFloor(1);
        data.add(i1);
        ShowroomItem i2 = new ShowroomItem();
        i2
                .setName("中国古代青铜馆")
                .setEnglishName("Ancient Chinese Bronze Gallery")
                .setFloor(1);
        data.add(i2);
        ShowroomItem i3 = new ShowroomItem();
        i3
                .setName("中国古代青铜馆")
                .setEnglishName("Ancient Chinese Bronze Gallery")
                .setFloor(1);
        data.add(i3);
    }

    public static ArrayList<ShowroomItem> getShowroomList() {
        return data;
    }

    public static List<Map<String, Object>> getShowroomData() {
        List<Map<String, Object>> list = new LinkedList<>();
        for (ShowroomItem item : data) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", item.getName());
                map.put("location", " (" + item.getFloor() + "楼)");
                map.put("englishName", item.getEnglishName());
                list.add(map);
        }
        return list;
    }
}
