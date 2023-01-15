package com.example.scrollingshooter1;

import android.graphics.PointF;

public class PlayerSpec extends ObjectSpec{
    //spesifikasi untuk player
    private static final String tag = "Player";
    private static final String bitmapName = "player_ship";
    private static final float speed = 1f;
    private static final PointF relativeScale = new PointF(15f, 15f);

    private static final String [] components = new String[] {
            "PlayerInputComponents",
            "StdGraphicsComponent",
            "PlayerMovementComponent",
            "PlayerSpawnComponent"
    };

    PlayerSpec() {
        super(tag, bitmapName, speed, relativeScale, components);
    }
}
