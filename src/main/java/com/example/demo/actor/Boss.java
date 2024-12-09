package com.example.demo.actor;

import java.util.*;

import com.example.demo.config.BossConfig;
import com.example.demo.projectile.BossProjectile;

/**
 * Represents the boss enemy in the game with advanced movement and defensive capabilities.
 * The boss has a dynamic movement pattern and a protective shield mechanism.
 * Manages complex vertical movement, projectile firing, and damage resistance.
 */
public class Boss extends FighterPlane {
	
	/** List storing the predefined vertical movement pattern for the boss. */
    private final List<Integer> movePattern = new ArrayList<>();
    
    /** Shield mechanism to provide temporary damage protection. */
    private final Shield shield;
    
    /** Current index in the move pattern array. */
    private int moveIndex;
    
    /** Tracks the number of frames the boss has been in the current move. */
    private int sameMoveFrames;

    /**
     * Constructs a new Boss with default configuration.
     * Initializes the boss's position, health, shield, and movement pattern.
     */
    public Boss() {
        super(BossConfig.IMAGE_NAME, BossConfig.IMAGE_HEIGHT, BossConfig.INITIAL_X_POSITION, BossConfig.INITIAL_Y_POSITION, BossConfig.HEALTH);
        this.shield = new Shield(BossConfig.MAX_SHIELD_FRAMES, BossConfig.SHIELD_PROBABILITY);
        initializeMovePattern();
        resetMoveState();
    }

    /**
     * Updates the boss's vertical position based on its predefined movement pattern.
     * Prevents the boss from moving outside the game boundaries.
     */
    @Override
    public void updatePosition() {
        double initialY = getTranslateY();
        moveVertically(getNextMove());
        if (isOutOfBounds()) {
            setTranslateY(initialY); 
        }
    }

    /**
     * Updates the boss's state by updating position and shield.
     */
    @Override
    public void updateActor() {
        updatePosition();
        shield.update();
    }

    /**
     * Attempts to fire a projectile based on the boss's fire rate.
     * 
     * @return A new BossProjectile if conditions are met, otherwise null
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        return shouldFireProjectile() ? new BossProjectile(getProjectileInitialY()) : null;
    }

    /**
     * Overrides damage mechanism to provide shield protection.
     * Boss only takes damage when shield is not active.
     */
    @Override
    public void takeDamage() {
        if (!shield.isActive()) {
            super.takeDamage();
        }
    }

    /**
     * Initializes the boss's movement pattern with vertical velocities.
     * Creates a repeating pattern of up, down, and stationary movements.
     */
    private void initializeMovePattern() {
        for (int i = 0; i < BossConfig.MOVE_FREQUENCY; i++) {
            movePattern.add(BossConfig.VERTICAL_VELOCITY);
            movePattern.add(-BossConfig.VERTICAL_VELOCITY);
            movePattern.add(0); 
        }
        shuffleMovePattern();
    }

    /**
     * Determines the next move in the boss's movement pattern.
     * Manages move duration and pattern cycling.
     * 
     * @return The vertical velocity for the next move
     */
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

    /**
     * Randomizes the order of moves in the movement pattern.
     */
    private void shuffleMovePattern() {
        Collections.shuffle(movePattern);
    }

    /**
     * Resets the frames counter for the current move.
     */
    private void resetMoveState() {
        sameMoveFrames = 0;
    }

    /**
     * Determines if the current move should be switched based on frame duration.
     * 
     * @return true if move should change, false otherwise
     */
    private boolean shouldSwitchMove() {
        return sameMoveFrames >= BossConfig.MAX_SAME_MOVE_FRAMES;
    }

    /**
     * Checks if the boss has moved outside the vertical game boundaries.
     * 
     * @return true if out of bounds, false otherwise
     */
    private boolean isOutOfBounds() {
        double currentY = getLayoutY() + getTranslateY();
        return currentY < BossConfig.Y_UPPER_BOUND || currentY > BossConfig.Y_LOWER_BOUND;
    }

    /**
     * Calculates the initial Y-coordinate for spawning a projectile.
     * 
     * @return The Y-coordinate for the projectile's starting position
     */
    private double getProjectileInitialY() {
        return getLayoutY() + getTranslateY() + BossConfig.PROJECTILE_Y_OFFSET;
    }

    /**
     * Determines probabilistically whether the boss should fire a projectile.
     * 
     * @return true if a projectile should be fired, false otherwise
     */
    private boolean shouldFireProjectile() {
        return Math.random() < BossConfig.FIRE_RATE;
    }
}
