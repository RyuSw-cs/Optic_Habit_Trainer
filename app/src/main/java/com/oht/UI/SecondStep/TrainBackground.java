package com.oht.UI.SecondStep;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;

import android.graphics.Paint;

import android.graphics.RectF;

import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.oht.Data.Circle;
import com.oht.UI.FirstStep.Result.FirstStepEndActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TrainBackground extends View {

    public ArrayList<Circle> shape;
    public boolean check;
    public int leftWidth, rightWidth, leftHeight, rightHeight, height, width;
    public Paint change;
    public Handler handler;
    public Timer timer;
    public TimerTask timerTask;
    public final int[] i = {0, 0};

    public TrainBackground(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        change = new Paint();
        leftWidth = 80;
        rightWidth = 180;
        leftHeight = 80;
        rightHeight = 180;
        width = 0;
        height = 0;
        check = true;
        shape = new ArrayList<>();
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
        super.onDraw(canvas);
        if (check) {
            drawing(canvas);
        } else {
            train(canvas);
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
                leftWidth += 144;
                rightWidth += 144;
            }
            leftWidth = 70;
            rightWidth = 170;
            leftHeight += 140;
            rightHeight += 140;
        }
    }

    public void train(Canvas canvas) {
        Paint test = new Paint();
        handler = new Handler() {
            public void handleMessage(Message msg) {
                if (i[0] < shape.size()) {
                    if (i[1] == i[0]) {
                        test.setColor(Color.parseColor("#2977F3"));
                        shape.get(i[0]).setPaint(test);
                    }
                    while (i[1] < shape.size()) {
                        canvas.drawArc(shape.get(i[1]).getRect(), 0, 360, true, shape.get(i[1]).getPaint());
                        test.setColor(Color.parseColor("#BABABA"));
                        shape.get(i[1]).setPaint(test);
                        i[1]++;
                    }
                    i[1] = 0;
                    i[0]++;
                } else {
                    timer.cancel();
                }
            }
        };

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);
            }
        };
        timer.schedule(timerTask, 0,3000);
    }

    public void startCheck(boolean temp) {
        check = temp;
        invalidate();
    }
}


