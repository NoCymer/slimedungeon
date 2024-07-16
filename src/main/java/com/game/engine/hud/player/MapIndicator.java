package com.game.engine.hud.player;

import java.awt.Font;
import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;

import com.game.engine.hud.HudElement;
import com.game.engine.view.Coords;
import com.game.engine.view.Sprite;
/** OpenShop class */
public class MapIndicator extends HudElement{
    /** Splash of the background */
    Sprite mapIndicatorSprite;
    /** Show string or not */
    boolean showString;
    /** Constructs the shop button 
     * @param shop
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public MapIndicator(Coords origin, int x, int y, int width, int height, String path, boolean showString) {
        super(origin, x, y, width, height);
        this.showString = showString;
        try{
            mapIndicatorSprite = new Sprite(16, 16, 2, ImageIO.read(
                new File(
                    path
                )
            ));
        }
        catch(Exception e) {}
    }

    @Override
    public void draw(Graphics g) {
        Font temp = g.getFont();
        g.setFont(temp.deriveFont(Font.BOLD).deriveFont(20F));

        mapIndicatorSprite.draw(g, getX(), getY());
        if(showString) g.drawString("Map", getX() - mapIndicatorSprite.getSizeX()*2, getY() + (int)(mapIndicatorSprite.getSizeY()/1.3));
        g.setFont(temp);
    }

    @Override
    public void onClick() {
    }
    
}
