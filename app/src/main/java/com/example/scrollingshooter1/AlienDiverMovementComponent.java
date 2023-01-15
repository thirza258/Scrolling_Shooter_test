package com.example.scrollingshooter1;

import android.graphics.PointF;

import java.util.Random;

public class AlienDiverMovementComponent implements Movement{

    @Override
    public boolean move(long fps, Transform transform, Transform playerTransform) {
        
        //lokasi ship
        PointF location = transform.getLocation();
        //kecepatan ship
        float speed = transform.getSpeed();

        // perbedaan kecepatan relatif dengan player
        float slowDownRelativeToPlayer = 1.8f;

        // pergerakan relatif dengan player ketika in view
        if(playerTransform.getFacingRight() == false) {
            location.x += speed * slowDownRelativeToPlayer / fps;
        }
        else {
            location.x -= speed * slowDownRelativeToPlayer / fps;
        }

        // jatuh lalu respawn diatas
        location.y += speed / fps;

        if(location.y > transform.getmScreenSize().y ) {
            //respawn diatas
            Random random = new Random();
            location.y = random.nextInt(300) - transform.getObjectHeight();

            location.x = random.nextInt((int) transform.getmScreenSize().x);
        }

        transform.updateCollider();

        return true;
    }
}
