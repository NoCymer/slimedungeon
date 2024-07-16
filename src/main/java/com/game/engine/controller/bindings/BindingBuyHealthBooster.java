package com.game.engine.controller.bindings;

import com.game.engine.controller.InputController;
import com.game.engine.controller.KeyBinding;
import com.game.engine.shop.HealthBooster;
import com.game.engine.shop.ShopManager;

public class BindingBuyHealthBooster extends KeyBinding{
    private boolean buyHealthBooster = false;
    @Override
    public void onPressed() {
        if(ShopManager.instance().isShopOpened()  && !buyHealthBooster) {
            buyHealthBooster = true;
            ShopManager.instance().tryBuyItem(InputController.getPlayer(), new HealthBooster());
        }
    }
    @Override
    public void onReleased() {
        buyHealthBooster = false;
    }
}
