package com.game.engine.controller;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Lobsang RIGARD
 * Manages the Keybinds, interfaces with awt and calls the appropriate binding
 * according to the appropriate action that was made to the keyboard
 */
public class KeyBindingsManager {
    private static HashMap<Integer, KeyBinding> keyMap = new HashMap<Integer, KeyBinding>();
    private static ArrayList<Integer> joystickOverridePressedKeys = new ArrayList<Integer>();
	private static Timer timer = new Timer("Timer");
	private static TimerTask task;

    /**
     * Binds a key to a binding
     * @param keyCode AWT keycode of the key
     * @param binding Binding to associate
     */
    public static void bindKey(int keyCode, KeyBinding binding) {
        keyMap.put(keyCode, binding);
    }

    /**
     * Starts overriding the acade inputs
     */
    public static void startJoystickOverride() {
		ArcadeControllerBindingsManager.overriden = true;
    }

    /***
     * Stops overriding arcade controller after delay ms
     * @param delay Time before override stop in ms
     */
    public static void endJoystickOverride(int delay) {
		if(task != null) task.cancel();

		task = new TimerTask() {
			@Override
			public void run() {
				ArcadeControllerBindingsManager.overriden = false;
			}
		};

		timer.schedule(task, delay);
    }

    /**
     * Handles the press of a key by calling the appropriate bindings
     * Overrides all arcade input in the meantime
     * @apiNote Must be called inside of an awt keyController or keyAdapter 
     * @param e KeyEvent
     */
    public static void handleKeyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        KeyBinding binding = keyMap.get(keyCode);
        if(binding == null) return;

        if(!joystickOverridePressedKeys.contains(keyCode))
            joystickOverridePressedKeys.add(keyCode);
        startJoystickOverride();

        binding.onPressed();
    }

    /**
     * Handles the release of a key by calling the appropriate bindings
     * Stops all arcade input override after a given duration
     * @apiNote Must be called inside of an awt keyController or keyAdapter 
     * @param e KeyEvent
     */
    public static void handleKeyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        KeyBinding binding = keyMap.get(keyCode);
        if(binding == null) return;

        if(joystickOverridePressedKeys.contains(keyCode))
            joystickOverridePressedKeys.remove((Integer)keyCode);
        if(joystickOverridePressedKeys.size() == 0) {
            endJoystickOverride(1000);
        }

        binding.onReleased();
    }
}
