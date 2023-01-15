package com.example.scrollingshooter1;

public interface Movement {
    boolean move(long fps, Transform transform, Transform playerTransform);
}
