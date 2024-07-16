package com.game.engine.controller.bindings;

import com.game.engine.controller.InputController;
import com.game.engine.controller.KeyBinding;

public class BindingOpenMap extends KeyBinding{
    @Override
    public void onPressed() {
        InputController.map = true;
    }
    @Override
    public void onReleased() {
        InputController.map = false;
    }
    
}
