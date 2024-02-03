package com.game.engine.hud.player;

import java.awt.Font;
import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;

import com.game.engine.controller.KeyController;
import com.game.engine.hud.HudElement;
import com.game.engine.hud.shop.Shop;
import com.game.engine.view.Coords;
import com.game.engine.view.Sprite;
/** OpenShop class */
public class OpenShop extends HudElement{
    /** Splash of the background */
    Sprite shopSprite;
    /** The shop */
    Shop shop;
    /** Constructs the shop button 
     * @param shop
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public OpenShop(Coords origin, Shop shop, int x, int y, int width, int height) {
        super(origin, x, y, width, height);
        this.shop = shop;
        try{
            shopSprite = new Sprite(16, 16, 2, ImageIO.read(
                new File(
                    "assets/misc/shop.png"
                )
            ));
        }
        catch(Exception e) {}
    }

    @Override
    public void draw(Graphics g) {
        Font temp = g.getFont();
        g.setFont(temp.deriveFont(Font.BOLD).deriveFont(20F));

        shopSprite.draw(g, getX(), getY());
        g.drawString("Shop", getX() - shopSprite.getSizeX()*2, getY() + (int)(shopSprite.getSizeY()/1.3));
        g.setFont(temp);
    }

    @Override
    public void onClick() {
        KeyController.closeShop = !KeyController.closeShop;
        shop.setIsShown(true);
        shop.setInteractable(true);
        KeyController.canMove = !KeyController.canMove;
    }
    
}
