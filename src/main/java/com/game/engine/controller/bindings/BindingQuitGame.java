package com.game.engine.controller.bindings;

import com.game.engine.controller.InputController;
import com.game.engine.controller.KeyBinding;

public class BindingQuitGame extends KeyBinding{
    @Override
    public void onPressed() {
        InputController.closeGame = true;
    }
    @Override
    public void onReleased() {
        InputController.closeGame = false;
    }
}
