package com.game.engine.shop;

import com.game.engine.physics.Player;

public class AttackBooster extends ShopItem {
    public AttackBooster() {
        super(5, "Attack Booster");
    }
    @Override
    public boolean canBeBoughtBy(Player player) {
        int curr = (int)(player.getAttackMultiplicator()+1.5);
        if(curr <= 10) { return true; }
        return false;
    }
    @Override
    public void onBuy(Player player) {
        int curr = (int)(player.getAttackMultiplicator()+1.5);
        player.setAttackMultiplicator(curr);
    }
}