package com.game.engine.shop;

import com.game.engine.controller.KeyController;
import com.game.engine.hud.shop.Shop;
import com.game.engine.physics.Player;

/**
 * Singleton class managing the shop
 */
public class ShopManager {
    private static ShopManager instance = new ShopManager();
    private boolean isShopOpened = false;
    private Shop shop;

    protected ShopManager() {} 

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

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

    public boolean isShopOpened() {
        return isShopOpened;
    }

    public void openShop() {
        isShopOpened = true;
        shop.setIsShown(true);
        shop.setInteractable(true);
        KeyController.canMove = false;
    }

    public void closeShop() {
        isShopOpened = false;
        shop.setIsShown(false);
        shop.setInteractable(false);
        KeyController.canMove = true;
    }
}
