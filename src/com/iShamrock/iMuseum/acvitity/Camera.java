package com.iShamrock.iMuseum.acvitity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.iShamrock.iMuseum.R;
import com.iShamrock.iMuseum.util.DrawerAdapter;
import com.iShamrock.iMuseum.util.DrawerItemOnClickAction;

/**
 * Created by mayezhou on 16/3/5.
 */
public class Camera extends Activity {
    private SensorManager sensorManager;
    private Sensor orientalSensor;
    //private CameraPre

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        initLeftDrawer();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//固定屏幕方向为横屏

    }

    private void initLeftDrawer() {
        ListView drawerList = (ListView) findViewById(R.id.left_drawer_camera);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_camera);
        drawerList.setAdapter(new DrawerAdapter(getApplicationContext()));
        Activity activity = this;
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DrawerItemOnClickAction.click(drawerLayout, activity, i, -1);
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
