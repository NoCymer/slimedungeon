package com.game.engine.hud.shop;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.game.engine.hud.Button;
import com.game.engine.shop.ShopManager;
import com.game.engine.view.Coords;
import com.game.engine.view.Sprite;
/** CloseButton class */
public class CloseButton extends Button {
    /** The shop */
    Shop shop;
    /** Constructs a close button 
     * @param shop
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public CloseButton(Coords origin, Shop shop, int x, int y, int width, int height, String activePath, String path) throws IOException{
        super(
            origin,
            new Sprite(
                x,
                y,
                2,
                ImageIO.read(
                    new File(path)
                )
                
            ),
            new Sprite(
                x,
                y,
                2,
                ImageIO.read(
                    new File(activePath)
                )
                
            ), x, y, width, height
        );
        this.shop = shop;
    }

    @Override
    public void onClick() {
        ShopManager.instance().closeShop();
    }
}
