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
    private TextView[] map_touch;
    private int map_showroom_num = 12;
    private ListView drawerList;
    private ImageButton leftDrawerBtn;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        initLeftDrawer();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_map);
        leftDrawerBtn = (ImageButton) findViewById(R.id.left_drawer_btn_map);
        leftDrawerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerList);
            }
        });

        /* use TabHost to show the maps of floors. */
        TabHost tabHost = (TabHost)this.findViewById(R.id.tabHost);
        tabHost.setup();

        tabHost.addTab(tabHost.newTabSpec("floor1").setIndicator("floor1").setContent(R.id.map_layout_floor1));
        tabHost.addTab(tabHost.newTabSpec("floor2").setIndicator("floor2").setContent(R.id.map_layout_floor2));
        tabHost.addTab(tabHost.newTabSpec("floor3").setIndicator("floor3").setContent(R.id.map_layout_floor3));
        tabHost.addTab(tabHost.newTabSpec("floor4").setIndicator("floor4").setContent(R.id.map_layout_floor4));
        tabHost.setCurrentTab(0);

        Activity activity = this;
        map_touch = new TextView[map_showroom_num];
        map_touch[0] = (TextView) this.findViewById(R.id.map_touch0);
        map_touch[1] = (TextView) this.findViewById(R.id.map_touch1);
        map_touch[2] = (TextView) this.findViewById(R.id.map_touch2);
        map_touch[3] = (TextView) this.findViewById(R.id.map_touch3);
        map_touch[4] = (TextView) this.findViewById(R.id.map_touch4);
        map_touch[5] = (TextView) this.findViewById(R.id.map_touch5);
        map_touch[6] = (TextView) this.findViewById(R.id.map_touch6);
        map_touch[7] = (TextView) this.findViewById(R.id.map_touch7);
        map_touch[8] = (TextView) this.findViewById(R.id.map_touch8);
        map_touch[9] = (TextView) this.findViewById(R.id.map_touch9);
        map_touch[10] = (TextView) this.findViewById(R.id.map_touch10);
        map_touch[11] = (TextView) this.findViewById(R.id.map_touch11);

        map_touch[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(activity, Showroom.class);
                intent.putExtra("name", "第一展览馆");
                startActivity(intent);
            }
        });
        map_touch[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(activity, Showroom.class);
                intent.putExtra("name", "中国古代雕塑馆");
                startActivity(intent);
            }
        });
        map_touch[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(activity, Showroom.class);
                intent.putExtra("name", "中国古代青铜馆");
                startActivity(intent);
            }
        });
        map_touch[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(activity, Showroom.class);
                intent.putExtra("name", "中国古代陶瓷馆");
                startActivity(intent);
            }
        });
        map_touch[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(activity, Showroom.class);
                intent.putExtra("name", "第二展览馆");
                startActivity(intent);
            }
        });
        map_touch[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(activity, Showroom.class);
                intent.putExtra("name", "中国历代绘画馆");
                startActivity(intent);
            }
        });
        map_touch[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(activity, Showroom.class);
                intent.putExtra("name", "中国历代书法馆");
                startActivity(intent);
            }
        });
        map_touch[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(activity, Showroom.class);
                intent.putExtra("name", "中国历代玺印馆");
                startActivity(intent);
            }
        });
        map_touch[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(activity, Showroom.class);
                intent.putExtra("name", "中国少数民族工艺馆");
                startActivity(intent);
            }
        });
        map_touch[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(activity, Showroom.class);
                intent.putExtra("name", "中国历代钱币馆");
                startActivity(intent);
            }
        });
        map_touch[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(activity, Showroom.class);
                intent.putExtra("name", "中国历代玉器馆");
                startActivity(intent);
            }
        });
        map_touch[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(activity, Showroom.class);
                intent.putExtra("name", "中国明清家具馆");
                startActivity(intent);
            }
        });
    }
    private void initLeftDrawer() {
        drawerList = (ListView) findViewById(R.id.left_drawer_map);
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
