package com.game.engine.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;

public class KeyController extends KeyAdapter {

	/**
	 * end of the game
	 */
	public static boolean closeGame = false;

	/** the player can move or not */
	public static boolean canMove = true;

	public static ArrayList<Integer> activeKeys = new ArrayList<Integer>();

	/**
	 * The controller
	 */
	public Control c;

	public JoystickController jc;

	/** Opens the map*/
	public static boolean map = false;

	/** Shows the next dialog */
	public static boolean nextDialog = false;

	/** Wheter or not the shop must be closed */
	public static boolean closeShop = true;

	/** Wheter or not the shop must be closed */
	public static boolean buyHealthBooster = false;

	/** Wheter or not the shop must be closed */
	public static boolean buyDefenceBooster = false;

	/** Wheter or not the shop must be closed */
	public static boolean buyAttackBooster = false;

	/**
	 * keyboard controller
	 */
	public KeyController(Control c, JoystickController jc) {
		this.c = c;
		this.jc = jc;

		// Left movement
		KeyBindingsManager.bindKey(KeyEvent.VK_LEFT, new KeyBinding(true) {
			@Override
			public void onPressed() {
				if(canMove) c.x = -1;
			}
			@Override
			public void onReleased() {
				if(c.x<0) c.x = 0;
			}
		});

		// Right movement
		KeyBindingsManager.bindKey(KeyEvent.VK_RIGHT, new KeyBinding(true) {
			@Override
			public void onPressed() {
				if(canMove) c.x = 1;
			}
			@Override
			public void onReleased() {
				if(c.x>0) c.x = 0;
			}
		});

		// Up movement
		KeyBindingsManager.bindKey(KeyEvent.VK_UP, new KeyBinding(true) {
			@Override
			public void onPressed() {
				if(canMove) c.y = -1;
			}
			@Override
			public void onReleased() {
				if(c.y<0) c.y = 0;
			}
		});

		// Down movement
		KeyBindingsManager.bindKey(KeyEvent.VK_DOWN, new KeyBinding(true) {
			@Override
			public void onPressed() {
				if(canMove) c.y = 1;
			}
			@Override
			public void onReleased() {
				if(c.y>0) c.y = 0;
			}
		});

		// Open map
		KeyBindingsManager.bindKey(KeyEvent.VK_M, new KeyBinding(true) {
			@Override
			public void onPressed() {
				map = true;
			}
			@Override
			public void onReleased() {
				map = false;
			}
		});

		// Next dialog
		KeyBindingsManager.bindKey(KeyEvent.VK_F, new KeyBinding(true) {
			@Override
			public void onPressed() {
				nextDialog = true;
			}
			@Override
			public void onReleased() {
				nextDialog = false;
			}
		});

		// Quit game
		KeyBindingsManager.bindKey(KeyEvent.VK_Q, new KeyBinding(true) {
			@Override
			public void onPressed() {
				closeGame = true;
			}
			@Override
			public void onReleased() {
				closeGame = false;
			}
		});

		// Close shop
		KeyBindingsManager.bindKey(KeyEvent.VK_ESCAPE, new KeyBinding(true) {
			@Override
			public void onPressed() { }
			@Override
			public void onReleased() {
				closeShop = true;
				canMove = true;
			}
		});

		// Buy health booster
		KeyBindingsManager.bindKey(KeyEvent.VK_F13, new KeyBinding(true) {
			@Override
			public void onPressed() {
				if(!closeShop) buyHealthBooster = true;
			}
			@Override
			public void onReleased() {
				buyHealthBooster = false;
			}
		});

		// Buy defence booster
		KeyBindingsManager.bindKey(KeyEvent.VK_F14, new KeyBinding(true) {
			@Override
			public void onPressed() {
				if(!closeShop) buyDefenceBooster = true;
			}
			@Override
			public void onReleased() {
				buyDefenceBooster = false;
			}
		});

		// Buy attack booster
		KeyBindingsManager.bindKey(KeyEvent.VK_F15, new KeyBinding(true) {
			@Override
			public void onPressed() {
				if(!closeShop) buyAttackBooster = true;
			}
			@Override
			public void onReleased() {
				buyAttackBooster = false;
			}
		});
	}

	@Override
	public void keyPressed(KeyEvent e) {
		KeyBindingsManager.handleKeyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		KeyBindingsManager.handleKeyReleased(e);
	}

}
