package com.example.scrollingshooter1;

import android.graphics.PointF;

import java.util.Random;

public class AlienPatrolMovementComponent implements Movement{

    private AlienLaserSpawner alienLaserSpawner;
    private Random mShotRandom = new Random();

    AlienPatrolMovementComponent(AlienLaserSpawner laserSpawner) {
        alienLaserSpawner = laserSpawner;
    }


    @Override
    public boolean move(long fps, Transform transform, Transform playerTransform) {
        //peluang tembakan akan ditembak
        final int TAKE_SHOT = 0;
        final int SHOT_CHANCE = 100;

        //lokasi player
        PointF playerLocation = playerTransform.getLocation();

        //atas dari layar
        final float MIN_VERTICAL_BOUNDS = 0;
        // lebar dan tinggi layar
        float screenX = transform.getmScreenSize().x;
        float screenY = transform.getmScreenSize().y;

        //jarak yang dapat alien lihat
        float mSeeingDistance = screenX * .5f;

        //lokasi alien
        PointF alienLocation = transform.getLocation();
        //kecepatan alien
        float speed = transform.getSpeed();
        //tinggi alien
        float height = transform.getObjectHeight();

        //menyetop alien yang pergi jauh
        float MAX_VERTICAL_BOUNDS = screenY - height;
        final float MAX_HORIZONTAL_BOUNDS = 2 * screenX;
        final float MIN_HORIZONTAL_BOUNDS = 2 * -screenX;

        // mensetting kecepatan horizontal relatif engan arah player
        float horizontalSpeedAdjustment = 0;
        // ukuran mempercepat dan memperlambat relatif dari arah player
        float horizontalSpeedAdjustmentModifier = .8f;

        // alien melihat player dan merubah kecepatan
        if (Math.abs(alienLocation.x - playerLocation.x) < mSeeingDistance) {
            if (playerTransform.getFacingRight() != transform.getFacingRight()) {
                //jika mengarah berbeda maka alien akan cepat
                horizontalSpeedAdjustment = speed * horizontalSpeedAdjustmentModifier;
            }
            else {
                // jika mengarah sama maka mengurangi kecepatan
                horizontalSpeedAdjustment = -(speed * horizontalSpeedAdjustmentModifier);
            }
        }

        // bergerak horizontal dengan memodifikasi kecepatan
        if(transform.headingLeft()) {
            alienLocation.x -= (speed + horizontalSpeedAdjustment) / fps;

            // membalikkan ship jika melewati patrol area
            if(alienLocation.x < MIN_HORIZONTAL_BOUNDS) {
                alienLocation.x = MIN_HORIZONTAL_BOUNDS;
                transform.headRight();
            }
        }
        else{
            alienLocation.x += (speed + horizontalSpeedAdjustment) / fps;
            if(alienLocation.x > MAX_HORIZONTAL_BOUNDS) {
                alienLocation.x = MAX_HORIZONTAL_BOUNDS;
                transform.headLeft();
            }
        }

        //kecepatan vertikal tetap sama dan tidak dipengaruhi
        if(transform.headingDown()) {
            alienLocation.y += (speed) / fps;
            if(alienLocation.y > MAX_VERTICAL_BOUNDS) {
                transform.headUp();
            }
        }
        else {
            alienLocation.y -= (speed) / fps;
            if(alienLocation.y < MIN_VERTICAL_BOUNDS) {
                transform.headDown();
            }
        }

        transform.updateCollider();

        //shhot jika alien within a ship abobe
        if(mShotRandom.nextInt(SHOT_CHANCE) == TAKE_SHOT) {
            if(Math.abs(playerLocation.y - alienLocation.y) < height) {
                //jika alien mengarah ke arah yang benar dan dekat dengan playher
                if((transform.getFacingRight() && playerLocation.x > alienLocation.x || !transform.getFacingRight()
                && playerLocation.x < alienLocation.x) && Math.abs(playerLocation.x - alienLocation.x) <  screenX) {
                    //tembak
                    alienLaserSpawner.spawnAlienLaser(transform);
                }
            }
        }

        return true;
    }
}
