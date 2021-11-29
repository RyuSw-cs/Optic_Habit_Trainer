package com.oht.UI.SecondStep;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.oht.Data.Circle;
import com.oht.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TrainBackground extends View {

    private int width = 0;
    private int height = 0;
    private ArrayList<Circle> shape = new ArrayList<>();
    private boolean check = true;
    int leftWidth = 80;
    int rightWidth = 180;
    int leftHeight = 80;
    int rightHeight = 180;
    int j = 0;
    int i = 0;
    Timer timer = new Timer();

    public TrainBackground(Context context, boolean check) {
        super(context);
        this.check = check;
    }

    public TrainBackground(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TrainBackground(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TrainBackground(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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
            //해당 작업이 1초마다 해야함.
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
        Paint changePaint = new Paint();
        /* 위치를 옮기며 파란색 원을 그림 */
        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                /* 1초마다 실행 */
                if (!(i > shape.size())) {
                    if (j == i) {
                        changePaint.setColor(Color.parseColor("#2977F3"));
                        shape.get(j).setPaint(changePaint);
                    }
                    canvas.drawArc(shape.get(i).getRect(), 0, 360, true, shape.get(i).getPaint());
                    changePaint.setColor(Color.parseColor("#BABABA"));
                    shape.get(i).setPaint(changePaint);
                    i++;
                }
                else {
                    timer.cancel();
                }
            }
        };

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);
            }
        };
        //타이머 실행
        timer.schedule(timerTask, 0, 1000);
    }

    public void startCheck(boolean temp) {
        check = temp;
        invalidate();
    }
}


