package com.example.scrollingshooter1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class Renderer {
    private Canvas mCanvas;
    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;

    Renderer(SurfaceView surfaceView) {
        mSurfaceHolder = surfaceView.getHolder();
        mPaint = new Paint();
    }

    void draw(ArrayList<GameObject> objects, GameState gameState, HUD hud, ParticleSystem particleSystem) {
        if(mSurfaceHolder.getSurface().isValid()) {
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.drawColor(Color.argb(255,0,0,0));

            if (gameState.getDrawing()) {
                //menggambar semua objek
                for(GameObject object : objects) {
                    if(object.checkActive()) {
                        object.draw(mCanvas, mPaint);
                    }
                }
            }

            if(gameState.getGameOver()) {
                //manggambar background
                objects.get(Level.BACKGROUND_INDEX).draw(mCanvas, mPaint);
            }

            //menggambar partikel sistem
            if(particleSystem.mIsRunning) {
                particleSystem.draw(mCanvas, mPaint);
            }

            //menggambar hud diatas
            hud.draw(mCanvas, mPaint, gameState);

            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }
}
