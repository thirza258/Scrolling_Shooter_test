package com.example.scrollingshooter1;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.Random;

public class ParticleSystem {
    float mDuration;

    ArrayList<Particle> mParticles;
    Random random = new Random();
    boolean mIsRunning = false;

    void init(int numParticles) {
        mParticles = new ArrayList<>();

        //membuat partikel
        for (int i = 0; i < numParticles; i++) {
            float angle = (random.nextInt(360));
            angle = angle * 3.14f/ 180.f;
            float speed = (random.nextInt(20) + 1);

            PointF direction;

            direction  = new PointF((float)Math.cos(angle) * speed, (float) Math.sin(angle) * speed);

            mParticles.add(new Particle(direction));
        }
    }

    void update(long fps) {
        mDuration = mDuration - (1f/fps);

        for (Particle particle : mParticles) {
            particle.update();
        }

        if(mDuration < 0) {
            mIsRunning = false;
        }
    }

    void emitParticles(PointF startPosition) {
        mIsRunning = true;
        mDuration = 1f;

        for(Particle particle : mParticles) {
            particle.setPosition(startPosition);
        }
    }

    void draw(Canvas canvas, Paint paint) {
        for(Particle particle : mParticles) {
            paint.setARGB(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));

            canvas.drawRect(particle.getPosition().x, particle.getPosition().y,
                    particle.getPosition().x + 25, particle.getPosition().y + 25, paint);
        }
    }
}
