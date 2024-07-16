package com.game.engine.hud.gameover;
import com.game.engine.hud.Hud;
import com.game.engine.view.Coords;
import com.game.engine.view.Display;
/** GameOver class */
public class GameOver extends Hud{
    /** Background of the hud */
    GameOverBackground gameOverBackground;
    GOQuitButton goQuitButton, goQuitButtonHint, goRestartButton, goRestartButtonHint;
    /** Constructs a GameOver hud 
     * @param display
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public GameOver(Display display, int x, int y, int width, int height) {
        super(display, x, y, width, height);
        Coords origin = new Coords(x, y);
        try{
            gameOverBackground = new GameOverBackground(origin,x, y, width, height);
            goQuitButton = new GOQuitButton(origin,width/2 - 32, 50, 64, 50, "assets/misc/Exit.png", "assets/misc/Exit_active.png");
            goQuitButtonHint = new GOQuitButton(origin,width/2 - 32 + 80, 50, 64, 50, "assets/misc/ArcadeController5.png", "assets/misc/ArcadeController5.png");
            
            goRestartButton = new GOQuitButton(origin,width/2 - 32, 120, 64, 50, "assets/misc/Back.png", "assets/misc/Back_active.png");
            goRestartButtonHint = new GOQuitButton(origin,width/2 - 32 + 80, 120, 64, 50, "assets/misc/ArcadeController6.png", "assets/misc/ArcadeController6.png");
            
            addElement(gameOverBackground);
            addElement(goQuitButton);
            addElement(goQuitButtonHint);
            addElement(goRestartButton);
            addElement(goRestartButtonHint);
        }
        catch(Exception e) {}
    }
}