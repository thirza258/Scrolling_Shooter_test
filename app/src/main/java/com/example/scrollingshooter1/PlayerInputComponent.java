package com.example.scrollingshooter1;

import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;

public class PlayerInputComponent implements InputComponent, InputObserver{

    private Transform mTransform;
    private PlayerLaserSpawner mPLS;

    PlayerInputComponent(GameEngine gameEngine) {
        gameEngine.addObserver(this);
        mPLS = gameEngine;
    }

    @Override
    public void setTransform(Transform transform) {
        mTransform = transform;
    }

    @Override
    public void handleInput(MotionEvent event, GameState gameState, ArrayList<Rect> controls) {
        int i = event.getActionIndex();
        int x = (int) event.getX(i);
        int y = (int) event.getY(i);

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                if(controls.get(HUD.UP).contains(x,y) || controls.get(HUD.DOWN).contains(x,y)) {
                    //player akan keatas atau bawah
                    mTransform.stopVertical();
                }
                break;
            case MotionEvent.ACTION_DOWN:
                if(controls.get(HUD.UP).contains(x,y)) {
                    //player memencet atas
                    mTransform.headUp();
                }
                else if(controls.get(HUD.DOWN).contains(x,y)) {
                    mTransform.headDown();
                }
                else if(controls.get(HUD.FLIP).contains(x,y)) {
                    mTransform.flip();
                }
                else if(controls.get(HUD.SHOOT).contains(x,y)) {
                    mPLS.spawnPlayerLaser(mTransform);
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                if(controls.get(HUD.UP).contains(x,y)) {
//                    player akan ke atas atau ke bawah
                    mTransform.stopVertical();
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                if(controls.get(HUD.UP).contains(x,y)) {
                    mTransform.headUp();
                }
                else if(controls.get(HUD.DOWN).contains(x,y)) {
                    mTransform.headDown();
                }
                else if(controls.get(HUD.SHOOT).contains(x,y)) {
                    mPLS.spawnPlayerLaser(mTransform);
                }
                break;
        }
    }
}
