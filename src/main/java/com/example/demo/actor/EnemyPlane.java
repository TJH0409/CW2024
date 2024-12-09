package com.example.demo.actor;

import com.example.demo.config.EnemyPlaneConfig;
import com.example.demo.projectile.EnemyProjectile;

/**
* Represents an enemy plane in the game with specific movement and firing behaviors.
* Extends FighterPlane with enemy-specific implementations.
*/
public class EnemyPlane extends FighterPlane {
	
	/** Configuration reference for enemy plane properties. */
    private final EnemyPlaneConfig config;

    /**
     * Constructs an enemy plane at specified initial position.
     * 
     * @param initialXPos Initial horizontal spawn position
     * @param initialYPos Initial vertical spawn position
     */
    public EnemyPlane(double initialXPos, double initialYPos) {
        super(EnemyPlaneConfig.IMAGE_NAME, 
              EnemyPlaneConfig.IMAGE_HEIGHT, 
              initialXPos, 
              initialYPos, 
              EnemyPlaneConfig.HEALTH);
        this.config = new EnemyPlaneConfig();
    }

    /**
     * Updates the enemy plane's position by moving horizontally.
     * Uses predefined horizontal velocity from configuration.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(EnemyPlaneConfig.HORIZONTAL_VELOCITY);
    }

    /**
     * Attempts to fire a projectile based on configured fire rate.
     * 
     * @return A new EnemyProjectile if conditions are met, otherwise null
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        if (Math.random() < EnemyPlaneConfig.FIRE_RATE) {
            double projectileXPosition = getProjectileXPosition(EnemyPlaneConfig.PROJECTILE_X_OFFSET);
            double projectileYPosition = getProjectileYPosition(EnemyPlaneConfig.PROJECTILE_Y_OFFSET);
            return new EnemyProjectile(projectileXPosition, projectileYPosition);
        }
        return null;
    }

    /**
     * Updates the enemy plane's state by updating its position.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }
}