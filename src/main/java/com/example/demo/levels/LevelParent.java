package com.example.demo.levels;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

import com.example.demo.actor.ActiveActorDestructible;
import com.example.demo.actor.FighterPlane;
import com.example.demo.actor.UserPlane;
import com.example.demo.input.InputHandler;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.util.Duration;

/**
 * An abstract base class for game levels in a fighter plane game.
 * Manages game state, actors, timeline, and core game mechanics.
 */
public abstract class LevelParent extends Observable {
    /**
     * Constructs a new level with specified background and screen dimensions.
     * Initializes game components including user plane, timeline, and lists of game actors.
     * 
     * @param backgroundImageName Path to the background image for this level
     * @param screenHeight Total height of the game screen
     * @param screenWidth Total width of the game screen
     * @param playerInitialHealth Starting health points for the player
     */
	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

	private final Group root;
	private final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;
	private final ImageView background;
	private boolean gamePlaying;

	private final List<ActiveActorDestructible> friendlyUnits;
	private final List<ActiveActorDestructible> enemyUnits;
	private final List<ActiveActorDestructible> userProjectiles;
	private final List<ActiveActorDestructible> enemyProjectiles;
	
	private int currentNumberOfEnemies;
	private LevelView levelView;
	private InputHandler inputHandler;

	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.inputHandler = new InputHandler(user, this::functPause, this::fireProjectile);
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();

		this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;
		initializeTimeline();
		friendlyUnits.add(user);
	}
	
    /**
     * Abstract method to initialize friendly units for the level.
     * Must be implemented by specific level subclasses to set up initial friendly actors.
     */
	protected abstract void initializeFriendlyUnits();
	
    /**
     * Checks the current game state to determine if the game is over.
     * This method should be implemented by subclasses to define win/lose conditions.
     */
	protected abstract void checkIfGameOver();

    /**
     * Spawns enemy units for the current level.
     * Abstract method to be implemented by specific level subclasses.
     * Defines how and when enemies are created during gameplay.
     */
	protected abstract void spawnEnemyUnits();

    /**
     * Creates and returns the LevelView for the current game level.
     * 
     * @return A LevelView instance specific to the current level
     */
	protected abstract LevelView instantiateLevelView();
	
    /**
     * Initializes and starts the game scene.
     * Sets up the background, friendly units, and displays player hearts.
     * 
     * @return The configured game Scene
     */
	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		return scene;
	}
	
	public void startGame() {
		background.requestFocus();
		timeline.play();
		gamePlaying = true; //game set to running at the beginning else user freeze in place
	}

    /**
     * Toggles the game between paused and running states.
     * When called during gameplay, it pauses the game timeline.
     * When called during pause, it resumes the game timeline.
     */
	public void functPause() {
		if (gamePlaying) {
			gamePlaying = false;
			timeline.pause();
		}
		else {
			gamePlaying = true;
			timeline.play();
		}
	}
	
    /**
     * Progresses to the next game level.
     * Stops the current level's timeline and notifies observers of the level change.
     * 
     * @param levelName Name or identifier of the next level
     */
	public void goToNextLevel(String levelName) {
		timeline.stop(); //to fix the pop up
		setChanged();
		notifyObservers(levelName);
	}

	private void updateScene() {
		spawnEnemyUnits();
		updateActors();
		generateEnemyFire();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		handleUserProjectileCollisions();
		handleEnemyProjectileCollisions();
		handlePlaneCollisions();
		removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
	}

	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	private void initializeBackground() {
	    background.setFocusTraversable(true);
	    background.setFitHeight(screenHeight);
	    background.setFitWidth(screenWidth);

	    background.setOnKeyPressed(inputHandler::handleKeyPress);
	    background.setOnKeyReleased(inputHandler::handleKeyRelease);

	    root.getChildren().add(background);
	}

    /**
     * Fires a projectile from the user's plane.
     * Adds the projectile to the game root and tracking list.
     */
	private void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		root.getChildren().add(projectile);
		userProjectiles.add(projectile);
	}

    /**
     * Generates projectile fire from enemy units.
     * Iterates through enemy units and spawns their projectiles.
     */
	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

    /**
     * Spawns a single enemy projectile in the game scene.
     * 
     * @param projectile The projectile to be added to the game
     */
	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
		}
	}

    /**
     * Updates the positions and states of all actors in the game.
     * Includes updating friendly units, enemy units, and both user and enemy projectiles.
     */
	private void updateActors() {
		friendlyUnits.forEach(plane -> plane.updateActor());
		enemyUnits.forEach(enemy -> enemy.updateActor());
		userProjectiles.forEach(projectile -> projectile.updateActor());
		enemyProjectiles.forEach(projectile -> projectile.updateActor());
	}

    /**
     * Removes all destroyed actors from the game scene and tracking lists.
     * Cleans up actors that have been marked as destroyed across all actor lists.
     */
	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
	}

	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed())
				.collect(Collectors.toList());
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	private void handlePlaneCollisions() {
		handleCollisions(friendlyUnits, enemyUnits);
	}

	private void handleUserProjectileCollisions() {
		handleCollisions(userProjectiles, enemyUnits);
	}

	private void handleEnemyProjectileCollisions() {
		handleCollisions(enemyProjectiles, friendlyUnits);
	}

    /**
     * Handles collision detection between two lists of actors.
     * Checks for intersections between actors and applies damage when they collide.
     * 
     * @param actors1 First list of actors to check for collisions
     * @param actors2 Second list of actors to check for collisions
     */
	private void handleCollisions(List<ActiveActorDestructible> actors1,
			List<ActiveActorDestructible> actors2) {
		for (ActiveActorDestructible actor : actors2) {
			for (ActiveActorDestructible otherActor : actors1) {
				if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
					actor.takeDamage();
					otherActor.takeDamage();
				}
			}
		}
	}

    /**
     * Checks if any enemies have penetrated the player's defenses.
     * Damages the player and destroys penetrating enemies.
     */
	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		}
	}

	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
	}

    /**
     * Updates the kill count based on destroyed enemy units.
     * Increments the player's kill count for each enemy that has been destroyed.
     */
	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}

    /**
     * Determines if a specific enemy has penetrated the player's defenses.
     * 
     * @param enemy The enemy actor to check for penetration
     * @return True if the enemy has passed the screen width, false otherwise
     */
	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

    /**
     * Ends the game in a winning state.
     * Stops the game timeline and displays the win image.
     */
	protected void winGame() {
		timeline.stop();
		levelView.showWinImage();
		gamePlaying = false;
	}

    /**
     * Ends the game in a losing state.
     * Stops the game timeline and displays the game over image.
     */
	protected void loseGame() {
		timeline.stop();
		levelView.showGameOverImage();
		gamePlaying = false;
	}

	protected UserPlane getUser() {
		return user;
	}

	protected Group getRoot() {
		return root;
	}

	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	protected double getScreenWidth() {
		return screenWidth;
	}

	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}

}