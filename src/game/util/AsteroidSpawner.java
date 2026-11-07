package game.util;

import game.model.Asteroid;
import java.util.Random;

public class AsteroidSpawner {

    private Random random = new Random();
    private long lastSpawnTime;
    private int minSpawnInterval;
    private int spawnInterval;


    public AsteroidSpawner(int minSpawnInterval){
        this.lastSpawnTime =System.currentTimeMillis();
        this.minSpawnInterval = minSpawnInterval;
        this.spawnInterval = minSpawnInterval;
    }

    public void setLastSpawnTime(int lastSpawnTime){this.lastSpawnTime = lastSpawnTime;}

    private Asteroid spawn() {
        double radius = 15 + random.nextInt(20);
        double x = random.nextInt((int)(Constants.SCREEN_WIDTH + radius) );
        double y = -30;
        double speed = 1.5 + random.nextDouble() * 0.5;
        return new Asteroid(x, y, 0, speed, radius);
    }

    public Asteroid trySpawn() {
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastSpawnTime >= spawnInterval) {
            lastSpawnTime = currentTime;
            spawnInterval = random.nextInt((int)(0.5 * minSpawnInterval) + 1) + minSpawnInterval; // between minSpawnInterval and 2 * minSpawnInterval
            return spawn();
        }
        return null;
    }
}