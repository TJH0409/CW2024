package com.example.demo.config;

/**
* Configuration constants for the User-controlled Plane in the game.
* Defines visual, movement, and boundary parameters for the player's plane.
*/
public class UserPlaneConfig {
    public static final String IMAGE_NAME = "userplane.png";
    public static final double Y_UPPER_BOUND = -40.0;
    public static final double Y_LOWER_BOUND = 600.0;
    public static final double INITIAL_X_POSITION = 5.0;
    public static final double INITIAL_Y_POSITION = 300.0;
    public static final int IMAGE_HEIGHT = 150;
    public static final int VERTICAL_VELOCITY = 8;
    public static final int PROJECTILE_X_POSITION = 110;
    public static final int PROJECTILE_Y_OFFSET = 20;
    /** Allow player to move horizontally */
    public static final double X_LEFT_BOUND = 0.0;
    public static final double X_RIGHT_BOUND = 800.0; 
    public static final int HORIZONTAL_VELOCITY = 8;
}
