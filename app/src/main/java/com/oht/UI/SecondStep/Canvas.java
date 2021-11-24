package com.oht.UI.SecondStep;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Canvas extends View {

    public Canvas(Context context) {
        super(context);
    }

    public Canvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Canvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Canvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#BABABA"));
        super.onDraw(canvas);
        canvas.drawCircle(100,100,70,paint);
    }
}
