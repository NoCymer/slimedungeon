package com.game.engine.hud.shop;

import java.awt.Graphics;

import com.game.engine.generation.Map;
import com.game.engine.hud.Hud;
import com.game.engine.physics.Player;
import com.game.engine.view.Coords;
import com.game.engine.view.Display;
/** Shop class */
public class Shop extends Hud{
	/** Constructs a shop 
	 * @param display
	 * @param player
	 * @param x
     * @param y
     * @param width
     * @param height
	*/
	public Shop(Display display, Player player, int x, int y, int width, int height, Map map) {
		super(display, 0,x, y, width, height);
		Coords origin = new Coords(x,y);
		double scale = 1.5;
		int offsetX = 40;
		int offsetY = 15;
		try{
			addElement(new ShopBackground(origin, 0, 0, width, height,map));
			addElement(new AttackDisplay(origin, player, 10, (int)(260*scale), width - 50, 50));
			addElement(new AddAttackButton(origin, player, 50 + 10 * (10+ (((width - 50)/2) / 10)), (int)(255*scale),  50, 50,"assets/misc/gem.png"));
			addElement(new AddAttackButton(origin, player, 50 + 10 * (10+ (((width - 50)/2) / 10)) + offsetX, (int)(255*scale) + offsetY,  50, 50,"assets/misc/ArcadeController1.png"));
			addElement(new DefenceDisplay(origin, player, 10, (int)(200*scale), width - 50, 50));
			addElement(new AddDefenceButton(origin, player, 50 + 10 * (10+ (((width - 50)/2) / 10)), (int)(195*scale),  50, 50,"assets/misc/gem.png"));
			addElement(new AddDefenceButton(origin, player, 50 + 10 * (10+ (((width - 50)/2) / 10)) + offsetX, (int)(195*scale) + offsetY,  50, 50,"assets/misc/ArcadeController2.png"));
			addElement(new HealthDisplay(origin, player, 10, (int)(140*scale), width - 50, 50));
			addElement(new AddHealthButton(origin, player, 50 + 10* (10+ (((width - 50)/2) / 10)), (int)(135*scale),  50, 50,"assets/misc/gem.png"));
			addElement(new AddHealthButton(origin, player, 50 + 10* (10+ (((width - 50)/2) / 10)) + offsetX, (int)(135*scale) + offsetY,  50, 50,"assets/misc/ArcadeController3.png"));
			addElement(new HealButton(origin, player, 25, (int)(50*scale),  64, 64,"assets/misc/HealthVial.png"));
			addElement(new HealButton(origin, player, 25 + offsetX + 30, (int)(50*scale),  64, 64,"assets/misc/ArcadeController4.png"));
			addElement(new HealLabel(origin,player, 30, (int)(10*scale*2),  64, 32));
			addElement(new CloseButton(origin, this, width/2-32, 30,  64, 50,"assets/misc/Back_active.png","assets/misc/Back.png"));
			addElement(new CloseButton(origin, this, width/2-32 + offsetX *2, 30,  64, 50,"assets/misc/ArcadeController5.png","assets/misc/ArcadeController5.png"));
		}
		catch(Exception e) {}
	}
	/** Draws the shop 
	 * @param g
	*/
    public void draw(Graphics g) {
        super.draw(g);
        int topInset = getDisplay().getFrame().getInsets().top;
        this.setOffsetY(topInset);
    }
}
