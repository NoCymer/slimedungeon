package com.game.engine.view;

import java.awt.Graphics;
import java.io.IOException;
import java.util.HashMap;

import com.game.engine.physics.Player;
import com.game.engine.tiles.Atlas;

/** PlayerSprites class */
public class PlayerSprites extends Sprites {
	/** The player */
	Player player;
	/** The sprite path */
	String imageFile = "assets/char/player.png";
	/** The atlas */
	Atlas spriteAtlas;
	/**
	 * Constructs a player sprite
	 * @param b
	 * @throws IOException
	 */
	public PlayerSprites(Player b) throws IOException {
		this.player = b;
		spriteAtlas = new Atlas(imageFile, 16, 4, 2, 2);
		activity = "down";
		sprites = new HashMap<String, Sprite>();
		sprites.put("fixe", spriteAtlas.get(1));
		sprites.put("fixe0", spriteAtlas.get(1));
		sprites.put("fixe1", spriteAtlas.get(2));
		sprites.put("fixe2", spriteAtlas.get(2));
		sprites.put("fixe3", spriteAtlas.get(2));
		sprites.put("fixe4", spriteAtlas.get(2));
		sprites.put("fixe5", spriteAtlas.get(1));
		sprites.put("fixe6", spriteAtlas.get(1));

		sprites.put("right0", spriteAtlas.get(5));
		sprites.put("right1", spriteAtlas.get(6));
		sprites.put("right2", spriteAtlas.get(5));

		sprites.put("left0", spriteAtlas.get(3));
		sprites.put("left1", spriteAtlas.get(4));
		sprites.put("left2", spriteAtlas.get(3));

		sprites.put("down0", spriteAtlas.get(1));
		sprites.put("down1", spriteAtlas.get(2));
		sprites.put("down2", spriteAtlas.get(1));
		
		sprites.put("down-right0", spriteAtlas.get(1));
		sprites.put("down-right1", spriteAtlas.get(2));
		sprites.put("down-right2", spriteAtlas.get(1));
		
		sprites.put("down-left0", spriteAtlas.get(1));
		sprites.put("down-left1", spriteAtlas.get(2));
		sprites.put("down-left2", spriteAtlas.get(1));
		
		sprites.put("up0", spriteAtlas.get(7));
		sprites.put("up1", spriteAtlas.get(8));
		sprites.put("up2", spriteAtlas.get(7));

		sprites.put("up-right0", spriteAtlas.get(7));
		sprites.put("up-right1", spriteAtlas.get(8));
		sprites.put("up-right2", spriteAtlas.get(7));

		sprites.put("up-left0", spriteAtlas.get(7));
		sprites.put("up-left1", spriteAtlas.get(8));
		sprites.put("up-left2", spriteAtlas.get(7));
	}

	/** Draws the player sprite 
	 * @param x
	 * @param y
	 * @param g
	*/
	public void draw(int x, int y, Graphics g) {
		Sprite s = sprites.get("fixe");
		if (s == null)
			s = sprites.get("erreur");
		s = sprites.get(chain());
		s.draw(g, x, y);
	}

	/**
	 *
	 */
	@Override
	public void animate() {
		iteration++;

		int itCount = 2;
		if(player.vx != 0 || player.vy !=0) {
			if (iteration > itCount) {
				num++;
				iteration = 0;
			}
	
			if (num > itCount)
				num = 0;
		} else {
			num = 0;
			iteration = 0;
		}
	}
}
