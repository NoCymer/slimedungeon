package com.game.engine.controller.bindings;

import com.game.engine.controller.AxisBinding;
import com.game.engine.controller.InputController;

public class BindingMoveXAxis extends AxisBinding{
    @Override
    public void onChange(float value) {
        InputController.c.x = value;
    }
}
