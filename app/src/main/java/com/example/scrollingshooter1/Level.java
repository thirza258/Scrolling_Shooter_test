package com.example.scrollingshooter1;

import android.content.Context;
import android.graphics.PointF;

import java.util.ArrayList;

public class Level {
    //mengetrack tipe spesifik
    public static final int BACKGROUND_INDEX = 0;
    public static final int PLAYER_INDEX = 1;
    public static final int FIRST_PLAYER_LASER = 2;
    public static final int LAST_PLAYER_LASER = 4;
    public static int mNextPlayerLaser;
    public static final int FIRST_ALIEN = 5;
    public static final int SECOND_ALIEN = 6;
    public static final int THIRD_ALIEN = 7;
    public static final int FOURTH_ALIEN = 8;
    public static final int FIFTH_ALIEN = 9;
    public static final int SIXTH_ALIEN = 10;
    public static final int LAST_ALIEN = 10;
    public static final int FIRST_ALIEN_LASER = 11;
    public static final int LAST_ALIEN_LASER = 15;
    public static int mNextAlienLaser;

    //untuk menyimpan semua instance dari gameObject
    private ArrayList<GameObject> objects;

    public Level(Context context, PointF mScreenSize, GameEngine gameEngine) {
        objects = new ArrayList<>();
        GameObjectFactory factory = new GameObjectFactory(context, mScreenSize, gameEngine);

        buildGameObjects(factory);
    }

    ArrayList<GameObject> buildGameObjects(GameObjectFactory factory) {
        objects.clear();
        objects.add(BACKGROUND_INDEX, factory.create(new BackgroundSpec()));

        objects.add(PLAYER_INDEX, factory.create(new PlayerSpec()));

        //mengespawn laser player
        for(int i = FIRST_PLAYER_LASER; i != LAST_PLAYER_LASER + 1 ; i++) {
            objects.add(i, factory.create(new PlayerLaserSpec()));
        }

        mNextPlayerLaser = FIRST_PLAYER_LASER;

        //membuat alien

        //membuat laser alien

        return objects;
    }

    ArrayList<GameObject> getGameObject() {
        return objects;
    }
}
