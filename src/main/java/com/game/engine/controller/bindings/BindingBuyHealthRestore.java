package com.game.engine.controller.bindings;

import com.game.engine.controller.InputController;
import com.game.engine.controller.KeyBinding;
import com.game.engine.shop.HealthRestore;
import com.game.engine.shop.ShopManager;

public class BindingBuyHealthRestore extends KeyBinding{
    private boolean buyHealthRestore = false;
    @Override
    public void onPressed() {
        if(ShopManager.instance().isShopOpened()  && !buyHealthRestore) {
            buyHealthRestore = true;
            ShopManager.instance().tryBuyItem(InputController.getPlayer(), new HealthRestore());
        }
    }
    @Override
    public void onReleased() {
        buyHealthRestore = false;
    }
}
