package com.example.scrollingshooter1;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import java.io.IOException;

public class SoundEngine {
    private SoundPool mSP;
    private int mShoot_ID = -1;
    private int mAlienExplode_ID = -1;
    private int mPlayer_Explode_ID = -1;

    SoundEngine (Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();

            mSP = new SoundPool.Builder().setMaxStreams(5).setAudioAttributes(audioAttributes).build();
        }
        else {
            mSP = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }

        try {
            AssetManager assetManager = context.getAssets();
            AssetFileDescriptor descriptor;

            //menaruh sound di memory
            descriptor = assetManager.openFd("Chapter 18_assets_shoot.ogg");
            mShoot_ID = mSP.load(descriptor, 0);

            descriptor = assetManager.openFd("Chapter 18_assets_alien_explosion.ogg");
            mAlienExplode_ID = mSP.load(descriptor, 0);

            descriptor = assetManager.openFd("Chapter 18_assets_player_explosion.ogg");
            mPlayer_Explode_ID = mSP.load(descriptor, 0);
        }
        catch (IOException e) {
            //Error
        }
    }

    void playShoot() {
        mSP.play(mShoot_ID, 1,1,0,0,1);
    }

    void playAlienExplode() {
        mSP.play(mAlienExplode_ID, 1,1,0,0,1);
    }

    void playPlayerExplode() {
        mSP.play(mPlayer_Explode_ID, 1,1,0,0,1);
    }
}
