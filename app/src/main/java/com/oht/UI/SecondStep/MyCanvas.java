package com.oht.UI.SecondStep;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.oht.Data.Circle;
import com.oht.R;

import java.util.ArrayList;

public class MyCanvas extends View {

    private int width = 0;
    private int height = 0;
    private int leftWidth = 50;
    private int rightWidth = 50;
    private int leftHeight = 100;
    private int rightHeight = 100;
    private ArrayList<Circle>shape = new ArrayList<>();

    public MyCanvas(Context context) {
        super(context);
    }

    public MyCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCanvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyCanvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
    }

    //뷰의 크기를 가져오고
    //크기마다 if 문을 넣어줍니다.
    //일정한 간격마다 추가해주고
    //while 문으로 제어

    //애니메이션으로 쇽쇽쇽?
    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#BABABA"));
        super.onDraw(canvas);
        RectF rectF = new RectF();
        while(height > rightHeight){
            while(width > rightWidth){
                rectF.set(leftWidth,leftHeight,rightWidth,rightHeight);
                shape.add(new Circle(rectF,paint));
                canvas.drawArc(rectF,0,360,true,paint);
                leftWidth += 70;
                rightWidth += 70;
            }
            leftWidth = 50;
            rightWidth = 100;
            leftHeight += 70;
            rightWidth += 70;
        }
    }
    /*
            while(height-100 > nowHeight){
            while(width > nowWidth){
                canvas.drawCircle(nowHeight,nowWidth,50,paint);
                Log.d("draw", "현재 가로: " + String.valueOf(nowWidth) + "현재 세로 : " + String.valueOf(nowHeight));
                Log.d("check", String.valueOf(width) +", " + String.valueOf(height));
            }
            nowHeight += 70;
            nowWidth  = 50;
        }
     */
}
