package com.game.engine.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.Timer;
import java.util.TimerTask;

public class KeyController extends KeyAdapter {

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

	/**
	 * keyboard controller
	 */
	public KeyController(Control c, JoystickController jc) {
		this.c = c;
		this.jc = jc;
	}
	
	private Timer timer = new Timer("Timer");
	private TimerTask task;

	/**
	 *  Overrides the joystick for a given delay in ms then goes back to normal
	 * @param delay Delay in ms befor override is over
	 */
	private void overrideJoystick(int delay) {
		jc.overriden = true;
		
		if(task != null) task.cancel();

		task = new TimerTask() {
			@Override
			public void run() {
				jc.overriden = false;
			}
		};

		timer.schedule(task, delay);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Movement key pressed considered true
		// until proven it has not been pressed
		boolean kbMovementPressed = true;

		// Menu action key pressed considered true
		// until proven it has not been pressed
		boolean kbMenuActionPressed = true;

		// Movement controls
		if(canMove){
			switch (e.getKeyCode()) {
				// left key
				case KeyEvent.VK_LEFT:
					c.x = -1;
					break;
				// right key
				case KeyEvent.VK_RIGHT:
					c.x = 1;
					break;
				// up key
				case KeyEvent.VK_UP:
					c.y = -1;
					break;
				// down key
				case KeyEvent.VK_DOWN:
					c.y = 1;
					break;
				default:
					kbMovementPressed = false;
					break;
			}	
		}
		else kbMovementPressed = false;

		// Menu controls
		switch (e.getKeyCode()) {
			// M key
			case KeyEvent.VK_M:
				map = true;
				break;
			// F key
			case KeyEvent.VK_F:
				nextDialog = true;
				break;
			// Q key
			case KeyEvent.VK_Q:
				fin = true;
				break;
			default:
				kbMenuActionPressed = false;
				break;
		}

		if(kbMovementPressed) overrideJoystick(1000);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			// left key
			case KeyEvent.VK_LEFT:
				if(c.x<0) c.x = 0;
				break;
			// right key
			case KeyEvent.VK_RIGHT:
				if(c.x>0) c.x = 0;
				break;
			// up key
			case KeyEvent.VK_UP:
				if(c.y<0) c.y = 0;
				break;
			// down key
			case KeyEvent.VK_DOWN:
				if(c.y>0) c.y = 0;
				break;
			// M key
			case KeyEvent.VK_M:
				map = false;
				break;
			// F key
			case KeyEvent.VK_F:
				nextDialog = false;
				break;
			// Q key
			case KeyEvent.VK_Q:
				fin = false;
				break;
			// Escape key
			case KeyEvent.VK_ESCAPE:
				closeShop = true;
				canMove = true;
				break;
			default:
				break;
		}
	}

}
