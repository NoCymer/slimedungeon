package com.game.engine.shop;

import com.game.engine.physics.Player;

/**
 * Singleton class managing the shop
 */
public class ShopManager {
    private static ShopManager instance = new ShopManager();

    protected ShopManager() {}

    /**
     * Tries to buy an item in the shop
     * @param player Player that buys the item
     * @param item Item to buy
     * @return True if the item was bought
     * @return False if the item wasn't bought
     */
    public boolean tryBuyItem(Player player, ShopItem item) {
        if(player.getGems() >= item.getPrice() && item.canBeBoughtBy(player)) {
            player.removeGems(item.getPrice());
            item.onBuy(player);
            return true;
        }
        return false;
    }

    /**
     * Returns the active shop manager
     * @return
     */
    public static ShopManager instance() {
        if(instance == null) instance = new ShopManager();
        return instance;
    }
}
