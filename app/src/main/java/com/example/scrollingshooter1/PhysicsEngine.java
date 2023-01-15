package com.example.scrollingshooter1;

import android.graphics.PointF;
import android.graphics.RectF;

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

        return detectCollisions(gameState, objects, soundEngine, particleSystem);
    }

    //collision
    private boolean detectCollisions(GameState mGameState, ArrayList<GameObject> objects,
                                     SoundEngine soundEngine, ParticleSystem particleSystem) {
        boolean playerHit = false;
        for(GameObject go1 : objects) {
            if(go1.checkActive()) {
                //jika game objek aktif maka akan dicek
                for (GameObject go2 : objects) {
                    if(go2.checkActive()) {
                        if(RectF.intersects(go1.getTransform().getCollider(), go2.getTransform().getCollider())) {
                            //ada intersect
                            switch (go1.getTag() + "with" + go2.getTag()) {
                                case "Player with Alien Laser":
                                    playerHit = true;
                                    mGameState.loseLife(soundEngine);
                                    break;
                                case "Player with Alien":
                                    playerHit = true;
                                    mGameState.loseLife(soundEngine);
                                    break;
                                case "Player Laser with Alien":
                                    mGameState.increaseScore();
                                    //respawn the alien
                                    particleSystem.emitParticles(new PointF(go2.getTransform().getLocation().x,
                                            go2.getTransform().getLocation().y));
                                    go2.setInactive();
                                    go2.spawn(objects.get(Level.PLAYER_INDEX).getTransform());

                                    go1.setInactive();
                                    soundEngine.playAlienExplode();
                                    break;
                                default:
                                    break;

                            }
                        }
                    }
                }
            }
        }
        return playerHit;
    }

}
