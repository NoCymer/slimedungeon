package com.game.engine.generation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.game.engine.physics.Boss;
import com.game.engine.physics.Enemy;
import com.game.engine.physics.Item;
import com.game.engine.physics.NPC;
import com.game.engine.physics.PhysicalObject;
import com.game.engine.physics.World;
import com.game.engine.tiles.Directions;
import com.game.engine.tiles.Grid;
import com.game.engine.tiles.GridCell;
import com.game.engine.tiles.TileMap;
import com.game.engine.trigger.TriggerMap;
import com.game.engine.view.Scene;
/** Room class */
public class Room extends Scene{
    /** Directions in which the room can have neighbors */
    private ArrayList<Directions> roomConstraints = new ArrayList<Directions>();

    /** Rooms that are neighbors of the room */
    private HashMap<Directions,Room> neighboringRooms = new HashMap<Directions,Room>();

    /** Count of all existing rooms */
    private static int count = 0;

    /** Id of the room */
    private int id;    

    /** World */
    public World world;
    /** Whether or not the room is locked */
    private boolean isLocked = false;
    /** Returns whether or not the room is locked
     * @return
    */
    public boolean isLocked() {
        return isLocked;
    }
    /** Locks the room */
    public void lockRoom() {
        isLocked = true;
    }
    /** Unlocks the room */
    public void unlockRoom() {
        isLocked = false;
    }
    /** Items */
    public ArrayList<Item> items = new ArrayList<Item>();

    /**
    * Objects
    */
    public ArrayList<PhysicalObject> objects = new ArrayList<PhysicalObject>();
    
    /**
     * Enemies
     */
    public ArrayList<Boss> bosses = new ArrayList<Boss>();

    /**
     * Enemies
     */
    public ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    /**
     * NPCs
     */
    public ArrayList<NPC> npcs = new ArrayList<NPC>();

    /**
     * Triggers
     */
    public ArrayList<TriggerMap> triggerMaps = new ArrayList<TriggerMap>();



    /**
     * Add enemy
     * 
     * @param vx
     * @param vy
     * @param px
     * @param py
     * @throws java.io.IOException
     */
    public void addEnemy(double vx, double vy, int px, int py, int level) throws IOException {
        Enemy enemy = new Enemy(world, this, level);
        enemy.vx = vx;
        enemy.vy = vy;
        enemy.px = px;
        enemy.py = py;
        enemy.index = enemies.size();

        enemies.add(enemy);
    }

    /**
     * Add boss
     * 
     * @param vx
     * @param vy
     * @param px
     * @param py
     * @throws java.io.IOException
     */
    public void addBoss(double vx, double vy, int px, int py, int level) throws IOException {
        Boss boss = new Boss(world, this, level);
        boss.vx = vx;
        boss.vy = vy;
        boss.px = px;
        boss.py = py;
        boss.index = bosses.size();

        bosses.add(boss);
    }
    /**
     * Add NPC
     * 
     * @param vx
     * @param vy
     * @param px
     * @param py
     * @throws java.io.IOException
     */
    public void addNPC(double vx, double vy, int px, int py) throws IOException {
        NPC npc = new NPC(world, this);
        npc.vx = vx;
        npc.vy = vy;
        npc.px = px;
        npc.py = py;
        npc.index = npcs.size();

        npcs.add(npc);
    }

    /**
     * Add object
     * @param o
     */
    public void addObject(PhysicalObject o) {
        objects.add(o);
    }

    /** Creates a new unique room
     * @param roomConstraints Directions in which the room can have neighbors
     */
    public Room(World world, ArrayList<Directions> roomConstraints) {
        this.world = world;
        id = count++;
        setRoomPossibleDirections(roomConstraints);
    }

    /** Creates a new unique room
     * @param roomConstraints Directions in which the room can have neighbors
     */
    public Room(World world, Directions[] roomConstraints) {
        this.world = world;
        id = count++;
        setRoomPossibleDirections(roomConstraints);
    }

    /**
     * @return Returns all directions which are not occupied by a room and does
     * not overflow from the given grid
     * @param grid Grid
     * @return
     */
    public List<Directions> getAvailableDirections(Grid<Room> grid) {
        ArrayList<Directions> availableDirections = new ArrayList<Directions>();
        HashMap<Directions, GridCell<Room>> adjacentRoomsCells = grid.getAdjacentCells(this);
        for(Directions dir: roomConstraints)
            if (
                adjacentRoomsCells.get(dir) != null &&
                adjacentRoomsCells.get(dir).isEmpty()
            ) availableDirections.add(dir);
        return availableDirections;
    }

