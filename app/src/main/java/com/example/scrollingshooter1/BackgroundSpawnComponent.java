package com.example.scrollingshooter1;

public class BackgroundSpawnComponent implements SpawnComponent{
    @Override
    public void spawn(Transform playerTransform, Transform transform) {
        //menambahkan background di ujung kiri atas
        transform.setLocation(0f,0f);
    }
}
