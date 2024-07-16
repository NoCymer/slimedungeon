package com.game.engine.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;

import com.game.Game;
import com.game.engine.controller.bindings.BindingBuyAttackBooster;
import com.game.engine.controller.bindings.BindingBuyDefenceBooster;
import com.game.engine.controller.bindings.BindingBuyHealthBooster;
import com.game.engine.controller.bindings.BindingBuyHealthRestore;
import com.game.engine.controller.bindings.BindingCloseShop;
import com.game.engine.controller.bindings.BindingMoveDown;
import com.game.engine.controller.bindings.BindingMoveLeft;
import com.game.engine.controller.bindings.BindingMoveRight;
import com.game.engine.controller.bindings.BindingMoveUp;
import com.game.engine.controller.bindings.BindingMoveXAxis;
import com.game.engine.controller.bindings.BindingMoveYAxis;
import com.game.engine.controller.bindings.BindingNextDialog;
import com.game.engine.controller.bindings.BindingOpenMap;
import com.game.engine.controller.bindings.BindingQuitGame;
import com.game.engine.controller.bindings.BindingRestartGame;
import com.game.engine.controller.bindings.BindingToggleShop;
import com.game.engine.hud.menu.Menu;
import com.game.engine.physics.Player;

public class InputController extends KeyAdapter {

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
	public static Control c;

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
	public InputController(Control c) {
		InputController.c = c;

		// Left movement
		KeyBindingsManager.bindKey(KeyEvent.VK_LEFT, new BindingMoveLeft());

		// Right movement
		KeyBindingsManager.bindKey(KeyEvent.VK_RIGHT, new BindingMoveRight());

		// Up movement
		KeyBindingsManager.bindKey(KeyEvent.VK_UP, new BindingMoveUp());

		// Down movement
		KeyBindingsManager.bindKey(KeyEvent.VK_DOWN, new BindingMoveDown());

		// Open map
		KeyBindingsManager.bindKey(KeyEvent.VK_M, new BindingOpenMap());

		// Next dialog
		KeyBindingsManager.bindKey(KeyEvent.VK_F, new BindingNextDialog());

		// Quit game
		KeyBindingsManager.bindKey(KeyEvent.VK_Q, new BindingQuitGame());

		// Toggle shop
		KeyBindingsManager.bindKey(KeyEvent.VK_U, new BindingToggleShop());

		// Close shop
		KeyBindingsManager.bindKey(KeyEvent.VK_ESCAPE, new BindingCloseShop());

		// Buy health booster
		KeyBindingsManager.bindKey(KeyEvent.VK_I, new BindingBuyHealthBooster());

		// Buy defence booster
		KeyBindingsManager.bindKey(KeyEvent.VK_O, new BindingBuyDefenceBooster());

		// Buy attack booster
		KeyBindingsManager.bindKey(KeyEvent.VK_P, new BindingBuyAttackBooster());

		// Buy health restore
		KeyBindingsManager.bindKey(KeyEvent.VK_L, new BindingBuyHealthRestore());

		// Restart game
		KeyBindingsManager.bindKey(KeyEvent.VK_R, new BindingRestartGame());

		ArcadeControllerBindingsManager.bindKey("Base 3", new BindingNextDialog());
		ArcadeControllerBindingsManager.bindKey("Base 4", new BindingRestartGame());

		ArcadeControllerBindingsManager.bindKey("Trigger", new BindingBuyAttackBooster());
		ArcadeControllerBindingsManager.bindKey("Thumb", new BindingBuyDefenceBooster());
		ArcadeControllerBindingsManager.bindKey("Thumb 2", new BindingBuyHealthBooster());
		ArcadeControllerBindingsManager.bindKey("Top", new BindingBuyHealthRestore());

		ArcadeControllerBindingsManager.bindKey("Top 2", new KeyBinding() {
			@Override
			public void onPressed() {
				if(Game.world.huds.get("gameOver").isShown() || Game.world.huds.get("endMenu").isShown() || Game.world.huds.get("menu").isShown())
					(new BindingQuitGame()).onPressed();
				else {
					(new BindingToggleShop()).onPressed();
				}
			}
			@Override
			public void onReleased() { }
		});

		ArcadeControllerBindingsManager.bindKey("Pinkie", new KeyBinding() {
			@Override
			public void onPressed() {
				if(Game.world.huds.get("gameOver").isShown() || Game.world.huds.get("endMenu").isShown()){
					(new BindingRestartGame()).onPressed();
				}
				else if(Game.world.huds.get("menu").isShown()){
					Game.world.huds.get("menu").setInteractable(false);   
					Game.world.huds.get("menu").setIsShown(false);
					((Menu)Game.world.huds.get("menu")).getpHud().setInteractable(true);
					((Menu)Game.world.huds.get("menu")).getpHud().setIsShown(true);
				}
				else
					(new BindingOpenMap()).onPressed();
			}
			@Override
			public void onReleased() {
				(new BindingOpenMap()).onReleased();
			}
		});

		ArcadeControllerBindingsManager.bindAxis("x", new BindingMoveXAxis());
		ArcadeControllerBindingsManager.bindAxis("y", new  BindingMoveYAxis());
		
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
		InputController.player = player;
	}


	
}
