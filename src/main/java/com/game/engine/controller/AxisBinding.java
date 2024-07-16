package com.game.engine.controller;

/**
 * @author Lobsang RIGARD
 * Class used to represent a binding that can be associated with an axis
 */
public abstract class AxisBinding {
    /**
     * Called when the binded axis's value changes
     * @param value
     */
    public abstract void onChange(float value);
}
