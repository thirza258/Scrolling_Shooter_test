package com.example.scrollingshooter1;

import android.graphics.Point;
import android.graphics.PointF;

public class Particle {
    PointF mVelocity;
    PointF mPosition;

    Particle(PointF direction) {
        mVelocity = new PointF();
        mPosition = new PointF();

        //menentukan arah
        mVelocity.x = direction.x;
        mVelocity.y = direction.y;
    }

    void update() {
        //memindahkan partikel
        mPosition.x = mPosition.x + mVelocity.x;
        mPosition.y = mPosition.y + mVelocity.y;
    }

    void setPosition(PointF position) {
        mPosition.x = position.x;
        mPosition.y = position.y;
    }

    PointF getPosition() {
        return mPosition;
    }

}
