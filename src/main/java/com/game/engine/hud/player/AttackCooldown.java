package com.game.engine.hud.player;

import java.awt.Graphics;

import com.game.engine.hud.HudElement;
import com.game.engine.physics.Player;
import com.game.engine.tiles.Atlas;
import com.game.engine.view.Coords;

public class AttackCooldown extends HudElement{
    
    /** The entity */
    private Player player; 

    /** Atlas */
    private Atlas boxingGloveAtlas;

    /** Constructs an attack cooldown 
     * @param origin
     * @param player
     * @param x
     * @param y 
     * @param width
     * @param height
    */
    public AttackCooldown(Coords origin, Player player, int x, int y, int width, int height) {
        super(origin, x, y, width, height);
        this.player = player;
        try{
		    boxingGloveAtlas = new Atlas("assets/misc/Box_Glove.png", 16, 1, 11, 4);
        }
        catch(Exception e){}
    }

    @Override
    public void draw(Graphics g) {
        
		int spriteID = 1 + (player.getCooldownLeft() * 10 / player.getAttackCooldown());
        boxingGloveAtlas.get(spriteID).draw(g, getX(), getY());
    }
    
    @Override
    public void onClick() {  }
    
}
