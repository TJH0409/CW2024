package com.example.demo.levels;

import com.example.demo.actor.Boss;

/**
 * Represents the second level of the game featuring a boss battle.
 * This level introduces a unique gameplay mechanic where the player must defeat a single boss enemy.
 */
public class LevelTwo extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;
	private LevelViewLevelTwo levelView;
	
    /**
     * Constructs Level Two with specified screen dimensions.
     * Initializes the level with a background, screen size, player health, and creates the boss.
     * 
     * @param screenHeight Total height of the game screen
     * @param screenWidth Total width of the game screen
     */
	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss();
	}
	
    /**
     * Initializes friendly units by adding the user's plane to the game root.
     * This method is called during level setup to position the player's plane.
     */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

    /**
     * Checks the current game state to determine if the game is over.
     * The game ends if the player is destroyed or if the boss is defeated.
     * Triggers appropriate game end scenarios (lose or win).
     */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (boss.isDestroyed()) {
			winGame();
		}
	}

    /**
     * Spawns enemy units for Level Two.
     * In this level, only spawns the boss when no other enemies are present.
     * Ensures that only one boss is active at a time.
     */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
		}
	}

    /**
     * Creates and returns the LevelView specific to Level Two.
     * Initializes a custom LevelViewLevelTwo with the game root and player's initial health.
     * 
     * @return A LevelViewLevelTwo instance configured for this level
     */
	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelView;
	}

}
