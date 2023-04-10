package engine.view;

import java.awt.*;
import java.awt.image.BufferStrategy;

import javax.swing.*;

import engine.physics.*;
import engine.hud.Hud;

public class Display extends JPanel {

	// le monde a affcher
	public World m;

	// l'afficheur de Decor
	public Scene decor;

	// double buffering
	public BufferStrategy bs;

	// creation d'un afficheur

	/**
	 *
	 * @param monde
	 */
	public Display(World monde, Scene decor) {
		JFrame f = new JFrame();

		this.decor = decor;

		setPreferredSize(new Dimension(decor.size(),decor.size()));

		// setPreferredSize(new Dimension(800, 800));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(this);
		f.pack();
		f.setVisible(true);

		// ajouter pour eviter les repaint
		f.setIgnoreRepaint(true);

		// double buffering
		f.createBufferStrategy(2);
		bs = f.getBufferStrategy();
		this.setIgnoreRepaint(true);

		this.m = monde;
	}

	// permet de faire un affichage

	// Renvoie le decor
	public Scene getDecor() {
		return decor;
	}

	public void setDecor(Scene decor) {
		this.decor = decor;
	}

	/**
	 *
	 */
	public void render() {
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();

		g.setColor(Color.black);

		// Affiche le decor
		decor.draw(g);

		// affiche les objets
		for (PhysicalObject obj : m.objects) {
			obj.draw(g);
		}

		// affiche les monstres
		for (Enemy enemy : m.enemies) {
			enemy.draw(g);
		}

		// draws npcs
		for (NPC npc : m.npcs) {
			npc.draw(g);
		}

		// affiche la balle
		Player b = m.player;
		b.draw(g);

		for (Hud hud : m.huds.values()) {
			hud.draw(g);
		}

		bs.show();
		Toolkit.getDefaultToolkit().sync();
		g.clearRect(0, 0, decor.size(), decor.size());
		g.dispose();

	}

}
