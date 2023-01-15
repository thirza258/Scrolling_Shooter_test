package com.example.scrollingshooter1;

import android.graphics.PointF;

public class AlienChaseSpec extends ObjectSpec{
    //merupakan spesifikasi untuk objek game
    // untuk alien chase

    private static final String tag = "Alien";
    private static final String bitmapName = "alien_ship1";
    private static final float speed = 4f;
    private static final PointF relativeScale = new PointF(15f, 15f);

    private static final String [] components = new String[] {
            "StdGraphicComponent", "AlienChaseMovementComponent",
            "AlienHorizontalSpawnComponent"
    };

    AlienChaseSpec() {
        super(tag, bitmapName, speed, relativeScale, components);
    }
}
