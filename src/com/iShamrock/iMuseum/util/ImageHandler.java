package com.iShamrock.iMuseum.util;


import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.iShamrock.iMuseum.acvitity.Homepage;

import java.lang.ref.WeakReference;

/**
 * Created by mayezhou on 16/2/3.
 */
public class ImageHandler extends Handler {
    public static final int MSG_DELAY = 3000;
    public static final int MSG_UPDATE_IMAGE = 0;
    public static final int MSG_PAUSE = 1;
    public static final int MSG_RESUME = 2;
    public static final int MSG_PAGE_CHANGED = 3;

    private WeakReference<Homepage> weakReference;
    private int currentItem = 0;

    public ImageHandler(WeakReference<Homepage> weakReference) {//TODO
        this.weakReference = weakReference;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        Log.d(Homepage.LOG_TAG, "receive message " + msg.what);
        Homepage activity = weakReference.get();
        if (activity == null) {
            //if activity is destroyed, do not need to deal with UI
            return;
        }
        switch (msg.what) {
            case MSG_UPDATE_IMAGE:
                activity.handler.removeMessages(ImageHandler.MSG_UPDATE_IMAGE);
                currentItem++;
                activity.viewPager.setCurrentItem(currentItem);
                //cycle
                activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                break;
            case MSG_PAUSE:
                //do nothing
                break;
            case MSG_RESUME:
                activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                break;
            case MSG_PAGE_CHANGED:
                currentItem = msg.arg1;
                break;
            default:
                System.out.println("Warning! invalid input in ImageHandler!");
                break;
        }
    }
}
