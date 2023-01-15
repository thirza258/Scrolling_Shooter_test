package com.example.scrollingshooter1;

import android.graphics.Point;
import android.graphics.PointF;

import java.util.Random;

public class AlienChaseComponent implements Movement {

    private Random mShotRandom = new Random();

    private AlienLaserSpawner alienLaserSpawner;

    AlienChaseComponent(AlienLaserSpawner laserSpawner) {
        alienLaserSpawner = laserSpawner;
    }

    @Override
    public boolean move(long fps, Transform transform, Transform playerTransform) {
        //peluang 1/100 akan ditembak ketika in line dengan player
        final int TAKE_SHOT = 0;
        final int SHOT_CHANCE = 100;

        //lebar layar
        float screenWidth = transform.getmScreenSize().x;

        //lokasi player
        PointF playerLocation = playerTransform.getLocation();

        //ukuran kapal
        float height = transform.getObjectHeight();
        //arah ship
        boolean facingRight = transform.getFacingRight();
        //jarak ship dengan player
        float mChasingDistance = transform.getmScreenSize().x / 3f;
        //jauh AI melihat
        float mSeeingDistance = transform.getmScreenSize().x / 1.5f;
        //lokasi ship
        PointF location = transform.getLocation();
        //kecepatan ship
        float speed = transform.getSpeed();

        //perbedaan kecepatan relatif dengan player
        float verticalSpeedDifference = .3f;
        float slowDownRelativeToPlayer = 1.8f;
        //agar ship tidak terlalu mengelock player
        float verticalSearchBounce = 20f;


        //bergerak dengan arah ke player tapi relatif jaga jarak
        if(Math.abs(location.x - playerLocation.x) > mChasingDistance) {
            if(location.x < playerLocation.x) {
                transform.headRight();
            }
            else if (location.x > playerLocation.x) {
                transform.headLeft();
            }
        }

        //untuk alien melihat player
        if(Math.abs(location.x - playerLocation.x) <= mSeeingDistance) {
            if((int) location.y - playerLocation.y < -verticalSearchBounce) {
                transform.headDown();
            }
            else if((int) location.y - playerLocation.y > verticalSearchBounce) {
                transform.headUp();
            }

            //alien akan bergerak relatif ke player jika dideteksi
            //alien akan pergi jauh
            if(playerTransform.getFacingRight() == false) {
                location.x += speed*slowDownRelativeToPlayer / fps;
            }
            else {
                location.x -= speed * slowDownRelativeToPlayer / fps;
            }
        }
        else {
            //berhenti atau alien akan hilang
            transform.stopVertical();
        }

        //bergerak vertikal lebih pelan daripada horizontal
        if(transform.headingDown()) {
            location.y += speed * verticalSpeedDifference / fps;
        }
        else if(transform.headingUp()) {
            location.y += speed * verticalSpeedDifference / fps;
        }

        //bergerak horizontal
        if(transform.headingLeft()) {
            location.x -= (speed) / fps;
        }

        if(transform.getFacingRight()) {
            location.x += (speed) / fps;
        }

        //mengupdate collider
        transform.updateCollider();

        if(mShotRandom.nextInt(SHOT_CHANCE) == TAKE_SHOT) {
            if(Math.abs(playerLocation.y - location.y) < height) {
                //jika player dekat dengan atau diarah player
                if((facingRight && playerLocation.x > location.x || !facingRight && playerLocation.x < location.x)
                && Math.abs(playerLocation.x - location.x) < screenWidth) {
                    //alien menembak
                    alienLaserSpawner.spawnAlienLaser(transform);
                }
            }
        }

        return true;
    }
}
