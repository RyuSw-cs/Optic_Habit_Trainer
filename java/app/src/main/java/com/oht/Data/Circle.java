package com.oht.Data;

import android.graphics.Paint;
import android.graphics.RectF;

public class Circle {
    private RectF rect;
    private Paint paint;

    public Circle(RectF rect, Paint paint) {
        this.rect = rect;
        this.paint = paint;
    }

    public RectF getRect() {
        return rect;
    }

    public void setRect(RectF rect) {
        this.rect = rect;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
}
