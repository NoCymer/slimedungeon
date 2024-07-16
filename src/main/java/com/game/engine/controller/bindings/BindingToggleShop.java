package com.game.engine.controller.bindings;

import com.game.engine.controller.KeyBinding;
import com.game.engine.shop.ShopManager;

public class BindingToggleShop extends KeyBinding{
    @Override
    public void onPressed() {
        if(!ShopManager.instance().isShopOpened()) {
            ShopManager.instance().openShop();
        }
        else {
            ShopManager.instance().closeShop();
        }
    }
    @Override
    public void onReleased() { }
}
