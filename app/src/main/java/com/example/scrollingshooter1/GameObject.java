package com.example.scrollingshooter1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

public class GameObject {
    private Transform mTransform;
    private boolean isActive = false;
    private String mTag;

    private GraphicsComponent graphicsComponent;
    private Movement movement;
    private SpawnComponent spawnComponent;


    void setSpawner(SpawnComponent spawn) {
        spawnComponent = spawn;
    }

    void setGraphics(GraphicsComponent graphics, Context context, ObjectSpec objectSpec, PointF objectSize) {
        graphicsComponent = graphics;
        graphics.initialize(context, objectSpec, objectSize);
    }

    void setMovement(Movement movementComponent) {
        movement = movementComponent;
    }

    void setInput(InputComponent input) {
        input.setTransform(mTransform);
    }

    void setmTag(String tag) {
        mTag = tag;
    }

    void setTransform(Transform transform) {
        mTransform = transform;
    }

    void draw(Canvas canvas, Paint paint){
        graphicsComponent.draw(canvas, paint, mTransform);
    }

    void update(long fps, Transform playerTransform) {
        if(!(movement.move(fps, mTransform, playerTransform))) {
            //komponen mengembalikan false
            isActive = false;
        }
    }

    boolean spawn(Transform playerTransform) {
        if(isActive == false) {
            spawnComponent.spawn(playerTransform, mTransform);
            isActive = true;
            return true;
        }

        return false;
    }

    boolean checkActive() {
        return isActive;
    }

    String getTag() {
        return mTag;
    }

    void setInactive() {
        isActive = false;
    }

    Transform getTransform() {
        return mTransform;
    }
}
