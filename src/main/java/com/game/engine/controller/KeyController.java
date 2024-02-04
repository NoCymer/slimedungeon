package com.game.engine.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;

import com.game.engine.hud.shop.Shop;
import com.game.engine.physics.Player;
import com.game.engine.shop.AttackBooster;
import com.game.engine.shop.DefenceBooster;
import com.game.engine.shop.HealthBooster;
import com.game.engine.shop.HealthRestore;
import com.game.engine.shop.ShopManager;

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

	/** If the player wants to buy health booster */
	public static boolean buyHealthBooster = false;

	/** If the player wants to buy defence booster */
	public static boolean buyDefenceBooster = false;

	/** If the player wants to buy attack booster */
	public static boolean buyAttackBooster = false;

	/** If the player wants to buy health restore */
	public static boolean buyHealthRestore = false;

	/** Reference to a player */
	private static Player player;
	

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

		// Open shop
		KeyBindingsManager.bindKey(KeyEvent.VK_U, new KeyBinding(true) {
			@Override
			public void onPressed() {
				if(!ShopManager.instance().isShopOpened()) {
					ShopManager.instance().openShop();
				}
				else {
					ShopManager.instance().closeShop();
				}
			}
			@Override
			public void onReleased() { }
		});

		// Close shop
		KeyBindingsManager.bindKey(KeyEvent.VK_ESCAPE, new KeyBinding(true) {
			@Override
			public void onPressed() { }
			@Override
			public void onReleased() {
				ShopManager.instance().closeShop();
			}
		});

		// Buy health booster
		KeyBindingsManager.bindKey(KeyEvent.VK_I, new KeyBinding(true) {
			@Override
			public void onPressed() {
				if(ShopManager.instance().isShopOpened() && !buyHealthBooster) {
					buyHealthBooster = true;
					ShopManager.instance().tryBuyItem(player, new HealthBooster());
				}
			}
			@Override
			public void onReleased() {
				buyHealthBooster = false;
			}
		});

		// Buy defence booster
		KeyBindingsManager.bindKey(KeyEvent.VK_O, new KeyBinding(true) {
			@Override
			public void onPressed() {
				if(ShopManager.instance().isShopOpened()  && !buyDefenceBooster) {
					buyDefenceBooster = true;
					ShopManager.instance().tryBuyItem(player, new DefenceBooster());
				}
			}
			@Override
			public void onReleased() {
				buyDefenceBooster = false;
			}
		});

		// Buy attack booster
		KeyBindingsManager.bindKey(KeyEvent.VK_P, new KeyBinding(true) {
			@Override
			public void onPressed() {
				if(ShopManager.instance().isShopOpened()  && !buyAttackBooster) {
					buyAttackBooster = true;
					ShopManager.instance().tryBuyItem(player, new AttackBooster());
				}
			}
			@Override
			public void onReleased() {
				buyAttackBooster = false;
			}
		});

		// Buy health restore
		KeyBindingsManager.bindKey(KeyEvent.VK_L, new KeyBinding(true) {
			@Override
			public void onPressed() {
				if(ShopManager.instance().isShopOpened()  && !buyHealthRestore) {
					buyHealthRestore = true;
					ShopManager.instance().tryBuyItem(player, new HealthRestore());
				}
			}
			@Override
			public void onReleased() {
				buyHealthRestore = false;
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

	public static Player getPlayer() {
		return player;
	}

	public static void setPlayer(Player player) {
		KeyController.player = player;
	}


	
}
