package com.example.scrollingshooter1;

import android.content.Context;
import android.content.SharedPreferences;

public class GameState {
    private static volatile boolean mThreadRunning = false;
    private static volatile boolean mPaused = true;
    private static volatile boolean mGameOver = true;
    private static volatile boolean mDrawing = true;

    //akan memiliki akses ke deSpawnRespawn method di game engine
    private GameStarter gameStarter;

    private int mScore;
    private int mHighScore;
    private int mNumShips;

    //agar high score tetap ada
    private SharedPreferences.Editor mEditor;

    GameState (GameStarter gs, Context context) {
        //inisialisasi gamestarter
        gameStarter = gs;

        //mendapatkan highscore sekarang
        SharedPreferences prefs;
        prefs = context.getSharedPreferences("HiScore", Context.MODE_PRIVATE);

        //inisialisasi agar mEditor siap
        mEditor = prefs.edit();

        //mengambil data highscore dari file jika tidak ada akan di set ke 0
        mHighScore = prefs.getInt("hi_score", 0);
    }

    private void endGame() {
        mGameOver = true;
        mPaused = true;
        if(mScore > mHighScore) {
            mHighScore = mScore;
            mEditor.putInt("hi_score", mHighScore);
            mEditor.commit();
        }
    }

    void startNewGame() {
        mScore = 0;
        mNumShips = 3;
        //tidak menggambar ketika despawnrespawn sedang menghilangkan objek dan menggambar kembali
        stopDrawing();
        gameStarter.deSpawnRespawn();
        resume();

        //setelah itu digambar kembali
        startDrawing();
    }

    void loseLife(SoundEngine se) {
        mNumShips = mNumShips - 1;
        se.playPlayerExplode();
        if(mNumShips == 0) {
            pause();
            endGame();
        }
    }

    int getNumShips() {
        return mNumShips;
    }

    void increaseScore() {
        mScore = mScore + 1;
    }

    int getScore() {
        return mScore;
    }

    int getHighScore() {
        return mHighScore;
    }

    void pause() {
        mPaused = true;
    }

    void resume() {
        mGameOver = false;
        mPaused = false;
    }

    void stopEverything() {
        mPaused = true;
        mGameOver = true;
        mThreadRunning = false;
    }

    boolean getThreadRunning() {
        return mThreadRunning;
    }

    void startThread() {
        mThreadRunning = true;
    }

    private void stopDrawing() {
        mDrawing = false;
    }

    private void startDrawing() {
        mDrawing = true;
    }

    boolean getDrawing() {
        return mDrawing;
    }

    boolean getPaused() {
        return mPaused;
    }

    boolean getGameOver() {
        return mGameOver;
    }
}
