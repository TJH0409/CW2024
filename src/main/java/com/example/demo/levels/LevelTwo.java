package com.example.demo.levels;

import com.example.demo.actor.ActiveActorDestructible;
import com.example.demo.actor.EnemyPlane2;

/**
 * Represents the first level of the game with specific enemy spawning and level progression mechanics.
 * Extends LevelParent to implement level-specific behaviors for Level One.
 */
public class LevelTwo extends LevelParent {
	
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
	private static final String NEXT_LEVEL = "com.example.demo.levels.LevelBoss";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 15;
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
	private static final int PLAYER_INITIAL_HEALTH = 5;

    /**
     * Constructs Level One with specified screen dimensions.
     * Initializes the level with a background, screen size, and player health.
     * 
     * @param screenHeight Total height of the game screen
     * @param screenWidth Total width of the game screen
     */
	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

    /**
     * Checks the current game state to determine if the game is over.
     * Game ends if the user is destroyed or has reached the kill target.
     */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (userHasReachedKillTarget())
			goToNextLevel(NEXT_LEVEL);
	}

    /**
     * Initializes friendly units by adding the user's plane to the game root.
     */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

    /**
     * Spawns enemy units for Level One.
     * Attempts to spawn enemies up to the total enemy limit based on spawn probability.
     * Enemies are positioned randomly within the vertical game area.
     */
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane2(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);
			}
		}
	}

    /**
     * Creates and returns the LevelView for Level One.
     * 
     * @return A LevelView instance configured for this level
     */
	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

    /**
     * Checks if the user has reached the kill target to advance to the next level.
     * 
     * @return True if the user has killed enough enemies, false otherwise
     */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

}