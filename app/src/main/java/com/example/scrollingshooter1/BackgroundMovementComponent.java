package com.example.scrollingshooter1;

public class BackgroundMovementComponent implements Movement{
    @Override
    public boolean move(long fps, Transform transform, Transform playerTransform) {
        int currentXClip = transform.getXClip();

        if(playerTransform.getFacingRight()) {
            currentXClip -= transform.getSpeed() / fps;
            transform.setXClip(currentXClip);
        }
        else {
            currentXClip += transform.getSpeed()/fps;
            transform.setXClip(currentXClip);
        }

        if(currentXClip >= transform.getSize().x) {
            transform.setXClip(0);
            transform.flipReversedFirst();
        }
        else if(currentXClip <= 0) {
            transform.setXClip((int)transform.getSize().x);
            transform.flipReversedFirst();
        }

        return true;
    }
}
