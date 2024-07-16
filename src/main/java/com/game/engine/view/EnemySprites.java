package com.game.engine.view;

import java.io.IOException;
import java.util.HashMap;

import com.game.engine.generation.MapGenerator;
import com.game.engine.physics.Entity;
import com.game.engine.tiles.Atlas;

public class EnemySprites extends Sprites {
	/** The entity */
	Entity entity;
	/** The sprite path */
	String imageFile = "assets/char/slime.png";
	/** The atlas */
	Atlas spriteAtlas;

	/**
	 * Constructs an enemy sprite
	 * @param b
	 * @throws IOException
	 */
	public EnemySprites(Entity b) throws IOException {
		this.entity = b;
		spriteAtlas = new Atlas(imageFile, MapGenerator.TILE_SIZE, 4, 2, MapGenerator.TILE_SCALE_FACTOR);
		activity = "down";
		sprites = new HashMap<String, Sprite>();
		sprites.put("fixe", spriteAtlas.get(0));
		sprites.put("fixe0", spriteAtlas.get(1));
		sprites.put("fixe1", spriteAtlas.get(1));
		sprites.put("fixe2", spriteAtlas.get(0));

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

	@Override
	public void animate() {
		iteration++;

		int itCount = 2;
		if(entity.vx != 0 || entity.vy !=0) {
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
