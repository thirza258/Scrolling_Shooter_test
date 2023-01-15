package com.example.scrollingshooter1;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GameEngine extends SurfaceView implements Runnable, GameStarter, GameEngineBroadcaster, PlayerLaserSpawner {
    private Thread mThread = null;
    private long mFPS;

    private ArrayList<InputObserver> inputObservers = new ArrayList<>();
    UIController mUIController;

    private GameState mGameState;
    private SoundEngine mSoundEngine;
    HUD mHUD;
    Renderer mRenderer;
    ParticleSystem mParticleSystem;
    PhysicsEngine mPhysicEngine;
    Level mLevel;


    public GameEngine(Context context, Point size) {
        super(context);

        mUIController = new UIController(this);
        mGameState = new GameState(this, context);
        mSoundEngine = new SoundEngine(context);
        mHUD = new HUD(size);
        mRenderer = new Renderer(this);
        mParticleSystem = new ParticleSystem();
        mParticleSystem.init(1000);
        mPhysicEngine = new PhysicsEngine();
        mLevel = new Level(context, new PointF(size.x, size.y), this);
    }

    @Override
    public void run() {
        //memperbarui game objek dengan cara yang baru
        while (mGameState.getThreadRunning()) {
            long frameStartTime = System.currentTimeMillis();
            ArrayList<GameObject> objects = mLevel.getGameObject();

            if (mGameState.getPaused() == false) {
                //mmeperbarui objek
                if(mPhysicEngine.update(mFPS,objects, mGameState, mSoundEngine, mParticleSystem)) {
                    //player kena
                    deSpawnRespawn();
                }
            }

            //menggambar game objek
            mRenderer.draw(objects, mGameState, mHUD, mParticleSystem);

            //memperhitungkan waktu
            long timeThisFrame = System.currentTimeMillis() - frameStartTime;
            if (timeThisFrame >= 1) {
                final int MILLIS_IN_SECOND = 1000;
                mFPS = MILLIS_IN_SECOND / timeThisFrame;
            }

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //melakukan kode disini
        for (InputObserver o : inputObservers) {
            o.handleInput(event, mGameState, mHUD.getControls());
        }

        mParticleSystem.emitParticles(new PointF(500,500));

        mSoundEngine.playShoot();

        return true;

    }

    public void stopThread() {
        mGameState.stopEverything();

        try {
            mThread.join();
        }
        catch (InterruptedException e) {
            Log.e("Exception", "stop Thread" + e.getMessage());
        }
    }

    public void startThread() {
        mGameState.startThread();

        mThread = new Thread(this);
        mThread.start();
    }

    @Override
    public void deSpawnRespawn() {
        //akan merespawn dan despawn objek

        ArrayList<GameObject> objects = mLevel.getGameObject();

        for (GameObject gameObject : objects) {
            gameObject.setInactive();
        }
        objects.get(Level.PLAYER_INDEX).spawn(objects.get(Level.PLAYER_INDEX).getTransform());
        objects.get(Level.BACKGROUND_INDEX).spawn(objects.get(Level.PLAYER_INDEX).getTransform());
    }

    @Override
    public void addObserver(InputObserver o) {
        inputObservers.add(o);
    }

    @Override
    public boolean spawnPlayerLaser(Transform transform) {
        ArrayList<GameObject> objects = mLevel.getGameObject();

        if(objects.get(Level.mNextPlayerLaser).spawn(transform)) {
            Level.mNextPlayerLaser++;
            mSoundEngine.playShoot();
            if(Level.mNextPlayerLaser == Level.LAST_PLAYER_LASER + 1) {
                Level.mNextPlayerLaser = Level.FIRST_PLAYER_LASER;
            }
        }

        return true;
    }
}
