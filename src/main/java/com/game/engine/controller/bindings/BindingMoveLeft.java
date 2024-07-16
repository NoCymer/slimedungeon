package com.game.engine.controller.bindings;

import com.game.engine.controller.InputController;
import com.game.engine.controller.KeyBinding;

public class BindingMoveLeft extends KeyBinding {
    @Override
    public void onPressed() {
        if(InputController.canMove) InputController.c.x = -1;
    }
    @Override
    public void onReleased() {
        if(InputController.c.x<0) InputController.c.x = 0;
    }
}
