package com.example.demo.input;

import com.example.demo.actor.UserPlane;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputHandler {

    private final UserPlane userPlane;
    private final Runnable pauseGameAction;
    private final Runnable fireProjectileAction;

    public InputHandler(UserPlane userPlane, Runnable pauseGameAction, Runnable fireProjectileAction) {
        this.userPlane = userPlane;
        this.pauseGameAction = pauseGameAction;
        this.fireProjectileAction = fireProjectileAction;
    }

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
