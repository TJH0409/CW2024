package com.example.demo.actor;

/**
 * Represents a shield mechanism with configurable activation and duration.
 * 
 * The shield can be activated based on a probability and has a limited active duration.
 * It automatically manages its activation and deactivation states.
 */
public class Shield {
    private boolean active;
    private int activeFrames;
    private final int maxShieldFrames;
    private final double activationProbability;

    /**
     * Constructs a new Shield with specified maximum active frames and activation probability.
     * 
     * @param maxShieldFrames Maximum number of frames the shield can remain active
     * @param activationProbability Probability of shield activation (between 0.0 and 1.0)
     */
    public Shield(int maxShieldFrames, double activationProbability) {
        this.maxShieldFrames = maxShieldFrames;
        this.activationProbability = activationProbability;
        this.active = false;
        this.activeFrames = 0;
    }


    /**
     * Updates the shield's state, managing activation and deactivation.
     * 
     * If the shield is active, it increments active frames and deactivates when exhausted.
     * If inactive, it may activate based on the activation probability.
     */
    public void update() {
        if (active) {
            activeFrames++;
            if (isExhausted()) deactivate();
        } else if (shouldActivate()) {
            activate();
        }
    }

    /**
     * Checks if the shield is currently active.
     * 
     * @return true if the shield is active, false otherwise
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Determines if the shield should be activated based on random probability.
     * 
     * @return true if shield activation is triggered, false otherwise
     */
    private boolean shouldActivate() {
        return Math.random() < activationProbability;
    }

    /**
     * Checks if the shield has exceeded its maximum active frames.
     * 
     * @return true if the shield is exhausted, false otherwise
     */
    private boolean isExhausted() {
        return activeFrames >= maxShieldFrames;
    }

    /** Activates the shield, resetting its active frames. */
    private void activate() {
        active = true;
        activeFrames = 0;
    }

    /** Deactivates the shield, resetting its active frames. */
    private void deactivate() {
        active = false;
        activeFrames = 0;
    }
}
