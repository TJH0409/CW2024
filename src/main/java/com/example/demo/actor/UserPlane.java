package com.example.demo.actor;

import com.example.demo.config.UserPlaneConfig;
import com.example.demo.projectile.UserProjectile;

/**
 * Represents the player-controlled fighter plane in the game.
 * Extends the FighterPlane class with specific user-controlled movement and interaction capabilities.
 * Manages the player's plane movement, projectile firing, and kill tracking.
 */
public class UserPlane extends FighterPlane {
	
	/** Multiplier for vertical velocity, controlling up and down movement. */
    private int verticalVelocityMultiplier; 
    
    /** Multiplier for horizontal velocity, controlling left and right movement. */
    private int horizontalVelocityMultiplier; 
    
    /** Tracks the number of enemy kills by the player. */
    private int numberOfKills;

    /**
     * Constructs a new UserPlane with initial configuration.
     * 
     * @param initialHealth The starting health points for the plane
     */
    public UserPlane(int initialHealth) {
        super(UserPlaneConfig.IMAGE_NAME, UserPlaneConfig.IMAGE_HEIGHT, UserPlaneConfig.INITIAL_X_POSITION, UserPlaneConfig.INITIAL_Y_POSITION, initialHealth);
        this.verticalVelocityMultiplier = 0;
        this.horizontalVelocityMultiplier = 0;
        this.numberOfKills = 0;
    }

    /**
     * Updates the plane's position based on current velocity multipliers.
     * Prevents the plane from moving outside the game boundaries.
     */
    @Override
    public void updatePosition() {
        if (!isMoving()) return;

        double initialY = getTranslateY();
        double initialX = getTranslateX();
        
        moveVertically(UserPlaneConfig.VERTICAL_VELOCITY * verticalVelocityMultiplier);
        moveHorizontally(UserPlaneConfig.HORIZONTAL_VELOCITY * horizontalVelocityMultiplier);
       
        if (isOutOfVerticalBounds()) {
            setTranslateY(initialY); 
        }
        
        if (isOutOfHorizontalBounds()) {
            setTranslateX(initialX); 
        }
    }

    /**
     * Updates the actor's state by calling position update method.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }

    /**
     * Creates and returns a new projectile fired from the plane's current position.
     * 
     * @return A new UserProjectile launched from the plane
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
    	double projectileX = getProjectileX();
    	double projectileY = getProjectileY();
        return new UserProjectile(projectileX, projectileY);
    }

    /**
     * Sets the vertical velocity multiplier to move the plane upward.
     */
    public void moveUp() {
        verticalVelocityMultiplier = -1;
    }

    /**
     * Sets the vertical velocity multiplier to move the plane downward.
     */
    public void moveDown() {
        verticalVelocityMultiplier = 1;
    }
    
    /**
     * Sets the horizontal velocity multiplier to move the plane left.
     */
    public void moveLeft() {
        horizontalVelocityMultiplier = -1;
    }

    /**
     * Sets the horizontal velocity multiplier to move the plane right.
     */
    public void moveRight() {
        horizontalVelocityMultiplier = 1;
    }

    /**
     * Stops vertical movement by resetting the vertical velocity multiplier.
     */
    public void stopVertical() {
        verticalVelocityMultiplier = 0;
    }
    
    /**
     * Stops horizontal movement by resetting the horizontal velocity multiplier.
     */
    public void stopHorizontal() {
        horizontalVelocityMultiplier = 0;
    }

    /**
     * Retrieves the current number of enemy kills.
     * 
     * @return The total number of kills
     */
    public int getNumberOfKills() {
        return numberOfKills;
    }

    /**
     * Increments the kill count by one when an enemy is destroyed.
     */
    public void incrementKillCount() {
    	numberOfKills++;
    }

    /**
     * Checks if the plane is currently moving in any direction.
     * 
     * @return true if the plane is moving, false otherwise
     */
    private boolean isMoving() {
        return verticalVelocityMultiplier != 0 || horizontalVelocityMultiplier != 0;
    }

    /**
     * Checks if the plane has moved outside the vertical game boundaries.
     * 
     * @return true if the plane is out of vertical bounds, false otherwise
     */
    private boolean isOutOfVerticalBounds() {
        double newY = getLayoutY() + getTranslateY();
        return newY < UserPlaneConfig.Y_UPPER_BOUND || newY > UserPlaneConfig.Y_LOWER_BOUND;
    }
    
    /**
     * Checks if the plane has moved outside the horizontal game boundaries.
     * 
     * @return true if the plane is out of horizontal bounds, false otherwise
     */
    private boolean isOutOfHorizontalBounds() {
        double newX = getLayoutX() + getTranslateX();
        return newX < UserPlaneConfig.X_LEFT_BOUND || newX > UserPlaneConfig.X_RIGHT_BOUND;
    }

    /**
     * Calculates the Y-coordinate for spawning a projectile.
     * 
     * @return The Y-coordinate for the projectile's initial position
     */
    private double getProjectileY() {
        return getLayoutY() + getTranslateY() + UserPlaneConfig.PROJECTILE_Y_OFFSET;
    }
    
    /**
     * Calculates the X-coordinate for spawning a projectile.
     * 
     * @return The X-coordinate for the projectile's initial position
     */
    private double getProjectileX() {
        return getLayoutX() + getTranslateX() + UserPlaneConfig.PROJECTILE_X_POSITION;
    }
} 
