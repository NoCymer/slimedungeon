package com.game.engine.hud.shop;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.game.engine.hud.Button;
import com.game.engine.physics.Player;
import com.game.engine.view.Coords;
import com.game.engine.view.Sprite;
/** HealButton class */
public class HealButton extends Button {
    /** The player */
    Player player;
    /** Constructs the healing button 
     * @param player
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public HealButton(Coords origin, Player player, int x, int y, int width, int height, String path) throws IOException{
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
        int vie = player.getHealth();
        int maxVie = player.getMaxHealth();
        if (player.getGems() >= 5){
            if(vie +40 <= maxVie) {
                player.setHealth(vie + 40);
                player.addGems(-5);
            }
            else if(vie+40 < maxVie + 40){
                player.setHealth(maxVie);
                player.addGems(-5);
            }
        }
    }
}
