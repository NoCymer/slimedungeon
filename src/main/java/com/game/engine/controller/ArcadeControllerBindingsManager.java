package com.game.engine.controller;

import java.util.HashMap;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

/**
 * @author Lobsang RIGARD
 * @implNote Requires JInput to be installed 
 * Class used to manage all bindings to an arcade's axises and keys
 */
public class ArcadeControllerBindingsManager {
    
    private static HashMap<String, KeyBinding> keyMap = new HashMap<String, KeyBinding>();
    private static HashMap<String, AxisBinding> axisMap = new HashMap<String, AxisBinding>();
    private static float EPSILON = (float)0.1;
    public static boolean overriden = false;

    /**
     * Binds a key to a binding, when the key is pressed or released the corresponding
     * method of the binging is called. 
     * @param keyCode Code of the key
     * @param binding Binding to call when a key is pressed or released
     */
    public static void bindKey(String keyCode, KeyBinding binding) {
        keyMap.put(keyCode, binding);
    }

    /**
     * Binds an axis to a binding, when the axis value changes the binding is called accordingly
     * @param axisName Name of the axis
     * @param binding Binding to call when the axis value chages
     */
    public static void bindAxis(String axisName, AxisBinding binding) {
        axisMap.put(axisName, binding);
    }

    /**
     * Satnitizes an analog input by removing some precision that could
     * catch stick drift, therefore making unwanted call to axis logic
     * @param rawInput Non-sanitized input
     * @return Sanitized input
     */
    public static float sanitizeAnalogInput(float rawInput){
        // Only consider 2 digits after the point
        float value = (float)Math.round(rawInput * 100)/100;

        // If the value is too small do not consider it
        // In order to prevent stick drift detection
        if(Math.abs(value) - EPSILON < 0.1) {
            value = 0;
        }
        return value;
    }

    /**
     * Checks and handle input if one has been made.
     */
    public static void checkForInput() {
        // If the arcade controller is overriden do not
        // interpret any input made from it
        if(overriden) return;
        /* Get the available controllers */
        Controller[] controllers = ControllerEnvironment
                .getDefaultEnvironment().getControllers();

        for (int i = 0; i < controllers.length; i++) {
            /* Polling each controller */
            controllers[i].poll();

            /* Get the controllers event queue */
            EventQueue queue = controllers[i].getEventQueue();

            /* Create an event object for the underlying plugin to populate */
            Event event = new Event();

            /* For each object in the queue */
            while (queue.getNextEvent(event)) {
                // Gets the component and sanitize the input
                Component comp = event.getComponent();
                float inputValue = sanitizeAnalogInput(event.getValue());
                // Checks for key input
                keyMap.forEach((key,value) -> {
                    if(comp.getName().equals(key)) {
                        if (!comp.isAnalog()) {
                            if(inputValue == 1f) {
                                value.onPressed();
                            } else {
                                value.onReleased();
                            }
                        }
                    }
                });
                // Checks for axis input
                axisMap.forEach((key,value) -> {
                    if(comp.getName().equals(key)) {
                        if (comp.isAnalog()) {
                            value.onChange(inputValue);
                        }
                    }
                });
            }
        }
    }
}
