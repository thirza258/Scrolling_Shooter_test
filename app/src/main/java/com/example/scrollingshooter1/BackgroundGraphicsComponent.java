package com.example.scrollingshooter1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

public class BackgroundGraphicsComponent implements GraphicsComponent{

    private Bitmap mBitmap;
    private Bitmap mBitmapReversed;

    @Override
    public void initialize(Context context, ObjectSpec objectSpec, PointF screenSize) {
        //membuat id dari string dari nama file
        int resID = context.getResources().getIdentifier(objectSpec.getBitmapName(), "drawable",
                context.getPackageName());

        //mengeload bitmap dengan id
        mBitmap = BitmapFactory.decodeResource(context.getResources(), resID);

        //mengukur ulang dari bitmap
        mBitmap = Bitmap.createScaledBitmap(mBitmap, (int)screenSize.x, (int)screenSize.y, false);

        //membuat versi reversed dari bimap
        Matrix matrix = new Matrix();
        matrix.setScale(-1, 1);
        mBitmapReversed = Bitmap.createBitmap(mBitmap, 0,0, mBitmap.getWidth(), mBitmap.getHeight(),
                matrix, true);
    }

    @Override
    public void draw(Canvas canvas, Paint paint, Transform transform) {
        int xClip = transform.getXClip();
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        int startY = 0;
        int endY = (int) transform.getmScreenSize().y + 20;

        //untuk regular bitmap
        Rect fromRect1 = new Rect(0,0,width - xClip, height);
        Rect toRect1 = new Rect(xClip, startY, width, endY);

        //untuk reversed background
        Rect fromRect2 = new Rect(width - xClip, 0,width, height);
        Rect toRect2 = new Rect(0, startY, xClip, endY);

        //menggambar 2 background bitmap
        if(transform.getReversedFirst() == false) {
            canvas.drawBitmap(mBitmap, fromRect1, toRect1, paint);
            canvas.drawBitmap(mBitmapReversed, fromRect2, toRect2, paint);
        }
        else {
            canvas.drawBitmap(mBitmap, fromRect2, toRect2, paint);
            canvas.drawBitmap(mBitmapReversed, fromRect1, toRect1, paint);
        }
    }
}
