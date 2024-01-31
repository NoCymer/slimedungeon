package com.game.engine.main;

import com.game.engine.controller.JoystickController;
import com.game.engine.controller.KeyboardController;

public class MainLoop {
	/** Frames per secons */
	public int fps = 60;
	/** The game physics */
	public GamePhysics jeuPhysique;
	/** Keyboard controller */
	public KeyboardController cClavier;
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

		// ControleurClavier cClavier=new ControleurClavier(true);
		jeuPhysique.display.addKeyListener(cClavier);
		
		jeuPhysique.physicsEngine.world.c = cClavier.c;

		long dureeBoucle = 1000000 / fps;

		Thread.sleep(1000);
		jeuPhysique.display.requestFocusInWindow();

		long beforeTime = System.nanoTime();
		
		while (!KeyboardController.fin) {
			cJoystick.checkForInput();
			jeuPhysique.update();
			jeuPhysique.render();

			while (System.nanoTime() - beforeTime - dureeBoucle * 1000L < 0) {}

			beforeTime = System.nanoTime();

			// Changes the world's map's position according to the window height
			jeuPhysique.physicsEngine.world.map.setPosX(
				(jeuPhysique.display.getWidth()/2) -
				(jeuPhysique.physicsEngine.world.map.size()/2)
			);
			jeuPhysique.physicsEngine.world.map.setPosY(
				(jeuPhysique.display.getHeight()/2) -
				(jeuPhysique.physicsEngine.world.map.size()/2)
			);
		}

		System.exit(0);
	}

}
