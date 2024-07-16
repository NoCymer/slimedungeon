package com.game.engine.physics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.game.engine.controller.Control;
import com.game.engine.controller.InputController;
import com.game.engine.dialog.DialogController;
import com.game.engine.generation.Map;
import com.game.engine.hud.Hud;
import com.game.engine.hud.map.MapHud;
import com.game.engine.hud.shop.Shop;
import com.game.engine.main.MainLoop;
import com.game.engine.tiles.TileMap;
import com.game.engine.trigger.TriggerMap;
import com.game.engine.view.CoordinateSystem;

/** World class */
public class World {
    /** MainLoop */
    public MainLoop gameLoop;

    /** Controller */
    public Control c;

    /** World border */
    private WorldBorder worldBorder;

    /** World triggers */
    public ArrayList<TriggerMap> triggerMaps = new ArrayList<TriggerMap>();
    
    /** Huds */
    public HashMap<String, Hud> huds = new HashMap<String, Hud>();

    /** Actual trigger */
    public TriggerMap worldTrigger;

    /** Player */
    public Player player;

    /** Active map */
    public Map map;

    /** Hud displaying the map */
    public MapHud mapHud;

    /** Hud displaying the shop */
    public Shop shop;

    /**
     * Creates a world
     * @throws IOException
     */
    public World() throws IOException {
        
    }

    /**
     * Adds a hud
     * @param hudName
     * @param hud
     */
    public void addHud(String hudName, Hud hud) {
        huds.put(hudName, hud);
    }

    /**
     * Adds a trigger map
     * @param triggerMap
     */
    public void addTriggerMap(TriggerMap triggerMap) {
        this.triggerMaps.add(triggerMap);
    }

    /**
     * Sets the active map to a new one
     * @param map
     */
    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * Sets the active trigger map to a new one
     * @param tm
     */
    public void setTriggerMap(TriggerMap tm) {
        worldTrigger = tm;
    }

    /**
     * Changes the tile map binded to the active trigger map
     * @param map
     */
    public void setTriggerMapTileMap(TileMap map) {
        worldTrigger.setTileMap(map);
    }

    /**
     * Changes the active world border
     * @param worldBorder
     */
    public void setWorldBorder(WorldBorder worldBorder) {
        this.worldBorder = worldBorder;
    }

    /**
     * Returns the world border
     * @return
     */
    public WorldBorder getWorldBorder() {
        return this.worldBorder;
    }

    /** Whether or not the boss is defeated */
    public boolean bossDefeated = false;

    /** Updates the world */
    public void update() {
        mapHud.setIsShown(InputController.map);
        if(InputController.nextDialog && DialogController.getCurrentDialog() !=null && huds.get("npc").isShown()){
            DialogController.getCurrentDialog().nextLine();
            InputController.nextDialog = false;
        }
        if(map.enemiesCount() == 0) {
            map.endRoom.unlockRoom();
        }
        if(map.activeRoom.getId()==1 && !bossDefeated){
            map.endRoom.lockRoom();
        }
        else if (bossDefeated){
            map.endRoom.unlockRoom();
        }
        
    }

    /**
     * Set the player to a new one
     * 
     * @param vx
     * @param vy
     * @param px
     * @param py
     * @throws java.io.IOException
     */
    public void setPlayer(double vx, double vy, int px, int py, int health, int gems) throws IOException {
        player = new Player(this, map.activeRoom, px, py, gems);
        CoordinateSystem.h = player;
        player.index = 1;
    }

    public ArrayList<Entity> getAllActiveRoomEntities() {
        ArrayList<Entity> all = new ArrayList<Entity>();

        for (Enemy enemy : map.activeRoom.enemies) all.add(enemy);
        for (Boss boss : map.activeRoom.bosses) all.add(boss);
        for (NPC npc : map.activeRoom.npcs) all.add(npc);
        all.add(player);

        return all;
    }
}
