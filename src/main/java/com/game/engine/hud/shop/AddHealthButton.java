package com.game.engine.hud.shop;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.game.engine.hud.Button;
import com.game.engine.physics.Player;
import com.game.engine.shop.HealthBooster;
import com.game.engine.shop.ShopManager;
import com.game.engine.view.Coords;
import com.game.engine.view.Sprite;
/** AddHealthButton class */
public class AddHealthButton extends Button {
    /** The player */
    Player player;
    /** Constructs the button to buy health stats 
     * @param player
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public AddHealthButton(Coords origin, Player player, int x, int y, int width, int height, String path) throws IOException{
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
                    new File(path)
                )
                
            ), x, y, width, height
        );
        this.player = player;
    }

    @Override
    public void onClick() {
        ShopManager.instance().tryBuyItem(player, new HealthBooster());
    }
}
