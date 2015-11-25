package com.iShamrock.iMuseum.vision;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import com.iShamrock.iMuseum.entity.RecognizedExhibit;

import java.util.ArrayList;

/**
 * Created by lifengshuang on 11/24/15.
 */
public class RecognizedExhibitView extends View {

    private Paint linePaint;
    private Paint textPaint;
    private ArrayList<RecognizedExhibit> recognizedExhibits = new ArrayList<>();

    public RecognizedExhibitView(Context context, AttributeSet attrs) {
        super(context, attrs);
        linePaint = new Paint();
        linePaint.setStrokeWidth(6);
        linePaint.setColor(Color.BLUE);
        textPaint = new Paint();
        textPaint.setTextSize(60);

        recognizedExhibits.add(new RecognizedExhibit("test", new Rect(400, 400, 900, 900), 1));
    }

    public void setRecognizedExhibits(ArrayList<RecognizedExhibit> recognizedExhibits) {
        this.recognizedExhibits = recognizedExhibits;
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
        }
    }
}
