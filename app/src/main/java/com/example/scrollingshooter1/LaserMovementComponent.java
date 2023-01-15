package com.example.scrollingshooter1;

import android.graphics.PointF;

public class LaserMovementComponent implements Movement{

    @Override
    public boolean move(long fps, Transform transform, Transform playerTransform) {
        // laser hanya bisa ada di 2 kali lebar layar
        float range = transform.getmScreenSize().x * 2;

        //lokasi leser ditembak
        PointF location = transform.getLocation();

        //kecepatan
        float speed = transform.getSpeed();

        if(transform.headingRight()) {
            location.x = location.x + speed / fps;
        }
        else if(transform.headingLeft()) {
            location.x = location.x - speed / fps;
        }

        if (location.x < - range || location.x > range) {
            //laser akan turn off
            return false;
        }
        return true;
    }
}
