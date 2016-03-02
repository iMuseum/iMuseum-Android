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

/**
 * Created by mayezhou on 16/2/28.
 */
public class ExhibitionHallOfFloor extends Activity {
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exhibitionhalloffloor);
        initLeftDrawer();

        /* get the floor of homepage */
        Bundle bundle = this.getIntent().getExtras();
        int floor = bundle.getInt("floor");
        List<java.util.Map<String, Object>> data = MuseumData.getExhibitionHallByFloor2(floor);

        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new SimpleAdapter(this, data,
                R.layout.exhibitionhall_gridview, new String[]{"img", "name", "englishName"},
                new int[]{R.id.exhibitionHall_img, R.id.exhibitionHall_name, R.id.exhibitionHall_englishName}));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), (String) data.get(i).get("name"), Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(ExhibitionHallOfFloor.this, Showroom.class);
                intent.putExtra("name", (String) data.get(i).get("name"));
                startActivity(intent);
            }
        });
    }

    private void initLeftDrawer() {
        ListView drawerList = (ListView) findViewById(R.id.left_drawer_exhibitionhalloffloor);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_exhibitionhalloffloor);
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
}
