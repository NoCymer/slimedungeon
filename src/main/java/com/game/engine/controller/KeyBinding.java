package com.game.engine.controller;

public abstract class KeyBinding {
    private boolean overridesJoystick = false;

    protected KeyBinding(boolean overridesJoystick) {
        this.overridesJoystick = overridesJoystick;
    }
    protected KeyBinding() { this(false); }
    
    public void onPressed() {};
    public void onReleased() {}

    public boolean overridesJoystick() {
        return overridesJoystick;
    }
    public void setOverridesJoystick(boolean overridesJoystick) {
        this.overridesJoystick = overridesJoystick;
    };
}
