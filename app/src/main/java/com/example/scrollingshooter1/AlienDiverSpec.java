package com.example.scrollingshooter1;

import android.graphics.PointF;

public class AlienDiverSpec extends ObjectSpec {
    //merupakan spesifikasi untuk objek game
    //untuk alien dive
    private static final String tag = "Alien";
    private static final String bitmapName = "alien_ship3";
    private static final float speed = 4f;
    private static final PointF relativeScale = new PointF(60f, 30f);

    private static final String [] components = new String[] {
            "StdGraphicsComponents", "AlienDiverMovementComponent",
            "AlienVerticalSpawnComponent"
    };

    AlienDiverSpec() {
        super(tag, bitmapName, speed, relativeScale, components);
    }
}
