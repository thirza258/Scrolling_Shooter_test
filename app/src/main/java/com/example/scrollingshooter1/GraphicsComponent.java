package com.example.scrollingshooter1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import androidx.constraintlayout.widget.ConstraintSet;

public interface GraphicsComponent {
    void initialize(Context context, ObjectSpec objectSpec, PointF screenSize);
    void draw(Canvas canvas, Paint paint, Transform transform);
}
