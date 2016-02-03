package com.iShamrock.iMuseum.acvitity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.widget.ImageView;
import com.iShamrock.iMuseum.R;
import com.iShamrock.iMuseum.util.ImageHandler;
import com.iShamrock.iMuseum.util.ViewPagerAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by mayezhou on 16/2/3.
 */
public class Test extends Activity {
    public static final String LOG_TAG = "TEST";
    public ImageHandler handler = new ImageHandler(new WeakReference<Test>(this));
    public ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        viewPager = (ViewPager) findViewById(R.id.test_viewpager);
        LayoutInflater inflater = LayoutInflater.from(this);
        ImageView view1 = (ImageView) inflater.inflate(R.layout.viewpager_item, null);
        ImageView view2 = (ImageView) inflater.inflate(R.layout.viewpager_item, null);
        ImageView view3 = (ImageView) inflater.inflate(R.layout.viewpager_item, null);
        view1.setImageResource(R.drawable.pic1_homepage);
        view2.setImageResource(R.drawable.pic2_homepage);
        view3.setImageResource(R.drawable.pic3_homepage);
        ArrayList<ImageView> views = new ArrayList<ImageView>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
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
                        handler.sendEmptyMessage(ImageHandler.MSG_PAUSE);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
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
}
