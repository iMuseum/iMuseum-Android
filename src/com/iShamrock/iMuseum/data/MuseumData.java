package com.iShamrock.iMuseum.data;

import android.content.Context;
import com.iShamrock.iMuseum.R;
import com.iShamrock.iMuseum.acvitity.*;
import com.iShamrock.iMuseum.util.XmlParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.Map;

/**
 * Created by lifengshuang on 2/13/16.
 */
public class MuseumData {
    private static InputStream inputStream;
    private static ArrayList<DataItem> data;
    private static List<ShowroomItem> exhibitionHalls;
    private static Context context;
    private static Set<Integer> favors;

    //must be called at very first in Homepage
    public static void initData(Context context1) {
        favors = new HashSet<>();
        context = context1;
        inputStream = context.getResources().openRawResource(R.raw.exhibit);
        try {
            exhibitionHalls = new XmlParser().parse(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        data = new ArrayList<>();
        int j = 0;
        for (ShowroomItem exhibitionHall : exhibitionHalls) {
            exhibitionHall.setId(j);
            data.addAll(exhibitionHall.getExhibits());
            j++;
        }
        //set the id of all exhibits
        for (int i = 0; i < data.size(); i++) {
            data.get(i).id(i);
        }

        //todo: following two lines should be deleted when release
        addFavorItem(0);
        addFavorItem(2);
    }

    //activity/HomePage
    public static List<Map<String, Object>> getShowroomList() {
        List<Map<String, Object>> list = new LinkedList<>();
        for (int i = 1; i <= 4 ; i++) {
            Map<String, Object> map = new HashMap<>();
            List<ShowroomItem> exhibitionHallsOfOneFloor = getExhibitionHallByFloor(i);
            String names = "";
            String englishNames = "";
            for (ShowroomItem exhibitionHall : exhibitionHallsOfOneFloor) {
                names += exhibitionHall.getName() + " ";
                englishNames += exhibitionHall.getEnglishName() + " ";
            }
            map.put("floor", i + "楼");
            map.put("names", names);
            map.put("englishName", englishNames);
            list.add(map);
        }
        return list;
    }

    private static List<ShowroomItem> getExhibitionHallByFloor(int floor) {
        List<ShowroomItem> list = new LinkedList<>();
        for (ShowroomItem exhibitionHall : exhibitionHalls) {
            if (exhibitionHall.getFloor() == floor) {
                list.add(exhibitionHall);
            }
        }
        return list;
    }

    //activity/ExhibitionHallOfFloor
    public static List<Map<String, Object>> getExhibitionHallByFloor2(int floor) {
        List<Map<String, Object>> list = new LinkedList<>();
        for (ShowroomItem exhibitionHall : exhibitionHalls) {
            if (exhibitionHall.getFloor() == floor) {
                Map<String, Object> map = new HashMap<>();
                String drawableName = exhibitionHall.getImgId();
                int res = context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());
                map.put("img", res);
                map.put("name", exhibitionHall.getName());
                map.put("englishName", exhibitionHall.getEnglishName());
                list.add(map);
            }
        }
        return list;
    }

    //activity/Showroom need
    public static List<Map<String, Object>> getShowroomData(String showroom) {
        List<Map<String, Object>> list = new LinkedList<>();
        for (ShowroomItem exhibitionHall : exhibitionHalls) {
            if (exhibitionHall.getName().equals(showroom)) {
                for (DataItem item : exhibitionHall.getExhibits()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", item.getId());
                    map.put("name", item.getName());
                    map.put("description", item.getDescription().length() >= 80
                            ? item.getDescription().substring(0, 80) + "..." : item.getDescription());
                    String drawableName = item.getImgId();
                    int res = context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());
                    map.put("img", res);
                    list.add(map);
                }
            }
        }
        return list;
    }

    //activity/Exhibit need
    public static DataItem getDataById(int id) {
//        for (DataItem item : data) {
//            if (item.getId() == id) {
//                return item;
//            }
//        }
//        return null;
        return data.get(id);
    }

    public static ShowroomItem getExhibitionHallById(int id) {
        return exhibitionHalls.get(id);
    }

    //activity/Favor need
    public static List<Map<String, Object>> getFavorData() {
        List<Map<String, Object>> list = new LinkedList<>();
        for (DataItem item : data) {
            if (favors.contains(item.getId())) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", item.getId());
                map.put("name", item.getName());
                map.put("location", item.getLocation() + " (" + item.getFloor() + "楼)");
                map.put("description", item.getDescription().length() >= 80
                        ? item.getDescription().substring(0, 80) + "..." : item.getDescription());
                String drawable = item.getImgId();
                int res = context.getResources().getIdentifier(drawable, "drawable", context.getPackageName());
                map.put("img", res);
                list.add(map);
            }
        }
        return list;
    }

    public static boolean isFavored(int id) {
        boolean isFavored = false;
        if (favors.contains(id)) {
            isFavored = true;
        }
        return isFavored;
    }

    public static void addFavorItem(int id) {
        favors.add(id);
    }

    public static void deleteFavorItem(int id) {
        favors.remove(id);
    }
}
