package com.iShamrock.iMuseum.acvitity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.*;
import com.iShamrock.iMuseum.R;
import com.iShamrock.iMuseum.data.DataItem;
import com.iShamrock.iMuseum.data.ShowroomItem;
import com.iShamrock.iMuseum.util.DrawerAdapter;
import com.iShamrock.iMuseum.util.DrawerItemOnClickAction;
import com.iShamrock.iMuseum.util.XmlParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.Map;

/**
 * Created by lifengshuang on 11/18/15.
 */
public class Favor extends Activity {
    private static Set<Integer> favors = new HashSet<>();
    private InputStream inputStream;
    private ArrayList<DataItem> data;
    private List<ShowroomItem> exhibitionHalls;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favor);
        initLeftDrawer();

        inputStream = this.getResources().openRawResource(R.raw.exhibit);

        //get data
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
        for (ShowroomItem exhibitionHall : exhibitionHalls) {
            data.addAll(exhibitionHall.getExhibits());
        }
        for (int i = 0; i < data.size(); i++) {
            data.get(i).id(i);
        }

        //todo: following two lines should be deleted when release
        addFavorItem(0);
        addFavorItem(2);

        initFavorList();
    }

    private void initFavorList() {
        ListView favorList = (ListView) findViewById(R.id.list_favor);
        List<java.util.Map<String, Object>> data = getFavorData();
        favorList.setAdapter(new SimpleAdapter(this, data,
                R.layout.favor_item, new String[]{"name", "img", "description", "location"},
                new int[]{R.id.favor_list_name, R.id.favor_list_img, R.id.favor_list_description, R.id.favor_list_location}));
        favorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), (String) data.get(i).get("name"), Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(Favor.this, Exhibit.class);
                intent.putExtra("id", (int) data.get(i).get("id"));
                startActivity(intent);
            }
        });

    }

    private void initLeftDrawer() {
        ListView drawerList = (ListView) findViewById(R.id.left_drawer_favor);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_favor);
        drawerList.setAdapter(new DrawerAdapter(getApplicationContext()));
        Activity activity = this;
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DrawerItemOnClickAction.click(drawerLayout, activity, i, 4);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(toggle);
    }

    private List<java.util.Map<String, Object>> getFavorData() {
        List<java.util.Map<String, Object>> list = new LinkedList<>();
        for (DataItem item : data) {
            if (favors.contains(item.getId())) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", item.getId());
                map.put("name", item.getName());
                map.put("location", item.getLocation() + " (" + item.getFloor() + "楼)");
                map.put("description", item.getDescription().length() >= 80
                        ? item.getDescription().substring(0, 80) + "..." : item.getDescription());
                String drawable = item.getImgId();
                int res = getResources().getIdentifier(drawable, "drawable", getPackageName());
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