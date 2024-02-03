package com.game.engine.shop;

import com.game.engine.physics.Player;

/**
 * Buyable item in the shop
 */
public abstract class ShopItem {
    private int price;
    private String name;

    protected ShopItem(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * When the item is bought this method will be called 
     * @param player Player that bought the item
     */
    public abstract void onBuy(Player player);

    /**
     * Checks whether or not the specified player can bought the current item
     * @param player Player that tries to buy the item
     * @return True if the player can buy the item, else false.
     */
    public abstract boolean canBeBoughtBy(Player player);
}
