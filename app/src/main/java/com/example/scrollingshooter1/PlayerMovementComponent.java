package com.example.scrollingshooter1;

import android.graphics.PointF;

public class PlayerMovementComponent implements Movement{
    @Override
    public boolean move(long fps, Transform transform, Transform playerTransform) {
        //ukuran layar
        float screenHeight = transform.getmScreenSize().y;
        //lokasi player
        PointF location = transform.getLocation();
        //kecepatan player
        float speed = transform.getSpeed();
        //tinggi player
        float height = transform.getObjectHeight();

        //memindahkan ship keatar dan kebawah
        if(transform.headingDown()) {
            location.y = location.y + (speed/fps);
        }
        else if(transform.headingUp()) {
            location.y = location.y - (speed/fps);
        }

        //agar ship tidak keluar layar
        if(location.y > screenHeight - height) {
            location.y = screenHeight - height;
        }
        else if(location.y < 0) {
            location.y = 0;
        }

        //update collider
        transform.updateCollider();

        return true;
    }
}
