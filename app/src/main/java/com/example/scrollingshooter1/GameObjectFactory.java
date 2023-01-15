package com.example.scrollingshooter1;

import android.content.Context;
import android.graphics.PointF;

public class GameObjectFactory {
    private Context mContext;
    private PointF mScreenSize;
    private GameEngine mGameEngineReference;

    GameObjectFactory(Context context, PointF screenSize, GameEngine gameEngine) {
        this.mContext = context;
        this.mScreenSize = screenSize;
        mGameEngineReference = gameEngine;
    }

    GameObject create(ObjectSpec spec) {
        GameObject object = new GameObject();

        int numComponents = spec.getComponents().length;

        final  float HIDDEN = -2000f;

        object.setmTag(spec.getTag());

        //mengkonfigurasi kecepatan relatif ke screen size
        float speed = mScreenSize.x / spec.getSpeed();

        //mengkonfigurasi ukuran objek ke ukuran layar
        PointF objectSize = new PointF(mScreenSize.x / spec.getScale().x , mScreenSize.y / spec.getScale().y);

        //mengeset lokasi ke offscreen
        PointF location = new PointF(HIDDEN, HIDDEN);

        object.setTransform(new Transform(speed, objectSize.x, objectSize.y, location, mScreenSize));
        //kode tambahan

        //loop dan add/initialize semua komponen
        for(int i = 0; i < numComponents; i++) {
            switch (spec.getComponents()[i]) {
                case "PlayerInputComponent":
                    object.setInput(new PlayerInputComponent(mGameEngineReference));
                    break;
                case "StdGraphicsComponent":
                    object.setGraphics(new StdGraphicsComponent(), mContext, spec, objectSize);
                    break;
                case "PlayerMovementComponent":
                    object.setMovement(new PlayerMovementComponent());
                    break;
                case "LaserMovementComponent":
                    object.setMovement(new LaserMovementComponent());
                    break;
                case "PlayerSpawnComponent":
                    object.setSpawner(new PlayerSpawn());
                    break;
                case "LaserSpawnComponent":
                    object.setSpawner(new LaserSpawnComponent());
                    break;
                case "BackgroundGraphicsComponent":
                    object.setGraphics(new BackgroundGraphicsComponent(), mContext, spec, objectSize);
                    break;
                case "BackgroundMovementComponent":
                    object.setMovement(new BackgroundMovementComponent());
                    break;
                case "BackgroundSpawnComponent":
                    object.setSpawner(new BackgroundSpawnComponent());
                    break;
                default:
                    //error unidentified component
                    break;
            }
        }

        return object;
    }
}
