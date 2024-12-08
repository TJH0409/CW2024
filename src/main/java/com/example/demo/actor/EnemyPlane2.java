package com.example.demo.actor;

import com.example.demo.config.EnemyPlane2Config;
import com.example.demo.projectile.EnemyProjectile;

public class EnemyPlane2 extends FighterPlane {
    private final EnemyPlane2Config config;

    public EnemyPlane2(double initialXPos, double initialYPos) {
        super(EnemyPlane2Config.IMAGE_NAME, 
              EnemyPlane2Config.IMAGE_HEIGHT, 
              initialXPos, 
              initialYPos, 
              EnemyPlane2Config.HEALTH);
        this.config = new EnemyPlane2Config();
    }

    @Override
    public void updatePosition() {
        moveHorizontally(EnemyPlane2Config.HORIZONTAL_VELOCITY);
    }

    @Override
    public ActiveActorDestructible fireProjectile() {
        if (Math.random() < EnemyPlane2Config.FIRE_RATE) {
            double projectileXPosition = getProjectileXPosition(EnemyPlane2Config.PROJECTILE_X_OFFSET);
            double projectileYPosition = getProjectileYPosition(EnemyPlane2Config.PROJECTILE_Y_OFFSET);
            return new EnemyProjectile(projectileXPosition, projectileYPosition);
        }
        return null;
    }

    @Override
    public void updateActor() {
        updatePosition();
    }
}