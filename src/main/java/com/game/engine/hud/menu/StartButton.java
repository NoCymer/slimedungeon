package com.game.engine.hud.menu;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.game.engine.hud.Button;
import com.game.engine.view.Coords;
import com.game.engine.view.Sprite;
/** StartButton class */
public class StartButton extends Button {
    /** The menu */
    private Menu menu;
    /** Constructs a start button 
     * @param menu
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public StartButton(Coords origin, Menu menu, int x, int y, int width, int height, String imgUrl, String umgActiveUrl) throws IOException{
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
        this.menu = menu;
    }

    @Override
    public void onClick() {
        menu.setInteractable(false);   
        menu.setIsShown(false);
        menu.getpHud().setInteractable(true);
        menu.getpHud().setIsShown(true);
    }
}
