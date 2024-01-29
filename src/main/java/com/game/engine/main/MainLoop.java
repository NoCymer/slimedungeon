package com.game.engine.main;

import com.game.engine.controller.KeyboardController;

public class MainLoop {
	/** Frames per secons */
	public int fps = 60;
	/** The game physics */
	public GamePhysics jeuPhysique;
	/** Keyboard controller */
	public KeyboardController cClavier;
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
		// mettre l'acces au controleur dans monde
		jeuPhysique.physicsEngine.world.c = cClavier.c;

		// fps
		long dureeBoucle = 1000000 / fps;

		// lancement
		Thread.sleep(1000);
		jeuPhysique.display.requestFocusInWindow();

		// boucle
		long beforeTime = System.nanoTime();
		long l = System.currentTimeMillis();
		// nombre iterations
		int n = 0;
		while (!KeyboardController.fin) {
			n++;
			jeuPhysique.update();
			jeuPhysique.render();

			// apres le render en nanos
			// long timafter = System.nanoTime();

			// sleep en millisecond
			// System.out.println(dureeBoucle);
			// System.out.println(beforeTime);
			// Thread.sleep((dureeBoucle - (timafter/ 1000 - beforeTime/ 1000))/1000);
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
