package com.game.engine.controller.bindings;

import com.game.engine.controller.InputController;
import com.game.engine.controller.KeyBinding;
import com.game.engine.shop.AttackBooster;
import com.game.engine.shop.ShopManager;

public class BindingBuyAttackBooster extends KeyBinding{
    private boolean buyAttackBooster = false;
    @Override
    public void onPressed() {
        if(ShopManager.instance().isShopOpened()  && !buyAttackBooster) {
            buyAttackBooster = true;
            ShopManager.instance().tryBuyItem(InputController.getPlayer(), new AttackBooster());
        }
    }
    @Override
    public void onReleased() {
        buyAttackBooster = false;
    }
}
