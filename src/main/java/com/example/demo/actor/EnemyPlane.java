package com.example.demo.actor;

import com.example.demo.config.EnemyPlaneConfig;
import com.example.demo.projectile.EnemyProjectile;

public class EnemyPlane extends FighterPlane {
    private final EnemyPlaneConfig config;

    public EnemyPlane(double initialXPos, double initialYPos) {
        super(EnemyPlaneConfig.IMAGE_NAME, 
              EnemyPlaneConfig.IMAGE_HEIGHT, 
              initialXPos, 
              initialYPos, 
              EnemyPlaneConfig.HEALTH);
        this.config = new EnemyPlaneConfig();
    }

    @Override
    public void updatePosition() {
        moveHorizontally(EnemyPlaneConfig.HORIZONTAL_VELOCITY);
    }

    @Override
    public ActiveActorDestructible fireProjectile() {
        if (Math.random() < EnemyPlaneConfig.FIRE_RATE) {
            double projectileXPosition = getProjectileXPosition(EnemyPlaneConfig.PROJECTILE_X_OFFSET);
            double projectileYPosition = getProjectileYPosition(EnemyPlaneConfig.PROJECTILE_Y_OFFSET);
            return new EnemyProjectile(projectileXPosition, projectileYPosition);
        }
        return null;
    }

    @Override
    public void updateActor() {
        updatePosition();
    }
}