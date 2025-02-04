package com.game.engine.physics;

import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;

import com.game.engine.view.CoordinateSystem;
import com.game.engine.view.Coords;
import com.game.engine.view.Sprite;

/** Gem class */
public class Gem extends Item {
    
    /**
     * Creates a new gem item
     * @param w
     * @param px
     * @param py
     */
    public Gem(World w, int px, int py){
        super(w);
        this.px = px;
        this.py = py;
    }
    /** Pickup logic of the item */
    public void pickup(){
        world.player.addGems(5);
        world.map.activeRoom.items.remove(this);
    }
    
    @Override
	public void draw(Graphics g) {
		Coords coords = CoordinateSystem.changeCS(this, world.map.getPosX(), world.map.getPosY());
        try {
            Sprite s = new Sprite(16, 16, 2, ImageIO.read(
                new File("assets/misc/gem.png")
            ));
            s.draw(g, coords.getX(), coords.getY());
        } catch (Exception e) { }
	}
}
