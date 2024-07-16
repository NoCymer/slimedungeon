package com.game.engine.hud.menu;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.game.engine.controller.InputController;
import com.game.engine.hud.Button;
import com.game.engine.view.Coords;
import com.game.engine.view.Sprite;
/** QuitButton class */
public class QuitButton extends Button {

    /** Constructs a quit button 
     * @param x 
     * @param y
     * @param width
     * @param height
    */
    public QuitButton(Coords origin, int x, int y, int width, int height, String imgUrl, String umgActiveUrl) throws IOException{
        super(
            origin,
            new Sprite(
                x,
                y,
                2,
                ImageIO.read(
                    new File(imgUrl)
                )
                
            ),
            new Sprite(
                x,
                y,
                2,
                ImageIO.read(
                    new File(umgActiveUrl)
                )
                
            ), x, y, width, height
        );
    }

    @Override
    public void onClick() {
        InputController.closeGame = true;
    }
}
