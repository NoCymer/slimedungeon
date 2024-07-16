package com.game.engine.hud.player;

import java.awt.Graphics;

import com.game.engine.hud.Hud;
import com.game.engine.hud.shop.Shop;
import com.game.engine.physics.Player;
import com.game.engine.view.Coords;
import com.game.engine.view.Display;
/** PlayerHud class */
public class PlayerHud extends Hud{
    /** The shop button */
    OpenShop openShop;
    /** The shop arcade button */
    OpenShop arcadeShop;
    /** The Map indicator */
    MapIndicator mapIndicator;
    /** The map arcade button */
    MapIndicator arcadeMap;
    /** The players gems */
    GemsCount gemsCount;
    /** The enemy count */
    EnemyCount enemyCount;
    /** The attack cooldown */
    AttackCooldown attackCooldown;
    /** Constructs the player hud 
     * @param display 
     * @param player
     * @param shop
    */
    public PlayerHud(Display display, Player player, Shop shop) {
        super(display, 0, 0, display.getWidth(), display.getHeight());
        Coords origin = new Coords(getX(), getY());
        Coords origin1 = new Coords(0, 0);
        addElement(new HealthBar(origin1,player, 10,display.getHeight()-30, 200, 20, true, false));

        openShop = new OpenShop(origin, shop, getWidth()-42, 100, 50, 32,"assets/misc/shop.png",true);
        arcadeShop = new OpenShop(origin, shop, getWidth()-(int)(42*4.5), 100+20, 50, 32,"assets/misc/ArcadeController5.png",false);
        gemsCount = new GemsCount(origin, player, getWidth()-42, 140, 50, 32);
        enemyCount = new EnemyCount(origin, player, getWidth()-42, getHeight()-70, 50, 32);
        attackCooldown = new AttackCooldown(origin, player, 10, 10, 50, 64);
        mapIndicator = new MapIndicator(origin,getWidth()-42,10,50,32,"assets/misc/Map.png",true);
        arcadeMap = new MapIndicator(origin,getWidth()-(int)(42*4.5),10 + 20,50,32,"assets/misc/ArcadeController6.png",false);
        addElement(openShop);
        addElement(arcadeShop);
        addElement(gemsCount);
        addElement(enemyCount);
        addElement(attackCooldown);
        addElement(mapIndicator);
        addElement(arcadeMap);
    }
    /** Draws the player hud 
     * @param g
    */
    public void draw(Graphics g) {
        int topInset = getTopInset();
        this.setOffsetY(topInset);

        super.draw(g);
    }
}
