package com.example.demo;

public class Shield {
    private boolean active;
    private int activeFrames;
    private final int maxShieldFrames;
    private final double activationProbability;

    public Shield(int maxShieldFrames, double activationProbability) {
        this.maxShieldFrames = maxShieldFrames;
        this.activationProbability = activationProbability;
        this.active = false;
        this.activeFrames = 0;
    }

    public void update() {
        if (active) {
            activeFrames++;
            if (isExhausted()) deactivate();
        } else if (shouldActivate()) {
            activate();
        }
    }

    public boolean isActive() {
        return active;
    }

    private boolean shouldActivate() {
        return Math.random() < activationProbability;
    }

    private boolean isExhausted() {
        return activeFrames >= maxShieldFrames;
    }

    private void activate() {
        active = true;
        activeFrames = 0;
    }

    private void deactivate() {
        active = false;
        activeFrames = 0;
    }
}
