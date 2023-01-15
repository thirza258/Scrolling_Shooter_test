package com.example.scrollingshooter1;

import java.util.ArrayList;

public class PhysicsEngine {
    boolean update(long fps, ArrayList<GameObject> objects, GameState gameState,
                   SoundEngine soundEngine,  ParticleSystem particleSystem) {
        //mengupdate game objek
        for (GameObject object : objects) {
            if(object.checkActive()) {
                object.update(fps, objects.get(Level.PLAYER_INDEX).getTransform());
            }
        }

        if(particleSystem.mIsRunning) {
            particleSystem.update(fps);
        }

        return false;
    }

    //collision
}
