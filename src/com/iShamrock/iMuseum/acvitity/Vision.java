package com.iShamrock.iMuseum.acvitity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.iShamrock.iMuseum.R;
import com.iShamrock.iMuseum.entity.RecognizedExhibit;
import com.iShamrock.iMuseum.util.DrawerAdapter;
import com.iShamrock.iMuseum.util.DrawerItemOnClickAction;
import com.iShamrock.iMuseum.vision.RecognizedExhibitView;

import java.util.ArrayList;


/**
 * Created by lifengshuang on 11/18/15.
 */
public class Vision extends Activity {

    private RecognizedExhibitView recognizedExhibitView;
    public static DisplayMetrics displayMetrics;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.vision);
        displayMetrics = getResources().getDisplayMetrics();

        recognizedExhibitView = (RecognizedExhibitView) findViewById(R.id.recognized_exhibit_view);
        initLeftDrawer();

//        ArrayList<RecognizedExhibit> recognizedExhibits = new ArrayList<>();
//        recognizedExhibitView.setRecognizedExhibits(recognizedExhibits);

    }



    private void initLeftDrawer() {
        ListView drawerList = (ListView) findViewById(R.id.left_drawer_vision);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_vision);
        drawerList.setAdapter(new DrawerAdapter(getApplicationContext()));
        Activity activity = this;
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DrawerItemOnClickAction.click(drawerLayout, activity, i, 3);
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