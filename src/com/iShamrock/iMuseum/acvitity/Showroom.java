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
 * Created by wangxin on 16/1/27.
 */
public class Showroom extends Activity{
    private TextView showroom_title;
    private String showroom;

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
        initShowroomList();
    }

    private void initShowroomList() {
        ListView showroomList = (ListView) findViewById(R.id.list_showroom);
        List<java.util.Map<String, Object>> data = MuseumData.getShowroomData(showroom);
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
}
