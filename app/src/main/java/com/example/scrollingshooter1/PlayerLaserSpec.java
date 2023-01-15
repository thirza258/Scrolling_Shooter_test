package com.example.scrollingshooter1;

import android.graphics.PointF;

public class PlayerLaserSpec extends ObjectSpec{
    //spesifikasi untuk player laser
    private static final String tag = "Player Laser";
    private static final String bitmapName = "player_laser";
    private static final float speed = .65f;
    private static final PointF relativeScale = new PointF(8f, 160f);

    private static final String [] components = new String[] {
            "StdGraphicsComponent",
            "LaserMovementComponent",
            "LaserSpawnComponent"
    };

    PlayerLaserSpec() {
        super(tag, bitmapName, speed, relativeScale, components);
    }
}
