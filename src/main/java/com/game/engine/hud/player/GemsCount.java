package com.game.engine.hud.player;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;

import com.game.engine.hud.HudElement;
import com.game.engine.physics.Player;
import com.game.engine.view.Coords;
import com.game.engine.view.Sprite;
/** GemsCount class */
public class GemsCount extends HudElement{
    /** Splash of the background */
    Sprite gem;
    /** The player */
    Player player;
    /** Constructs the gems count hud
     * @param player
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public GemsCount(Coords origin, Player player, int x, int y, int width, int height) {
        super(origin, x, y, width, height);
        this.player = player;
        try{
            gem = new Sprite(16, 16, 2, ImageIO.read(
                new File(
                    "assets/misc/gem.png" 
                )
            ));
        }
        catch(Exception e) {}
    }

    @Override
    public void draw(Graphics g) {
        Font temp = g.getFont();
        g.setFont(temp.deriveFont(Font.BOLD).deriveFont(20F));

        gem.draw(g, getX(), getY());

        String str = "" + player.getGems();
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int xStr = getX() - metrics.stringWidth(str);

        g.drawString(str, xStr - 5, getY() + (int)(gem.getSizeY()/1.3));
        g.setFont(temp);
    }

    @Override
    public void onClick() {}
    
}
