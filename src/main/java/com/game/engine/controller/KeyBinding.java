package com.game.engine.controller;

/**
 * @author Lobsang RIGARD
 * Class used to represent a binding that can be associated with a keystroke 
 */
public abstract class KeyBinding {
    protected KeyBinding() {}
    
    /**
     * Called when the key binded is pressed
     */
    public void onPressed() {};

    /**
     * Called when the key binded is released
     */
    public void onReleased() {};
}
