package com.game.engine.controller.bindings;

import com.game.engine.controller.InputController;
import com.game.engine.controller.KeyBinding;

public class BindingNextDialog extends KeyBinding{
    @Override
    public void onPressed() {
        InputController.nextDialog = true;
    }
    @Override
    public void onReleased() {
        InputController.nextDialog = false;
    }
    
}
