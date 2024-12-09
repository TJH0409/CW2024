package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main application class for launching the Sky Battle game.
 * 
 * Extends JavaFX Application and sets up the primary stage for the game.
 */
public class Main extends Application {

	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;
	private static final String TITLE = "Sky Battle";
	private Controller myController;

    /**
     * Initializes and launches the game window.
     * 
     * Configures the primary stage with fixed dimensions and launches the game controller.
     * 
     * @param stage The primary stage for this application
     * @throws Various reflection-related exceptions during controller initialization
     */
	@Override
	public void start(Stage stage) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		stage.setTitle(TITLE);
		stage.setResizable(false);
		stage.setHeight(SCREEN_HEIGHT);
		stage.setWidth(SCREEN_WIDTH);
		myController = new Controller(stage);
		myController.launchGame();
	}

    /**
     * Entry point of the application.
     * 
     * Launches the JavaFX application.
     * 
     * @param args Command-line arguments
     */
	public static void main(String[] args) {
		launch();
	}
}