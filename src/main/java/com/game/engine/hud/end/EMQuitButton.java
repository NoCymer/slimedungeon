package com.game.engine.hud.end;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.game.engine.controller.KeyController;
import com.game.engine.hud.Button;
import com.game.engine.view.Coords;
import com.game.engine.view.Sprite;
/** GOQuitButton class */
public class EMQuitButton extends Button {

    /** Constructs a game over exit button 
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public EMQuitButton(Coords origin, int x, int y, int width, int height) throws IOException{
        super(
            origin,
            new Sprite(
                x,
                y,
                2,
                ImageIO.read(
                    new File("assets/misc/Exit.png")
                )
                
            ),
            new Sprite(
                x,
                y,
                2,
                ImageIO.read(
                    new File("assets/misc/Exit_active.png")
                )
                
            ), x, y, width, height
        );
    }

    @Override
    public void onClick() {
        KeyController.closeGame = true;
    }
}
