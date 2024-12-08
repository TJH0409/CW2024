package com.example.demo.actor;

import com.example.demo.config.UserPlaneConfig;
import com.example.demo.projectile.UserProjectile;

public class UserPlane extends FighterPlane {
    private int verticalVelocityMultiplier; //for up down
    private int horizontalVelocityMultiplier; //for left right
    private int numberOfKills;

    public UserPlane(int initialHealth) {
        super(UserPlaneConfig.IMAGE_NAME, UserPlaneConfig.IMAGE_HEIGHT, UserPlaneConfig.INITIAL_X_POSITION, UserPlaneConfig.INITIAL_Y_POSITION, initialHealth);
        this.verticalVelocityMultiplier = 0;
        this.horizontalVelocityMultiplier = 0;
        this.numberOfKills = 0;
    }

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

    @Override
    public void updateActor() {
        updatePosition();
    }

    @Override
    public ActiveActorDestructible fireProjectile() {
    	double projectileX = getProjectileX();
    	double projectileY = getProjectileY();
        return new UserProjectile(projectileX, projectileY);
    }

    public void moveUp() {
        verticalVelocityMultiplier = -1;
    }

    public void moveDown() {
        verticalVelocityMultiplier = 1;
    }
    
    public void moveLeft() {
        horizontalVelocityMultiplier = -1;
    }

    public void moveRight() {
        horizontalVelocityMultiplier = 1;
    }

    public void stopVertical() {
        verticalVelocityMultiplier = 0;
    }
    
    public void stopHorizontal() {
        horizontalVelocityMultiplier = 0;
    }

    public int getNumberOfKills() {
        return numberOfKills;
    }

    public void incrementKillCount() {
    	numberOfKills++;
    }

    private boolean isMoving() {
        return verticalVelocityMultiplier != 0 || horizontalVelocityMultiplier != 0;
    }

    private boolean isOutOfVerticalBounds() {
        double newY = getLayoutY() + getTranslateY();
        return newY < UserPlaneConfig.Y_UPPER_BOUND || newY > UserPlaneConfig.Y_LOWER_BOUND;
    }
    
    private boolean isOutOfHorizontalBounds() {
        double newX = getLayoutX() + getTranslateX();
        return newX < UserPlaneConfig.X_LEFT_BOUND || newX > UserPlaneConfig.X_RIGHT_BOUND;
    }

    private double getProjectileY() {
        return getLayoutY() + getTranslateY() + UserPlaneConfig.PROJECTILE_Y_OFFSET;
    }
    
    private double getProjectileX() {
        return getLayoutX() + getTranslateX() + UserPlaneConfig.PROJECTILE_X_POSITION;
    }
} 
