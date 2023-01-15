package com.example.scrollingshooter1;

import java.util.Random;

public class AlienVerticalSpawnComponent implements SpawnComponent{

    @Override
    public void spawn(Transform playerTransform, Transform transform) {
        //spawn di jauh layar secara random tapi sesuai dengan lebar layar
        Random random = new Random();
        float xPosition = random.nextInt((int)transform.getmScreenSize().x);
        //mengeset tinggi secara vertikal diatas game yang terlihat
        float spawnHeight = random.nextInt(300) - transform.getObjectHeight();

        // mengespawn ship
        transform.setLocation(xPosition, spawnHeight);
        //selalu ke bawah
        transform.headDown();
    }
}
