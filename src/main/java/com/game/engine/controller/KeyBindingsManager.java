package com.game.engine.controller;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class KeyBindingsManager {
    private static HashMap<Integer, KeyBinding> keyMap = new HashMap<Integer, KeyBinding>();
    private static ArrayList<Integer> joystickOverridePressedKeys = new ArrayList<Integer>();
	private static Timer timer = new Timer("Timer");
	private static TimerTask task;
    private static JoystickController jc;

    public static void bindKey(int keyCode, KeyBinding binding) {
        keyMap.put(keyCode, binding);
    }

    public static void startJoystickOverride() {
		jc.overriden = true;
    }

    public static void endJoystickOverride(int delay) {
		if(task != null) task.cancel();

		task = new TimerTask() {
			@Override
			public void run() {
				jc.overriden = false;
			}
		};

		timer.schedule(task, delay);
    }

    public static void handleKeyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        KeyBinding binding = keyMap.get(keyCode);
        if(binding == null) return;

        if(binding.overridesJoystick()) {
            if(!joystickOverridePressedKeys.contains(keyCode))
                joystickOverridePressedKeys.add(keyCode);
            startJoystickOverride();
        }

        binding.onPressed();
    }

    public static void handleKeyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        KeyBinding binding = keyMap.get(keyCode);
        if(binding == null) return;

        if(binding.overridesJoystick()) {
            if(joystickOverridePressedKeys.contains(keyCode))
                joystickOverridePressedKeys.remove((Integer)keyCode);
            if(joystickOverridePressedKeys.size() == 0) {
                endJoystickOverride(1000);
            }
        }

        binding.onReleased();
    }

    public static JoystickController getJoystickController() {
        return jc;
    }

    public static void setJoystickController(JoystickController jc) {
        KeyBindingsManager.jc = jc;
    }

    
}
