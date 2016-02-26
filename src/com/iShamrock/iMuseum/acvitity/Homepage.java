package com.iShamrock.iMuseum.acvitity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.*;
import com.iShamrock.iMuseum.R;
import com.iShamrock.iMuseum.data.MuseumData;

import com.iShamrock.iMuseum.util.DrawerAdapter;
import com.iShamrock.iMuseum.util.DrawerItemOnClickAction;
import com.iShamrock.iMuseum.util.ImageHandler;
import com.iShamrock.iMuseum.util.ViewPagerAdapter;

import java.lang.ref.WeakReference;
import java.util.*;

/**
 * Created by lifengshuang on 11/18/15.
 */
public class Homepage extends Activity{
//    private AnimationDrawable animationDrawable;
//    private ImageView ivAnimView;
//    private Animation animation;
    private ImageButton imageButton;
    public static final String LOG_TAG = "HOMEPAGE";
    public ImageHandler handler = new ImageHandler(new WeakReference<Homepage>(this));
    public ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        MuseumData.initData(this);
        initLeftDrawer();
        //myz start here
        /*
        animation = AnimationUtils.loadAnimation(this, R.anim.translate_pic);
        ivAnimView.setAnimation(animation);
        animation.start();
        animation.setRepeatCount(Animation.INFINITE);
        ivAnimView.setVisibility(ImageView.VISIBLE);
        */

        /*
        ivAnimView = (ImageView) findViewById(R.id.mainPic);
        ivAnimView.setBackgroundResource(R.anim.homepage_news);
        Object backgroundObject = ivAnimView.getBackground();
        animationDrawable = (AnimationDrawable) backgroundObject;
        animationDrawable.stop();
        animationDrawable.start();
        */

        imageButton = (ImageButton) findViewById(R.id.hello);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Homepage.this, Navigation.class);
                startActivity(intent);
            }
        });


        initShowroomList();
        initViewPager();
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.hp_viewpager);
        LayoutInflater inflater = LayoutInflater.from(this);
        ImageView view1 = (ImageView) inflater.inflate(R.layout.viewpager_item, null);
        ImageView view2 = (ImageView) inflater.inflate(R.layout.viewpager_item, null);
        ImageView view3 = (ImageView) inflater.inflate(R.layout.viewpager_item, null);
        ImageView view4 = (ImageView) inflater.inflate(R.layout.viewpager_item, null);
        view1.setImageResource(R.drawable.pic1_homepage);
        view2.setImageResource(R.drawable.pic2_homepage);
        view3.setImageResource(R.drawable.pic3_homepage);
        view4.setImageResource(R.drawable.pic4_homepage);
        ArrayList<ImageView> views = new ArrayList<ImageView>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        viewPager.setAdapter(new ViewPagerAdapter(views));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                handler.sendMessage(Message.obtain(handler, ImageHandler.MSG_PAGE_CHANGED, i, 0));
            }

            @Override //implement pause & resume
            public void onPageScrollStateChanged(int i) {
                switch (i) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        handler.removeMessages(ImageHandler.MSG_UPDATE_IMAGE);
                        handler.sendEmptyMessage(ImageHandler.MSG_PAUSE);
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        handler.sendEmptyMessageDelayed(ImageHandler.MSG_RESUME, ImageHandler.MSG_DELAY);
                        break;
                    default:
                        break;
                }
            }
        });
        //default initial: intermediate
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2);
        //start
        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
    }

    private void initShowroomList() {
        ListView showroomList = (ListView) findViewById(R.id.showroomList);
        List<java.util.Map<String, Object>> data = MuseumData.getShowroomList();
        showroomList.setAdapter(new SimpleAdapter(this, data,
                R.layout.homepage_listview, new String[]{"name", "englishName", "location"},
                new int[]{R.id.showroom_name, R.id.showroom_englishName, R.id.showroom_location}));

        showroomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), (String) data.get(i).get("name"), Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(Homepage.this, Showroom.class);
                intent.putExtra("name", (String) data.get(i).get("name"));
                startActivity(intent);
            }
        });
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
