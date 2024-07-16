package com.game.engine.controller.bindings;

import com.game.engine.controller.InputController;
import com.game.engine.controller.KeyBinding;
import com.game.engine.shop.DefenceBooster;
import com.game.engine.shop.ShopManager;

public class BindingBuyDefenceBooster extends KeyBinding{
    private boolean buyDefenceBooster = false;
    @Override
    public void onPressed() {
        if(ShopManager.instance().isShopOpened()  && !buyDefenceBooster) {
            buyDefenceBooster = true;
            ShopManager.instance().tryBuyItem(InputController.getPlayer(), new DefenceBooster());
        }
    }
    @Override
    public void onReleased() {
        buyDefenceBooster = false;
    }
}
