package com.example.scrollingshooter1;

import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;

public class UIController implements InputObserver{

    public UIController(GameEngineBroadcaster engineBroadcaster) {
        engineBroadcaster.addObserver(this);
    }


    @Override
    public void handleInput(MotionEvent event, GameState gameState, ArrayList<Rect> controls) {
        int i = event.getActionIndex();
        int x = (int) event.getX(i);
        int y = (int) event.getY(i);

        int eventType = event.getAction() & MotionEvent.ACTION_MASK;

        if (eventType == MotionEvent.ACTION_UP || eventType == MotionEvent.ACTION_POINTER_UP) {
            if(controls.get(HUD.PAUSE).contains(x,y)) {
                // player hit pause button and reponse accordingly
                //jika game belum di pause
                if(gameState.getPaused() == false) {
                    gameState.pause();
                }

                //ghame selesai
                else if (gameState.getGameOver()) {
                    gameState.startNewGame();
                }

                else if (gameState.getPaused() && gameState.getGameOver() == false) {
                    gameState.resume();
                }
            }
        }
    }
}
