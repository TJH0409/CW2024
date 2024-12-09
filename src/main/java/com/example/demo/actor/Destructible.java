package com.example.demo.actor;

/**
 * Represents an object that can take damage and potentially be destroyed.
 * 
 * This interface provides methods for managing the health and destruction 
 * of objects in a game or simulation environment.
 */

public interface Destructible {

	void takeDamage();

	void destroy();
	
}
