package com.iShamrock.iMuseum.util;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import com.iShamrock.iMuseum.acvitity.Favor;
import com.iShamrock.iMuseum.acvitity.Homepage;
import com.iShamrock.iMuseum.acvitity.Map;
import com.iShamrock.iMuseum.acvitity.Vision;

/**
 * Created by lifengshuang on 11/18/15.
 */
public class DrawerItemOnClickAction {

    public static void click(DrawerLayout drawerLayout, Activity activity, int i, int order) {
        Intent intent = new Intent();
        if (i != order) {
            switch (i) {
                case 1:
                    intent.setClass(activity, Homepage.class);
                    activity.startActivity(intent);
                    break;
                case 2:
                    intent.setClass(activity, Map.class);
                    activity.startActivity(intent);
                    break;
                case 3:
                    intent.setClass(activity, Vision.class);
                    activity.startActivity(intent);
                    break;
                case 4:
                    intent.setClass(activity, Favor.class);
                    activity.startActivity(intent);
                    break;
            }
            activity.finish();
        }

        drawerLayout.closeDrawer(Gravity.LEFT);
    }
}
