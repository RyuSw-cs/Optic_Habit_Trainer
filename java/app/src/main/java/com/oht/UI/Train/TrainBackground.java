package com.oht.UI.Train;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

import android.graphics.Paint;

import android.graphics.RectF;

import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.oht.Data.Circle;

import java.util.ArrayList;

public class TrainBackground extends View {

    public ArrayList<Circle> shape;
    public boolean check;
    public int leftWidth, rightWidth, leftHeight, rightHeight, height, width;
    public Paint change;
    public Handler handler;
    public Context context;
    public int count = 0;
    public int size = 0;

    public TrainBackground(Context context) {
        super(context);
        init();
        this.setWillNotDraw(false);
    }

    public TrainBackground(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        this.setWillNotDraw(false);
    }

    public TrainBackground(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        this.setWillNotDraw(false);
    }

    public TrainBackground(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
        this.setWillNotDraw(false);
    }


    private void init() {
        change = new Paint();
        leftWidth = 30;
        rightWidth = 130;
        leftHeight = 20;
        rightHeight = 120;
        width = 0;
        height = 0;
        check = true;
        shape = new ArrayList<>();
        handler = new Handler();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /* 왠만하면 객체 생성 금지 */
        if (check) {
            drawing(canvas);
        } else {
            train(canvas, count);
            try {
                Thread.sleep(154);
                return;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void drawing(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#BABABA"));
        while (height > rightHeight) {
            while (width > rightWidth) {
                RectF rectF = new RectF();
                rectF.set(leftWidth, leftHeight, rightWidth, rightHeight);
                shape.add(new Circle(rectF, paint));
                canvas.drawArc(rectF, 0, 360, true, paint);
                leftWidth += 155;
                rightWidth += 155;
            }
            leftWidth = 30;
            rightWidth = 130;
            leftHeight += 140;
            rightHeight += 140;
        }
        size = shape.size();
    }

    public void train(Canvas canvas, int count) {
        if (count > size) {
            shape.clear();
            drawing(canvas);
        }
        for (int j = 0; j < size; j++) {
            if (j == count) {
                change.setColor(Color.parseColor("#2977F3"));
                shape.get(count).setPaint(change);
            }
            canvas.drawArc(shape.get(j).getRect(), 0, 360, true, shape.get(j).getPaint());
            change.setColor(Color.parseColor("#BABABA"));
            shape.get(j).setPaint(change);
        }
        this.count++;
    }
}



