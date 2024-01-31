package com.game.engine.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class KeyboardController implements KeyListener {

	/**
	 * end of the game
	 */
	public static boolean fin = false;

	/** the player can move or not */
	public static boolean canMove = true;

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

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	/**
	 * keyboard controller
	 */
	public KeyboardController(Control c, JoystickController jc) {
		this.c = c;
		this.jc = jc;
	}
	
	private Timer timer = new Timer("Timer");
	private TimerTask task;

	/** 
	 * @param e
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(canMove){
			// left key
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				c.left = true;
				c.x = -1;
			}
			// right key
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				c.right = true;
				c.x = 1;
			}
			// up key
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				c.up = true;
				c.y = -1;
			}
			// down key
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				c.down = true;
				c.y = 1;
			}
			// Q key
			if (e.getKeyCode() == KeyEvent.VK_Q) {
				fin = true;
			}
			
		}
		// M key
		if (e.getKeyCode() == KeyEvent.VK_M) {
			map = true;
		}
		// F key
		if (e.getKeyCode() == KeyEvent.VK_F){
			nextDialog = true;
		}
		jc.overriden = true;
		if(task != null)
			task.cancel();
		task = new TimerTask() {
			@Override
			public void run() {
				jc.overriden = false;
			}
		};
		timer.schedule(task, 1000);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// left key
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			c.left = false;
			if(c.x<0) c.x = 0;
		}
		// right key
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			c.right = false;
			if(c.x>0) c.x = 0;
		}
		// up key
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			c.up = false;
			if(c.y<0) c.y = 0;
		}
		// down key
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			c.down = false;
			if(c.y>0) c.y = 0;
		}
		// M key
		if (e.getKeyCode() == KeyEvent.VK_M) {
			map = false;
		}
		// F key
		if (e.getKeyCode() == KeyEvent.VK_F){
			nextDialog = false;
		}
		// Escape key
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
			closeShop = true;
			canMove = true;
		}	
	}

}
