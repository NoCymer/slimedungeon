package com.game.engine.hud.map;

import java.awt.Graphics;
import com.game.engine.generation.Map;
import com.game.engine.hud.Hud;
import com.game.engine.view.Coords;
import com.game.engine.view.Display;
/** MapHud class */
public class MapHud extends Hud{

	/** Constructs a map hud 
	 * @param display
	 * @param map
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	*/
	public MapHud(Display display, Map map, int x, int y, int width, int height) {
		super(display, 0,x, y, width, height);
		Coords origin = new Coords(0, 0);
		try{
			addElement(new MapHudBackground(origin,map, x, y, width, height));
		}
		catch(Exception e) {}
	}
	/** Draws the hud 
	 * @param g
	*/
    public void draw(Graphics g) {
        super.draw(g);
        int topInset = getDisplay().getFrame().getInsets().top;
        this.setOffsetY(topInset);
    }
}
