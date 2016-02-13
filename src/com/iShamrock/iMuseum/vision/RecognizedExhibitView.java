package com.iShamrock.iMuseum.vision;

import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import com.iShamrock.iMuseum.acvitity.Exhibit;
import com.iShamrock.iMuseum.entity.RecognizedExhibit;

import java.util.ArrayList;

/**
 * Created by lifengshuang on 11/24/15.
 */
public class RecognizedExhibitView extends View {

    private Paint linePaint;
    private Paint textPaint;
    private Paint rectPaint;
    private Context context;
    private float touchX;
    private float touchY;
    private ArrayList<RecognizedExhibit> recognizedExhibits = new ArrayList<>();

    public RecognizedExhibitView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        linePaint = new Paint();
        linePaint.setStrokeWidth(4);
        linePaint.setColor(Color.BLUE);
        textPaint = new Paint();
        textPaint.setTextSize(60);
        rectPaint = new Paint();
        rectPaint.setColor(Color.YELLOW);
        rectPaint.setAlpha(60);

        recognizedExhibits.add(new RecognizedExhibit("test1", resizeRect(new Rect(300, 300, 600, 600)), 1));
        recognizedExhibits.add(new RecognizedExhibit("test2", resizeRect(new Rect(400, 500, 800, 800)), 1));
    }

    public void setRecognizedExhibits(ArrayList<RecognizedExhibit> recognizedExhibits) {
        this.recognizedExhibits = recognizedExhibits;
    }

    private Rect resizeRect(Rect rect) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        return new Rect(rect.left * width / 1000, rect.top * height / 1000, rect.right * width / 1000, rect.bottom * height / 1000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (RecognizedExhibit exhibit : recognizedExhibits) {
            int centerX = exhibit.getRect().centerX();
            int centerY = exhibit.getRect().centerY();
            int nextX = centerX + 150;
            int nextY = centerY - 300;
            int finalX = nextX + 200;
            int finalY = nextY;

            int textX = nextX + 30;
            int textY = nextY - 30;
            canvas.drawLine(centerX, centerY, nextX, nextY, linePaint);
            canvas.drawLine(nextX, nextY, finalX, finalY, linePaint);
            canvas.drawText(exhibit.getName(), textX, textY, textPaint);
            canvas.drawRect(exhibit.getRect(), rectPaint);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("Touch Event on Vision. X: " + event.getX() + ", Y: " + event.getY());
//        if (event.getAction() == MotionEvent.ACTION_UP) {
//            if (Math.abs(this.touchX - event.getX()) < 30 && Math.abs(this.touchY - event.getY()) < 30) {
                for (RecognizedExhibit exhibit : recognizedExhibits) {
                    if (exhibit.getRect().contains((int) event.getX(), (int) event.getY())) {
                        //after recongnizing, get the id of the dataItem
                        int id = 1;

                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putInt("id",id);
                        intent.setClass(context, Exhibit.class);
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                        break;
                    }
                }
//            }
//        }
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            this.touchX = event.getX();
//            this.touchY = event.getY();
//        }
        return false;
    }
}
