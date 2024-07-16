package com.game.engine.hud.npc;

import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;

import com.game.engine.dialog.DialogController;
import com.game.engine.hud.HudElement;
import com.game.engine.view.Coords;
import com.game.engine.view.Sprite;
/** DialogDisplay class */
public class DialogDisplay extends HudElement{
    /** Constructs a dialog display 
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public DialogDisplay(Coords origin, int x, int y, int width, int height) {
        super(origin, x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        try{
            new Sprite(
                getX(),
                getY(),
                1.5,
                ImageIO.read(
                    new File("assets/misc/text_bg.png")
                )
            ).draw(g, getX(), getY());
        } catch (Exception e) { }
        DialogController.getCurrentDialog().draw(g, getX(), getY());
    }

    @Override
    public void onClick() {
        DialogController.getCurrentDialog().nextLine();
    }
    
}
