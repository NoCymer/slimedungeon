package com.game.engine.shop;

import com.game.engine.physics.Player;

public class HealthBooster extends ShopItem {
    public HealthBooster() {
        super(5, "Health Booster");
    }

    @Override
    public boolean canBeBoughtBy(Player player) {
        int curr = player.getHealthMultiplicator()+1;
        if(curr <= 10) { return true; }
        return false;
    }
    @Override
    public void onBuy(Player player) {
        int curr = player.getHealthMultiplicator()+1;
        player.setHealthMultiplicator(curr);
        player.setHealth(player.getHealth() + player.HEALTH_MUL_UNIT);
    }
}
