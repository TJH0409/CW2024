package com.example.demo;

import java.util.*;

public class Boss extends FighterPlane {
    private final List<Integer> movePattern = new ArrayList<>();
    private final Shield shield;
    private int moveIndex;
    private int sameMoveFrames;

    public Boss() {
        super(BossConfig.IMAGE_NAME, BossConfig.IMAGE_HEIGHT, BossConfig.INITIAL_X_POSITION, BossConfig.INITIAL_Y_POSITION, BossConfig.HEALTH);
        this.shield = new Shield(BossConfig.MAX_SHIELD_FRAMES, BossConfig.SHIELD_PROBABILITY);
        initializeMovePattern();
        resetMoveState();
    }

    @Override
    public void updatePosition() {
        double initialY = getTranslateY();
        moveVertically(getNextMove());
        if (isOutOfBounds()) {
            setTranslateY(initialY); // Reset to avoid going out of bounds
        }
    }

    @Override
    public void updateActor() {
        updatePosition();
        shield.update();
    }

    @Override
    public ActiveActorDestructible fireProjectile() {
        return shouldFireProjectile() ? new BossProjectile(getProjectileInitialY()) : null;
    }

    @Override
    public void takeDamage() {
        if (!shield.isActive()) {
            super.takeDamage();
        }
    }

    private void initializeMovePattern() {
        for (int i = 0; i < BossConfig.MOVE_FREQUENCY; i++) {
            movePattern.add(BossConfig.VERTICAL_VELOCITY);
            movePattern.add(-BossConfig.VERTICAL_VELOCITY);
            movePattern.add(0); // No movement
        }
        shuffleMovePattern();
    }

    private int getNextMove() {
        int move = movePattern.get(moveIndex);
        sameMoveFrames++;
        if (shouldSwitchMove()) {
            shuffleMovePattern();
            moveIndex = (moveIndex + 1) % movePattern.size();
            resetMoveState();
        }
        return move;
    }

    private void shuffleMovePattern() {
        Collections.shuffle(movePattern);
    }

    private void resetMoveState() {
        sameMoveFrames = 0;
    }

    private boolean shouldSwitchMove() {
        return sameMoveFrames >= BossConfig.MAX_SAME_MOVE_FRAMES;
    }

    private boolean isOutOfBounds() {
        double currentY = getLayoutY() + getTranslateY();
        return currentY < BossConfig.Y_UPPER_BOUND || currentY > BossConfig.Y_LOWER_BOUND;
    }

    private double getProjectileInitialY() {
        return getLayoutY() + getTranslateY() + BossConfig.PROJECTILE_Y_OFFSET;
    }

    private boolean shouldFireProjectile() {
        return Math.random() < BossConfig.FIRE_RATE;
    }
}
