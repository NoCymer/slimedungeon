package com.game.engine.main;

import com.game.engine.controller.ArcadeControllerBindingsManager;
import com.game.engine.controller.InputController;

public class MainLoop {
	/** Frames per secons */
	public int fps = 60;
	/** The game physics */
	public GamePhysics gamePhysics;
	/** Keyboard controller */
	public InputController cKey;

	public static boolean canRender = true;
	/** Updates the fps 
	 * @param fps
	*/
	public void setFPS(int fps) {
		this.fps = fps;
	}

	public void addListeners() {
		gamePhysics.display.addKeyListener(cKey);
		gamePhysics.physicsEngine.world.c = InputController.c;
		gamePhysics.display.requestFocusInWindow();
	}

	/** Runs the game loop */
	public void start() throws Exception {

		long loopDuration = 1000000 / fps;

		addListeners();

		Thread.sleep(100);

		gamePhysics.display.requestFocusInWindow();

		long beforeTime = System.nanoTime();
		
		while (!InputController.closeGame) {
			// Hacky fix, thanks to threads obscure magic
			// Dont remove the sleep
			// As it will break the whole damn game
			// Let it sleep in peace
			if(!canRender) {
				Thread.sleep(1000);
				continue;
			}
			ArcadeControllerBindingsManager.checkForInput();
			gamePhysics.update();
			gamePhysics.render();

			while (System.nanoTime() - beforeTime - loopDuration * 1000L < 0) {}

			beforeTime = System.nanoTime();

			// Changes the world's map's position according to the window height
			gamePhysics.physicsEngine.world.map.setPosX(
				(gamePhysics.display.getWidth()/2) -
				(gamePhysics.physicsEngine.world.map.size()/2)
			);
			gamePhysics.physicsEngine.world.map.setPosY(
				(gamePhysics.display.getHeight()/2) -
				(gamePhysics.physicsEngine.world.map.size()/2)
			);
		}

		System.exit(0);
	}

}