    /**
     * Adds a new neighbor to the room
     * @param direction Direction of the new neighbor
     * @param room New neighbor
     */
    private void addNeighbor(Directions direction, Room room) {
        neighboringRooms.put(direction, room);
    }

    /**
     * @return List of all the possible directions of the room
     */
    public List<Directions> getRoomConstraints() {
        return roomConstraints;
    }
    /** Updates the neighboring rooms */
    private void updateNeighboringRooms() {
        for(Directions dir: Directions.values()) {
            if(roomConstraints.contains(dir)) {
                neighboringRooms.put(dir, null);
            }
        }
    }

    /**
     * Sets the possible directions of the room 
     * @param freeDirections New free directions of the room
     */
    public void setRoomPossibleDirections(List<Directions> freeDirections) {
        this.roomConstraints = new ArrayList<Directions>(freeDirections);
        updateNeighboringRooms();
    }

    /**
     * Sets the possible directions of the room 
     * @param freeDirections New free directions of the room
     */
    public void setRoomPossibleDirections(Directions[] freeDirections) {
        setRoomPossibleDirections(Arrays.asList(freeDirections));
    }

    /**
     * @return Room's neighboring room
     */
    public HashMap<Directions,Room> getNeighbors() {
        return neighboringRooms;
    }

    /**
     * @return Id of the room
     */
    public int getId(){
        return id;
    }

    /**
     * @return String representation of the room
     */
    public String toString() {
        String ret = "";
        ret += id + " : [  ";
        for(Entry<Directions, Room> entry: neighboringRooms.entrySet()) {
            if(entry.getValue() != null)
                ret+= entry.getKey() + ":" + entry.getValue().id + " ";
        }
        ret += " ]";
        return ret;
    }

    /** Updates the room */
    public void update(){
        for(Entry<Directions, Room> entry: neighboringRooms.entrySet()){
            if(entry.getValue()!=null) {
                updateTileMap(entry.getKey(), entry.getValue(), getTileMap());
            }
        }
    }
    private void updateTileMap(Directions dir, Room room, TileMap tm) {
        switch(dir){
            case UP:
                if(room.isLocked() || isLocked()){
                    tm.setTile(tm.getCountX()/2, 0, 15);
                    tm.setTile(tm.getCountX()/2-1, 0, 14);
                }
                else{
                    tm.setTile(tm.getCountX()/2, 0, 27);
                    tm.setTile(tm.getCountX()/2-1, 0, 26);
                }
                break;
            case DOWN:
                if(room.isLocked() || isLocked()){
                    tm.setTile(tm.getCountX()/2, tm.getCountY()-1, 19);
                    tm.setTile(tm.getCountX()/2-1, tm.getCountY()-1, 18);
                }
                else{
                    tm.setTile(tm.getCountX()/2, tm.getCountY()-1, 29);
                    tm.setTile(tm.getCountX()/2-1, tm.getCountY()-1, 28);
                }
                break;
            case LEFT:
                if(room.isLocked() || isLocked()){
                    tm.setTile(0, tm.getCountY()/2, 21);
                    tm.setTile(0, tm.getCountY()/2-1, 20);
                }
                else{
                    tm.setTile(0, tm.getCountY()/2, 33);
                    tm.setTile(0, tm.getCountY()/2-1, 32);
                }
                break;    
            case RIGHT:
                if(room.isLocked() || isLocked()){
                    tm.setTile(tm.getCountX()-1, tm.getCountY()/2, 17);
                    tm.setTile(tm.getCountX()-1, tm.getCountY()/2-1, 16);
                }
                else{
                    tm.setTile(tm.getCountX()-1, tm.getCountY()/2, 31);
                    tm.setTile(tm.getCountX()-1, tm.getCountY()/2-1, 30);
                }
                break;   
        }
    }

    /**
     * Links the room to the target room in the given direction
     * @param target Target room
     * @param dir Direction of the link
     */
    public void linkRoom(Room target, Directions dir) {
        addNeighbor(dir, target);
        target.addNeighbor(dir.opposite(), this);
    }
    /** Checks if the given room is equal to the room 
     * @param o
     * @return
    */
    public boolean equals(Object o) {
        if(!(o instanceof Room))
            return false;
        if(id == ((Room)o).id)
            return true;
        else return false;
    }
}
