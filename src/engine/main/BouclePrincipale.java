package engine.main;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JFrame;

import engine.controller.KeyboardController;

public class BouclePrincipale {

	public String nom = "Mon super Jeu";
	public int fps = 60;
	public GamePhysics jeuPhysique;
	// creation du controleur
	public KeyboardController cClavier;

	public void setName(String nom) {
		this.nom = nom;
	}

	public void setFPS(int fps) {
		this.fps = fps;
	}

	public BouclePrincipale() throws Exception {
		// creation du jeu
		// jeuPhysique = new JeuPhysique();
	}

	public void lanceBouclePrincipale() throws Exception {

		// ControleurClavier cClavier=new ControleurClavier(true);
		jeuPhysique.display.addKeyListener(cClavier);
		// mettre l'acces au controleur dans monde
		jeuPhysique.physicsEngine.world.c = cClavier.c;

		// afficher
		System.out.println("\n\n**************************************************");
		System.out.println("*  " + nom);
		System.out.println("*                                                 *");
		System.out.println("*** touche 'Q' pour arreter jeu                  ***");
		System.out.println("****************************************************");
		System.out.println("\n\n");

		// fps
		long dureeBoucle = 1000000 / fps;
		System.out.println(" ---> duree d'une boucle " + dureeBoucle / 1000.);

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
			while (System.nanoTime() - beforeTime - dureeBoucle * 1000L < 0) {
			}

			beforeTime = System.nanoTime();


			JFrame frame = jeuPhysique.display.getFrame();
			Insets is = frame.getInsets();

			// Changes the world's map's position according to the window height
			jeuPhysique.physicsEngine.world.map.setPosX(
				(jeuPhysique.display.getWidth()/2) -
				(jeuPhysique.physicsEngine.world.map.size()/2)
			);
			jeuPhysique.physicsEngine.world.map.setPosY(
				(jeuPhysique.display.getHeight()/2) -
				(jeuPhysique.physicsEngine.world.map.size()/2) + is.top
			);
		}
		long l2 = System.currentTimeMillis();

		// statistiques
		System.out.println("\n\n\n************************\n");
		System.out.println("Iterations = " + n);
		System.out.println("FPS = " + (n * 1000.0 / (l2 - l)));
		System.out.println("\n************************");

		System.exit(0);
	}

}
