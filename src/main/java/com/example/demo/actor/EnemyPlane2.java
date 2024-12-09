package com.example.demo.actor;

import com.example.demo.config.EnemyPlane2Config;
import com.example.demo.projectile.EnemyProjectile;

/**
* Represents another enemy plane in the game with specific movement and firing behaviors.
* Extends FighterPlane with enemy-specific implementations.
*/
public class EnemyPlane2 extends FighterPlane {
	
	/** Configuration reference for enemy plane properties. */
    private final EnemyPlane2Config config;

    /**
     * Constructs an enemy plane at specified initial position.
     * 
     * @param initialXPos Initial horizontal spawn position
     * @param initialYPos Initial vertical spawn position
     */
    public EnemyPlane2(double initialXPos, double initialYPos) {
        super(EnemyPlane2Config.IMAGE_NAME, 
              EnemyPlane2Config.IMAGE_HEIGHT, 
              initialXPos, 
              initialYPos, 
              EnemyPlane2Config.HEALTH);
        this.config = new EnemyPlane2Config();
    }

    /**
     * Updates the enemy plane 2's position by moving horizontally.
     * Uses predefined horizontal velocity from configuration.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(EnemyPlane2Config.HORIZONTAL_VELOCITY);
    }

    /**
     * Attempts to fire a projectile based on configured fire rate.
     * 
     * @return A new EnemyProjectile if conditions are met, otherwise null
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        if (Math.random() < EnemyPlane2Config.FIRE_RATE) {
            double projectileXPosition = getProjectileXPosition(EnemyPlane2Config.PROJECTILE_X_OFFSET);
            double projectileYPosition = getProjectileYPosition(EnemyPlane2Config.PROJECTILE_Y_OFFSET);
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