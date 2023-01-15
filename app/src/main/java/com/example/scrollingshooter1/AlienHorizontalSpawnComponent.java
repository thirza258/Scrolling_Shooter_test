package com.example.scrollingshooter1;

import android.graphics.PointF;

import java.util.Random;

public class AlienHorizontalSpawnComponent implements SpawnComponent{
    @Override
    public void spawn(Transform playerTransform, Transform transform) {
        //ukuran layar
        PointF screenSize = transform.getmScreenSize();

        Random random = new Random();
        boolean left = random.nextBoolean();
        //jarak
        float distance = random.nextInt(2000) + transform.getmScreenSize().x;

        // tinggi dari spawn
        float spawnHeight = random.nextFloat() * screenSize.y - transform.getSize().y;

        if(left) {
            transform.setLocation(-distance, spawnHeight);
            transform.headRight();
        }
        else {
            transform.setLocation(distance, spawnHeight);
            transform.headLeft();
        }
    }
}
