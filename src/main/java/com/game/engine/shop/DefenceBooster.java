package com.game.engine.shop;

import com.game.engine.physics.Player;

public class DefenceBooster extends ShopItem {
    public DefenceBooster() {
        super(5, "Defence Booster");
    }
    @Override
    public boolean canBeBoughtBy(Player player) {
        int curr = player.getDefenceMultiplicator()+1;
        if(curr <= 10) { return true; }
        return false;
    }
    @Override
    public void onBuy(Player player) {
        int curr = player.getDefenceMultiplicator()+1;
        player.setDefenceMultiplicator(curr);
    }
}