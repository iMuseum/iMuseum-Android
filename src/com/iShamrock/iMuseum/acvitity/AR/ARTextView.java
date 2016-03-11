package com.iShamrock.iMuseum.acvitity.AR;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by 逢双 on 14-2-22.
 */
public class ARTextView extends View {

    private DisplayMetrics dm = ARActivity.dm;
    Handler handler;
    Paint paint;

    public ARTextView(Context context, ARActivity activity) {
        super(context);
        //这里可以改paint
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(60);
        paint.setAntiAlias(true);

        handler = activity.handler;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.rotate(270);
//        this.canvas = canvas;
//        bubbleManager = MapView.bubbleManager;
//        bubblePiece[0] = BitmapFactory.decodeResource(getResources(), R.drawable.ar_bubble_left);
//        bubblePiece[1] = BitmapFactory.decodeResource(getResources(), R.drawable.ar_bubble_center);
//        bubblePiece[2] = BitmapFactory.decodeResource(getResources(), R.drawable.ar_bubble_right);
//        String message;
//        for (int i = 0; i < angleArray.size(); i++) {
//
//            int center_number = bubbleManager.getBubbleArray().get(i).getMessageLength();
//            int width = bubblePiece[0].getWidth() + bubblePiece[1].getWidth() * center_number + bubblePiece[2].getWidth();
//            int height = bubblePiece[0].getHeight();
//            result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//            Canvas canvas2 = new Canvas(result);
//            canvas2.drawBitmap(bubblePiece[0], 0, 0, null);
//            for (int j = 0; j < center_number; j++) {
//                canvas2.drawBitmap(bubblePiece[1], bubblePiece[0].getWidth() + bubblePiece[1].getWidth() * j, 0, null);
//            }
//            canvas2.drawBitmap(bubblePiece[2], width - bubblePiece[2].getWidth(), 0, null);
//
//            canvas.drawBitmap(result,
//                    (float) angleArray.get(i).getxPercent() * canvas.getWidth() - canvas.getWidth() - (float) (20 * dm.widthPixels / 320),
//                    (float) angleArray.get(i).getHeight() - (float) (14 * dm.widthPixels / 320), paint);
//
//
//            if (i < bubbleManager.getBubbleArray().size()) {
//                message = bubbleManager.getBubbleArray().get(i).getMessage();
//            } else break;
//            if (bubbleManager.getBubbleArray().get(i).getMessageLength() > 20) {
//                message = message.substring(0, 16) + "...";
//            }
//            canvas.drawText(message, (float) angleArray.get(i).getxPercent() * canvas.getWidth() - canvas.getWidth(),
//                    (float) angleArray.get(i).getHeight(), paint);
//        }
        //上面一堆被注释的主要是给文字加个框，因为文字是变长的所以框也要变长，时间有多的话可以试一试
        for (Angle angle : ARActivity.angleArray) {
            canvas.drawText(angle.getText(), (float) angle.getxPercent() * canvas.getWidth() - canvas.getWidth(),
                    (float) angle.getHeight(), paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        for (int i = 0; i < ARActivity.angleArray.size(); i++) {
            int heightIndex = i;
            if (heightIndex >= 2) {
                heightIndex += 2;
            }
            double heightTop = 73 * heightIndex + 250;
            double heightBottom = 73 * heightIndex + 310;
            double ratio = 1.0;

            if (event.getX() * ratio > heightTop && event.getX() * ratio < heightBottom) {
                handler.sendEmptyMessage((i+10)%12);
            }
        }
        return super.onTouchEvent(event);
    }
}
