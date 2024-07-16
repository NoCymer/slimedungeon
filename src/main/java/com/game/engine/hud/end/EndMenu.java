package com.game.engine.hud.end;

import com.game.engine.hud.Hud;
import com.game.engine.view.Coords;
import com.game.engine.view.Display;
/** GameOver class */
public class EndMenu extends Hud{
    /** Background of the hud */
    EndMenuBackground endMenuBackground;
    /** Quit button */
    EMQuitButton emQuitButton, emQuitButtonHint, emRestartButton, emRestartButtonHint;
    /** Constructs a GameOver hud 
     * @param display
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public EndMenu(Display display, int x, int y, int width, int height) {
        super(display, x, y, width, height);
        Coords origin = new Coords(x, y);
        try{
            endMenuBackground = new EndMenuBackground(origin,x, y, width, height);
            emQuitButton = new EMQuitButton(origin,width/2 - 32, 50, 64, 50, "assets/misc/Exit.png", "assets/misc/Exit_active.png");
            emQuitButtonHint = new EMQuitButton(origin,width/2 - 32 + 80, 50, 64, 50, "assets/misc/ArcadeController5.png", "assets/misc/ArcadeController5.png");
            
            emRestartButton = new EMQuitButton(origin,width/2 - 32, 120, 64, 50, "assets/misc/Back.png", "assets/misc/Back_active.png");
            emRestartButtonHint = new EMQuitButton(origin,width/2 - 32 + 80, 120, 64, 50, "assets/misc/ArcadeController6.png", "assets/misc/ArcadeController6.png");
            addElement(endMenuBackground);
            addElement(emQuitButton);
            addElement(emQuitButtonHint);
            addElement(emRestartButton);
            addElement(emRestartButtonHint);
        }
        catch(Exception e) {}
    }
}