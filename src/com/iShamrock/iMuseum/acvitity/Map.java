package com.iShamrock.iMuseum.acvitity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.*;
import com.iShamrock.iMuseum.R;
import com.iShamrock.iMuseum.util.DrawerAdapter;
import com.iShamrock.iMuseum.util.DrawerItemOnClickAction;

/**
 * Created by lifengshuang on 11/18/15.
 */
public class Map extends Activity{
    private TextView map_touch0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        /* use TabHost to show the maps of floors. */
        TabHost tabHost = (TabHost)this.findViewById(R.id.tabHost);
        tabHost.setup();

        tabHost.addTab(tabHost.newTabSpec("floor1").setIndicator("floor1").setContent(R.id.map_layout_floor1));
        tabHost.addTab(tabHost.newTabSpec("floor2").setIndicator("floor2").setContent(R.id.map_layout_floor2));
        tabHost.addTab(tabHost.newTabSpec("floor3").setIndicator("floor3").setContent(R.id.map_layout_floor3));
        tabHost.addTab(tabHost.newTabSpec("floor4").setIndicator("floor4").setContent(R.id.map_layout_floor4));
        tabHost.setCurrentTab(0);

        Activity activity = this;
        map_touch0 = (TextView) this.findViewById(R.id.map_touch0);
        map_touch0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Map.this, Showroom.class);
                intent.putExtra("name", "中国古代陶瓷馆");//TODO
                startActivity(intent);
            }
        });

        initLeftDrawer();
    }
    private void initLeftDrawer() {
        ListView drawerList = (ListView) findViewById(R.id.left_drawer_map);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_map);
        drawerList.setAdapter(new DrawerAdapter(getApplicationContext()));
        Activity activity = this;
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DrawerItemOnClickAction.click(drawerLayout, activity, i, 2);
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
