package com.game.engine.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Pierre-Frederic Villard
 */
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

	/** Opens the map*/
	public static boolean map = false;

	/** Shows the next dialog */
	public static boolean nextDialog = false;

	/** Wheter or not the shop must be closed */
	public static boolean closeShop = true;

	/** 
	 * @param e
	 */
	// constructeur avec affichage du controleur ou non.

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	/**
	 * keyboard controller
	 */
	public KeyboardController() {
		c = new Control();
	}
	
	
	
	/** 
	 * @param e
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(canMove){
			// left key
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				c.left = true;
			}
			// right key
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				c.right = true;
			}
			// up key
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				c.up = true;
			}
			// down key
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				c.down = true;
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
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// left key
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			c.left = false;
		}
		// right key
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			c.right = false;
		}
		// up key
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			c.up = false;
		}
		// down key
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			c.down = false;
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
