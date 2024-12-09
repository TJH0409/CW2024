package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import com.example.demo.levels.LevelParent;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Controls game level navigation and manages level transitions.
 * 
 * Implements Observer pattern to handle dynamic level changes.
 */
public class Controller implements Observer {

	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.levels.LevelOne";
	private final Stage stage;

    /**
     * Constructs a Controller with the primary application stage.
     * 
     * @param stage The main JavaFX stage for the game
     */
	public Controller(Stage stage) {
		this.stage = stage;
	}

    /**
     * Initializes and displays the game, starting with the first level.
     * 
     * @throws Various reflection-related exceptions during level initialization
     */
	public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {

			stage.show();
			goToLevel(LEVEL_ONE_CLASS_NAME);
	}

    /**
     * Dynamically loads and initializes a game level by its class name.
     * 
     * Uses reflection to create level instances with stage dimensions.
     * 
     * @param className Fully qualified name of the level class to load
     * @throws Various reflection-related exceptions during level creation
     */
	private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			Class<?> myClass = Class.forName(className);
			Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
			LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());
			myLevel.addObserver(this);
			Scene scene = myLevel.initializeScene();
			stage.setScene(scene);
			myLevel.startGame();

	}

    /**
     * Handles level transitions when notified by an Observable level.
     * 
     * Attempts to load the next level or shows an error alert if transition fails.
     * 
     * @param arg0 The Observable object (level) triggering the update
     * @param arg1 The next level's class name
     */
	@Override
	public void update(Observable arg0, Object arg1) {
		try {
			goToLevel((String) arg1);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getClass().toString());
			alert.show();
		}
	}

}
