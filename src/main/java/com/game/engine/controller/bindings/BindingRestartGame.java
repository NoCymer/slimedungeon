package com.game.engine.controller.bindings;

import com.game.Game;
import com.game.engine.controller.KeyBinding;
import com.game.engine.hud.menu.Menu;
import com.game.engine.main.MainLoop;
import com.game.engine.physics.PhysicsEngine;
import com.game.engine.view.Display;

public class BindingRestartGame extends KeyBinding{
    @Override
    public void onPressed() {
        try{
            if(Game.world.huds.get("gameOver").isShown() || Game.world.huds.get("endMenu").isShown()){
                MainLoop.canRender = false;
                Display.frame.dispose();
                Game.startGame();
                Game.gameLoop.addListeners();
                MainLoop.canRender = true;
                PhysicsEngine.endGame = false;
            }
            else if(Game.world.huds.get("menu").isShown()){
                Game.world.huds.get("menu").setInteractable(false);   
                Game.world.huds.get("menu").setIsShown(false);
                ((Menu)Game.world.huds.get("menu")).getpHud().setInteractable(true);
                ((Menu)Game.world.huds.get("menu")).getpHud().setIsShown(true);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onReleased() {

    }
}
