package com.game.engine.hud.shop;

import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;

import com.game.engine.generation.Map;
import com.game.engine.hud.HudElement;
import com.game.engine.view.Coords;
import com.game.engine.view.Sprite;

/** ShopBackground class */
public class ShopBackground extends HudElement{
    /** The map */
    Map map;
    /** Constructs a shop background 
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public ShopBackground(Coords origin, int x, int y, int width, int height, Map map) {
        super(origin, x, y, width, height);
        this.map = map;
    }

    @Override
    public void draw(Graphics g) {
        try{
            new Sprite(
                getWidth(),
                getHeight(),
                1.5,
                ImageIO.read(
                    new File("assets/misc/MenuBackground.png")
                )
                
            ).draw(g, getX(), getY());
        } catch (Exception e) { }
    }

    @Override
    public void onClick() {}
    
}
