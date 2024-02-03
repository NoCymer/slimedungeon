package com.game.engine.main;

import com.game.engine.controller.JoystickController;
import com.game.engine.controller.KeyController;

public class MainLoop {
	/** Frames per secons */
	public int fps = 60;
	/** The game physics */
	public GamePhysics gamePhysics;
	/** Keyboard controller */
	public KeyController cKey;
	/** Joystick controller */
	public JoystickController cJoystick;
	/** Updates the fps 
	 * @param fps
	*/
	public void setFPS(int fps) {
		this.fps = fps;
	}
	/** Runs the game loop */
	public void start() throws Exception {

		gamePhysics.display.addKeyListener(cKey);
		
		gamePhysics.physicsEngine.world.c = cKey.c;

		long loopDuration = 1000000 / fps;

		Thread.sleep(1000);
		gamePhysics.display.requestFocusInWindow();

		long beforeTime = System.nanoTime();
		
		while (!KeyController.fin) {
			cJoystick.checkForInput();
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
