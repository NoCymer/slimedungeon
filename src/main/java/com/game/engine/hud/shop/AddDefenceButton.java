package com.game.engine.hud.shop;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.game.engine.hud.Button;
import com.game.engine.physics.Player;
import com.game.engine.view.Coords;
import com.game.engine.view.Sprite;
/** AddDefenceButton class */
public class AddDefenceButton extends Button {
    /** The player */
    Player player;
    /** Constructs the button to buy defence stats 
     * @param player
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public AddDefenceButton(Coords origin, Player player, int x, int y, int width, int height) throws IOException{
        super(
            origin,
            new Sprite(
                x,
                y,
                2,
                ImageIO.read(
                    new File("assets/misc/gem.png")
                )
                
            ),
            new Sprite(
                x,
                y,
                2,
                ImageIO.read(
                    new File("assets/misc/gem.png")
                )
                
            ), x, y, width, height
        );
        this.player = player;
    }

    @Override
    public void onClick() {
        int curr = player.getDefenceMultiplicator()+1;
        if(curr <= 10 && player.getGems() >= 5) {
            player.setDefenceMultiplicator(curr);
            player.addgems(-5);
        }
    }
}
