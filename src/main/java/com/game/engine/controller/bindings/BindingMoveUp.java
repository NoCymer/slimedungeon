package com.game.engine.controller.bindings;

import com.game.engine.controller.InputController;
import com.game.engine.controller.KeyBinding;

public class BindingMoveUp extends KeyBinding{
    @Override
    public void onPressed() {
        if(InputController.canMove) InputController.c.y = -1;
    }
    @Override
    public void onReleased() {
        if(InputController.c.y<0) InputController.c.y = 0;
    }
}
