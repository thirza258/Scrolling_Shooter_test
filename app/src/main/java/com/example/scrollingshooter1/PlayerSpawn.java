package com.example.scrollingshooter1;

public class PlayerSpawn implements SpawnComponent{
    @Override
    public void spawn(Transform playerTransform, Transform transform) {
        transform.setLocation(transform.getmScreenSize().x / 2,transform.getmScreenSize().y / 2 );
    }
}
