package com.iShamrock.iMuseum.util;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by mayezhou on 16/1/31.
 */
public class ViewPagerAdapter extends PagerAdapter{
    private ArrayList<ImageView> imageList;

    public ViewPagerAdapter(ArrayList<ImageView> imageList) {
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;//thus users cannot reach the bound
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //do not removeView
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //for the size is too big, mod calculation is needed
        position %= imageList.size();
        if (position < 0) {
            position += imageList.size();
        }
        ImageView imageView = imageList.get(position);
        //if imageView has already added to a container, delete
        ViewParent viewParent = imageView.getParent();
        if (viewParent != null) {//actually implement remove here
            ViewGroup viewGroup = (ViewGroup) viewParent;
            viewGroup.removeView(imageView);
        }
        container.addView(imageView);
        //add listeners here is necessary
        return imageView;
    }
}
