package com.example.demo.actor;

/**
* Abstract base class for actors that can be destroyed and have active update mechanisms.
* Combines active state management with destructibility.
*/
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	/** Tracks whether the actor has been destroyed. */
	private boolean isDestroyed;

	/**
	 * Constructs an active, destructible actor with initial image and position.
	 * 
	 * @param imageName Name of the actor's image asset
	 * @param imageHeight Height of the actor's image
	 * @param initialXPos Initial horizontal position
	 * @param initialYPos Initial vertical position
	 */	
	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;
	}

	/**
	 * Updates the actor's position.
	 * Must be implemented by subclasses.
	 */
	@Override
	public abstract void updatePosition();

	/**
	 * Updates the actor's overall state.
	 * Must be implemented by subclasses.
	 */
	public abstract void updateActor();

	/**
	 * Handles damage logic for the actor.
	 * Must be implemented by subclasses.
	 */
	@Override
	public abstract void takeDamage();

	/**
	 * Marks the actor as destroyed.
	 */
	@Override
	public void destroy() {
		setDestroyed(true);
	}

	/**
	 * Sets the destruction state of the actor.
	 * 
	 * @param isDestroyed New destruction state
	 */
	protected void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	/**
	 * Checks if the actor has been destroyed.
	 * 
	 * @return true if destroyed, false otherwise
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}
	
}
