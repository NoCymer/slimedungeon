package com.game.engine.controller.bindings;

import com.game.engine.controller.KeyBinding;
import com.game.engine.shop.ShopManager;

public class BindingCloseShop extends KeyBinding{
    @Override
    public void onPressed() { }
    
    @Override
    public void onReleased() {
        ShopManager.instance().closeShop();
    }
}
