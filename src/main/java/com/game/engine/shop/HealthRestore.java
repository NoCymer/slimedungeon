package com.game.engine.shop;

import com.game.engine.physics.Player;

public class HealthRestore extends ShopItem {
    public HealthRestore() {
        super(5, "Health Restore");
    }

    @Override
    public boolean canBeBoughtBy(Player player) {
        return true;
    }
    @Override
    public void onBuy(Player player) {
        int health = player.getHealth();
        int maxHealth = player.getMaxHealth();
        if(health +40 <= maxHealth) {
            player.setHealth(health + 40);
        }
        else if(health+40 < maxHealth + 40){
            player.setHealth(maxHealth);
        }
    }
}
