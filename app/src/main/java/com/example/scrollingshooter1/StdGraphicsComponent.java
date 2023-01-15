package com.example.scrollingshooter1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;

public class StdGraphicsComponent implements GraphicsComponent{
    private Bitmap mBitmap;
    public Bitmap mBitmapReversed;

    @Override
    public void initialize(Context context, ObjectSpec objectSpec, PointF screenSize) {
        //membuat id dari string dari nama file
        int resID = context.getResources().getIdentifier(objectSpec.getBitmapName(), "drawable", context.getPackageName());

        //mengeload bitmap dari id
        mBitmap = BitmapFactory.decodeResource(context.getResources(), resID);

        //mengukur ulang bitmap
        mBitmap = Bitmap.createScaledBitmap(mBitmap, (int) screenSize.x, (int) screenSize.y, false);

        //membuat versi terbalik dari bitmap
        Matrix matrix = new Matrix();
        matrix.setScale(-1,1);
        mBitmapReversed = Bitmap.createBitmap(mBitmap, 0,0 ,mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
    }

    @Override
    public void draw(Canvas canvas, Paint paint, Transform transform) {
        if(transform.getFacingRight()) {
            canvas.drawBitmap(mBitmap, transform.getLocation().x, transform.getLocation().y, paint);
        }
        else {
            canvas.drawBitmap(mBitmapReversed, transform.getLocation().x, transform.getLocation().y, paint);
        }
    }
}
