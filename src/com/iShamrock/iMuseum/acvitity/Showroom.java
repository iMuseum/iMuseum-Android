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
 * Created by wangxin on 16/1/27.
 */
public class Showroom extends Activity{
    private TextView showroom_title;
    private String showroom;
    private InputStream inputStream;
    private static ArrayList<DataItem> data;
    private List<ShowroomItem> exhibitionHalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showroom);
        initLeftDrawer();
        /* get the showroom of this exhibit */
        Bundle bundle = this.getIntent().getExtras();
        showroom = bundle.getString("name");
        showroom_title = (TextView) this.findViewById(R.id.showroom_title);
        showroom_title.setText(showroom);

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
        //set the id of exhibit
        for (int i = 0; i < data.size(); i++) {
            data.get(i).id(i);
        }

        initShowroomList();
    }

    private void initShowroomList() {
        ListView showroomList = (ListView) findViewById(R.id.list_showroom);
        List<java.util.Map<String, Object>> data = getShowroomData(showroom);

        showroomList.setAdapter(new SimpleAdapter(this, data,
                R.layout.showroom_item, new String[]{"name", "img", "description"},
                new int[]{R.id.showroom_list_name, R.id.showroom_list_img, R.id.showroom_list_description}));

        showroomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), (String) data.get(i).get("name"), Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(Showroom.this, Exhibit.class);
                intent.putExtra("id", (int) data.get(i).get("id"));
                startActivity(intent);
            }
        });
    }

    private void initLeftDrawer() {
        ListView drawerList = (ListView) findViewById(R.id.left_drawer_showroom);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_showroom);
        drawerList.setAdapter(new DrawerAdapter(getApplicationContext()));
        Activity activity = this;
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DrawerItemOnClickAction.click(drawerLayout, activity, i, 1);
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

    private List<java.util.Map<String, Object>> getShowroomData(String showroom) {
        List<java.util.Map<String, Object>> list = new LinkedList<>();
        for (ShowroomItem exhibitionHall : exhibitionHalls) {
            if (exhibitionHall.getName().equals(showroom)) {
                for (DataItem item : exhibitionHall.getExhibits()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", item.getId());
                    map.put("name", item.getName());
                    map.put("description", item.getDescription().length() >= 80
                            ? item.getDescription().substring(0, 80) + "..." : item.getDescription());
                    String drawableName = item.getImgId();
                    int res = getResources().getIdentifier(drawableName, "drawable", getPackageName());
                    map.put("img", res);
                    list.add(map);
                }
            }
        }
        return list;
    }

    public static DataItem getDataById(int id) {
        for (DataItem item : data) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}
