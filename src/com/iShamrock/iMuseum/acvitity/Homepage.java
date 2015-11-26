package com.iShamrock.iMuseum.acvitity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.*;
import com.iShamrock.iMuseum.R;
import com.iShamrock.iMuseum.util.DrawerAdapter;
import com.iShamrock.iMuseum.util.DrawerItemOnClickAction;

/**
 * Created by lifengshuang on 11/18/15.
 */
public class Homepage extends Activity{
    private AnimationDrawable animationDrawable;
    private ImageView ivAnimView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        initLeftDrawer();
        //myz start here
        Button showPosition = (Button) findViewById(R.id.showMap);

        ivAnimView = (ImageView) findViewById(R.id.exhibitView);
        ivAnimView.setBackgroundResource(R.drawable.pic1_homepage);
        Object backgroundObject = ivAnimView.getBackground();
        animationDrawable = (AnimationDrawable) backgroundObject;
        animationDrawable.stop();
        animationDrawable.start();
    }

    private void initLeftDrawer() {
        ListView drawerList = (ListView) findViewById(R.id.left_drawer_homepage);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_homepage);
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
