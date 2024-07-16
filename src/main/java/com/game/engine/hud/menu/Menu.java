package com.game.engine.hud.menu;

import com.game.engine.hud.Hud;
import com.game.engine.hud.player.PlayerHud;
import com.game.engine.view.Coords;
import com.game.engine.view.Display;
/** Menu class */
public class Menu extends Hud{
	/** Player hud */
	private PlayerHud pHud;
	/** Background of the menu */
	MenuBackground menuBackground;
	/** Start button */
	StartButton startButton, startButtonHint;
	/** Quit button */
	QuitButton quitButton, quitButtonHint;
	/** Constructs a menu 
	 * @param display
	 * @param pHud
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	*/
	public Menu(Display display, PlayerHud pHud, int x, int y, int width, int height) {
		super(display, x, y, width, height);
		this.pHud = pHud;
		Coords origin = new Coords(x, y);
		try{
			menuBackground = new MenuBackground(origin,x, y, width, height);
            quitButton = new QuitButton(origin,width/2 - 32, 50, 64, 50, "assets/misc/Exit.png", "assets/misc/Exit_active.png");
            quitButtonHint = new QuitButton(origin,width/2 - 32 + 80, 50, 64, 50, "assets/misc/ArcadeController5.png", "assets/misc/ArcadeController5.png");
            
            startButton = new StartButton(origin,this,width/2 - 32, 120, 64, 50, "assets/misc/Play.png", "assets/misc/Play_active.png");
            startButtonHint = new StartButton(origin,this,width/2 - 32 + 80, 120, 64, 50, "assets/misc/ArcadeController6.png", "assets/misc/ArcadeController6.png");
            

			addElement(menuBackground);
            addElement(quitButton);
            addElement(quitButtonHint);
            addElement(startButton);
            addElement(startButtonHint);
		}
		catch(Exception e) {}
	}
	/** Returns the player hud 
	 * @return
	*/
	public PlayerHud getpHud() {
		return pHud;
	}
	
}
