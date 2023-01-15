package com.example.scrollingshooter1;

import android.graphics.Point;
import android.graphics.PointF;

public class LaserSpawnComponent implements SpawnComponent{
    @Override
    public void spawn(Transform playerTransform, Transform transform) {
        PointF startPosition = playerTransform.getFiringLocation(transform.getSize().x);

        transform.setLocation((int)startPosition.x, (int)startPosition.y);

        if(playerTransform.getFacingRight()) {
            transform.headRight();
        }
        else {
            transform.headLeft();
        }
    }
}
