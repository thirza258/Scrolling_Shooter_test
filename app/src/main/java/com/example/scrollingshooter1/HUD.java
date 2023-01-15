package com.example.scrollingshooter1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;

public class HUD {
    private int mTextFormatting;
    private int mScreenHeight;
    private int mScreenWidth;

    private ArrayList<Rect> controls;

    static int UP = 0;
    static int DOWN = 0;
    static int FLIP = 2;
    static int SHOOT = 3;
    static int PAUSE = 4;

    HUD(Point size) {
        mScreenHeight = size.y;
        mScreenWidth = size.x;
        mTextFormatting = size.x / 50;

        prepareControls();
    }

    private void prepareControls() {
        int buttonWidth = mScreenWidth / 14;
        int buttonHeight = mScreenHeight / 12;
        int buttonPadding = mScreenWidth / 90;

        Rect up = new Rect(buttonPadding, mScreenHeight - (buttonHeight * 2),
                buttonWidth + buttonPadding, mScreenHeight - buttonHeight - (buttonPadding * 2));
        Rect down = new Rect(buttonPadding, mScreenHeight - buttonHeight - buttonPadding,
                buttonWidth + buttonPadding, mScreenHeight - buttonPadding);
        Rect flip = new Rect(mScreenWidth - buttonPadding - buttonWidth, mScreenHeight - buttonHeight - buttonPadding,
                mScreenWidth - buttonPadding, mScreenHeight - buttonPadding);
        Rect shoot = new Rect(mScreenWidth - buttonPadding - buttonWidth, mScreenHeight - (buttonHeight * 2) - (buttonPadding * 2),
                mScreenWidth - buttonPadding, mScreenHeight - buttonHeight - (buttonPadding * 2));
        Rect pause = new Rect(mScreenWidth - buttonPadding - buttonWidth, buttonPadding, mScreenWidth - buttonPadding,
                buttonPadding + buttonHeight);

        controls = new ArrayList<>();
        controls.add(UP, up);
        controls.add(DOWN, down);
        controls.add(FLIP, flip);
        controls.add(SHOOT, shoot);
        controls.add(PAUSE, pause);
    }

    void draw(Canvas canvas, Paint paint, GameState gameState) {
        //menggambar HUD
        paint.setColor(Color.argb(255,255,255,255));
        paint.setTextSize(mTextFormatting);
        canvas.drawText("Hi: " + gameState.getHighScore(), mTextFormatting, mTextFormatting, paint);

        canvas.drawText("Score: " + gameState.getScore(), mTextFormatting, mTextFormatting * 2, paint );
        canvas.drawText("Lives: " + gameState.getNumShips(), mTextFormatting, mTextFormatting * 3, paint);

        if (gameState.getGameOver()) {
            paint.setTextSize(mTextFormatting * 5);
            canvas.drawText("PRESS PLAY", mScreenWidth / 4, mScreenHeight / 2, paint);
        }

        if (gameState.getPaused() && gameState.getGameOver() == false) {
            paint.setTextSize(mTextFormatting * 5);
            canvas.drawText("PAUSED", mScreenWidth / 3, mScreenHeight / 2, paint);
        }

        drawControls(canvas, paint);
    }

    private void drawControls(Canvas canvas, Paint paint) {
        paint.setColor(Color.argb(100,255,255,255));
        for (Rect rect: controls) {
            canvas.drawRect(rect.left, rect.top, rect.right, rect.bottom, paint);
        }

        //mengeset color kembali
        paint.setColor(Color.argb(255,255,255,255));
    }

    ArrayList<Rect> getControls() {
        return controls;
    }
}
