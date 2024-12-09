package com.example.demo.input;

import com.example.demo.actor.UserPlane;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Handles user input for controlling the player's plane and game state.
 * Manages keyboard events for movement, firing projectiles, and pausing the game.
 */
public class InputHandler {

	/** The user-controlled plane that responds to movement inputs. */
    private final UserPlane userPlane;
    
    /** Action to be executed when the game is paused. */
    private final Runnable pauseGameAction;
    
    /** Action to be executed when firing a projectile. */
    private final Runnable fireProjectileAction;

    /**
     * Constructs an InputHandler with specific actions for the user plane.
     * 
     * @param userPlane The plane controlled by the user
     * @param pauseGameAction Runnable to pause or unpause the game
     * @param fireProjectileAction Runnable to fire a projectile
     */
    public InputHandler(UserPlane userPlane, Runnable pauseGameAction, Runnable fireProjectileAction) {
        this.userPlane = userPlane;
        this.pauseGameAction = pauseGameAction;
        this.fireProjectileAction = fireProjectileAction;
    }

    /**
     * Handles key press events to control the user plane and game state.
     * Supports multiple key bindings for movement and actions:
     * - ESCAPE: Pause/Unpause game
     * - UP/W: Move plane upward
     * - DOWN/S: Move plane downward
     * - LEFT/A: Move plane left
     * - RIGHT/D: Move plane right
     * - SPACE: Fire projectile
     * 
     * @param event The key press event to be processed
     */
    public void handleKeyPress(KeyEvent event) {
        KeyCode key = event.getCode();
        switch (key) {
            case ESCAPE -> pauseGameAction.run();
            case UP, W -> userPlane.moveUp();
            case DOWN, S -> userPlane.moveDown();
            case LEFT, A -> userPlane.moveLeft();
            case RIGHT, D -> userPlane.moveRight();
            case SPACE -> fireProjectileAction.run();
            default -> {
            }
        }
    }

    /**
     * Handles key release events to stop plane movement.
     * Stops vertical or horizontal movement when corresponding keys are released.
     * 
     * @param event The key release event to be processed
     */
    public void handleKeyRelease(KeyEvent event) {
        KeyCode key = event.getCode();
        switch (key) {
            case UP, W, DOWN, S -> userPlane.stopVertical();
            case LEFT, A, RIGHT, D -> userPlane.stopHorizontal();
            default -> {
            }
        }
    }
}
