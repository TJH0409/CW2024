package com.example.demo;

public class UserPlane extends FighterPlane {
    private int velocityMultiplier;
    private int killCount;

    public UserPlane(int initialHealth) {
        super(UserPlaneConfig.IMAGE_NAME, UserPlaneConfig.IMAGE_HEIGHT, UserPlaneConfig.INITIAL_X_POSITION, UserPlaneConfig.INITIAL_Y_POSITION, initialHealth);
        this.velocityMultiplier = 0;
        this.killCount = 0;
    }

    @Override
    public void updatePosition() {
        if (!isMoving()) return;

        double initialY = getTranslateY();
        moveVertically(UserPlaneConfig.VERTICAL_VELOCITY * velocityMultiplier);

        if (isOutOfBounds()) {
            setTranslateY(initialY); // Reset position if out of bounds
        }
    }

    @Override
    public void updateActor() {
        updatePosition();
    }

    @Override
    public ActiveActorDestructible fireProjectile() {
        return new UserProjectile(UserPlaneConfig.PROJECTILE_X_POSITION, getProjectileY());
    }

    public void moveUp() {
        velocityMultiplier = -1;
    }

    public void moveDown() {
        velocityMultiplier = 1;
    }

    public void stop() {
        velocityMultiplier = 0;
    }

    public int getKillCount() {
        return killCount;
    }

    public void incrementKillCount() {
        killCount++;
    }

    private boolean isMoving() {
        return velocityMultiplier != 0;
    }

    private boolean isOutOfBounds() {
        double newY = getLayoutY() + getTranslateY();
        return newY < UserPlaneConfig.Y_UPPER_BOUND || newY > UserPlaneConfig.Y_LOWER_BOUND;
    }

    private double getProjectileY() {
        return getLayoutY() + getTranslateY() + UserPlaneConfig.PROJECTILE_Y_OFFSET;
    }
}
