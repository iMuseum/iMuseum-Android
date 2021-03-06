package com.iShamrock.iMuseum.acvitity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.*;
import com.iShamrock.iMuseum.R;
import com.iShamrock.iMuseum.data.MuseumData;
import com.iShamrock.iMuseum.util.DrawerAdapter;
import com.iShamrock.iMuseum.util.DrawerItemOnClickAction;
import java.util.*;
import java.util.Map;

/**
 * Created by lifengshuang on 11/18/15.
 */
public class Favor extends Activity {
    private static Set<Integer> favors = new HashSet<>();
    private ListView drawerList;
    private ImageButton leftDrawerBtn;
    private DrawerLayout drawerLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favor);
        initLeftDrawer();
        initFavorList();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_favor);
        leftDrawerBtn = (ImageButton) findViewById(R.id.left_drawer_btn_favor);
        leftDrawerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerList);
            }
        });
    }

    private void initFavorList() {
        ListView favorList = (ListView) findViewById(R.id.list_favor);
        List<Map<String, Object>> data = MuseumData.getFavorData();
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
        drawerList = (ListView) findViewById(R.id.left_drawer_favor);
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

}