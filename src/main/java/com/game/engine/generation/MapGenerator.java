package com.game.engine.generation;

import java.util.ArrayList;
import java.util.Random;
import java.util.Map.Entry;

import com.game.engine.physics.Gem;
import com.game.engine.physics.World;
import com.game.engine.tiles.Atlas;
import com.game.engine.tiles.Directions;
import com.game.engine.tiles.Grid;
import com.game.engine.tiles.GridCell;
import com.game.engine.tiles.TileMap;
import com.game.engine.view.Coords;

/** MapGenerator class */
public class MapGenerator {
    public static int[][] tm;
    /** Constructs a MapGenerator */
    public MapGenerator() {}
    /** Returns a tilemap 
     * @param r
     * @return
    */
    private static TileMap generateTileMap(Room r) {
        Atlas atlas = new Atlas(
            "assets/tiles/tilemap_exp.png",
            16,
            8,
            8,
			2
        );

        tm = new int[][] {
            {22,23,23,23,23,23,23,23,23,23,23,23,23,23,23,24},
            {25,9,9,9,9,9,9,9,9,9,9,9,9,9,9,5},
            {25,9,9,9,9,9,9,9,9,9,9,9,9,9,9,5},
            {25,9,9,9,9,9,9,9,9,9,9,9,9,9,9,5},
            {25,9,9,9,9,9,9,9,9,9,9,9,9,9,9,5},
            {25,9,9,9,9,9,9,9,9,9,9,9,9,9,9,5},
            {25,9,9,9,9,9,9,9,9,9,9,9,9,9,9,5},
            {25,9,9,9,9,9,9,9,9,9,9,9,9,9,9,5},
            {25,9,9,9,9,9,9,9,9,9,9,9,9,9,9,5},
            {25,9,9,9,9,9,9,9,9,9,9,9,9,9,9,5},
            {25,9,9,9,9,9,9,9,9,9,9,9,9,9,9,5},
            {25,9,9,9,9,9,9,9,9,9,9,9,9,9,9,5},
            {25,9,9,9,9,9,9,9,9,9,9,9,9,9,9,5},
            {25,9,9,9,9,9,9,9,9,9,9,9,9,9,9,5},
            {25,9,9,9,9,9,9,9,9,9,9,9,9,9,9,5},
            {6,7,7,7,7,7,7,7,7,7,7,7,7,7,7,8}
        };
        
        for(Entry<Directions, Room> entry: r.getNeighbors().entrySet()) {
            if(entry.getValue() != null) {
                int cX = tm.length;
                int cY = tm[0].length;
                for(int x = 1; x < tm.length-1; x++)
                    for(int y = 1; y < tm[0].length-1; y++) {
                        if(!(
                            //Down
                            (
                                y < 3 &&
                                x > cX/2-4 && x < cX/2+3
                            ) ||
                            // Up
                            (
                                y > cY-4 &&
                                x > cX/2-4 && x < cX/2+3
                            ) ||
                            // Left
                            (
                                x < 4 &&
                                y > cY/2-4 && y < cY/2+3
                            ) ||
                            // Right
                            (
                                x > cX-4 &&
                                y > cY/2-4 && y < cY/2+3
                            ) 
                        )) {
                            Random rand = new Random();
                            if(rand.nextInt(30) == 1) {
                                Random rand1 = new Random();
                                tm[y][x] = rand1.nextInt(5)+9;
                            }
                        }
                    }
                
            switch(entry.getKey()) {
                case UP:
                    // For door triggers
                    tm[1][tm[0].length/2] = 1;
                    tm[1][tm[0].length/2-1] =1;
                    break;
                case DOWN:
                    // For door triggers
                    tm[tm.length-2][tm[0].length/2] = 3;
                    tm[tm.length-2][tm[0].length/2-1] =3;
                    break;
                case LEFT:
                    // For door triggers
                    tm[tm.length/2][1] = 2;
                    tm[tm.length/2 -1][1] =2;
                    break;
                case RIGHT:
                    // For door triggers
                    tm[tm.length/2][tm[0].length-2] = 4;
                    tm[tm.length/2-1][tm[0].length-2] =4;
                    break;
            }

            }

        }

        TileMap tileMap = new TileMap(
            16,
            2,
            tm,
            atlas
        );
        
        return tileMap;
    }
    /** Populates the map 
     * @param map
     * @param minEnemyCount
     * @param maxEnemyCount
    */
    public static void populateMap(
        Map map,
        int minEnemyCount,
        int maxEnemyCount
    ) { 
        populateMap(map, minEnemyCount, maxEnemyCount, new ArrayList<Integer>());
    }
    /** Populates the rooms of the map that aren't in the exclusionIndexes 
     * @param map
     * @param minEnemyCount
     * @param maxEnemyCount
     * @param exclusionIndexes
    */
    public static void populateMap(
        Map map,
        int minEnemyCount,
        int maxEnemyCount,
        ArrayList<Integer> exclusionIndexes
    ) {
        
        int minOffset = 2;
        for(GridCell<Room> roomCell : map.rooms){
            if(!roomCell.isEmpty() && roomCell.getContent().getId() == map.endRoom.getId()){
                // populates the room
                try{ 
                    map.endRoom.addBoss(
                        0,
                        0,
                        map.endRoom.getTileMap().size()/2,
                        map.endRoom.getTileMap().size()/2,
                        20
                    );
                }
                catch(Exception e){}
            }
            else if(
                !roomCell.isEmpty() &&
                !exclusionIndexes.contains(roomCell.getContent().getId())
            ) {
                int id = roomCell.getContent().getId();
                Random rand = new Random();
                int ct = rand.nextInt(maxEnemyCount- minEnemyCount)+minEnemyCount;
                if(ct == 0 && rand.nextInt(5)%2 == 0)
                    ct = rand.nextInt(maxEnemyCount- minEnemyCount)+minEnemyCount;

                ArrayList<Coords> possiblePositions = new ArrayList<Coords>();

                int cX = roomCell.getContent().getTileMap().getCountX();
                int cY = roomCell.getContent().getTileMap().getCountY();

                // Inits all from zero to max coords
                for(int y = 2; y < cY-2; y++) {
                    for(int x = 2; x < cX-2; x++) {
                        if(!(
                            //Down
                            (
                                y < 3 &&
                                x > cX/2-4 && x < cX/2+3
                            ) ||
                            // Up
                            (
                                y > cY-4 &&
                                x > cX/2-4 && x < cX/2+3
                            ) ||
                            // Left
                            (
                                x < 4 &&
                                y > cY/2-4 && y < cY/2+3
                            ) ||
                            // Right
                            (
                                x > cX-4 &&
                                y > cY/2-4 && y < cY/2+3
                            ) 
                        ))
                            possiblePositions.add(new Coords(x, y));
                    }
                }


                for(int i = 0; i < ct ; i++) {
                    try{
                        Random randLevel = new Random();
                        int level = randLevel.nextInt((id+1)-(id-1))+id-1;
                        Coords coords = possiblePositions.get(rand.nextInt(possiblePositions.size()));  
                        roomCell.getContent().addEnemy(
                            0,
                            0,
                            coords.getX()*roomCell.getContent().getTileMap().size()/cX,
                            coords.getY()*roomCell.getContent().getTileMap().size()/cY,
                            level                        
                        );
                        for(int j = 0; j < possiblePositions.size(); j++) {
                            if(
                                possiblePositions.get(j).getX() < (coords.getX()+minOffset) &&
                                possiblePositions.get(j).getX() > (coords.getX()-minOffset) &&
                                possiblePositions.get(j).getY() < (coords.getY()+minOffset) &&
                                possiblePositions.get(j).getY() > (coords.getY()-minOffset))
                                {
                                    possiblePositions.remove(j);
                                }
                        }
                    }
                    catch (Exception e) { }   
                     
                }  
                Room room = roomCell.getContent();
                if(room.enemies.size() == 0){
                    // Place gems in the middle of the room 
                    for(int i = 0; i < rand.nextInt(5)+5 ; i++) {
                        try{
                            Coords coords = possiblePositions.get(rand.nextInt(possiblePositions.size()));  
                            room.items.add(new Gem(
                                room.world,
                                coords.getX()*roomCell.getContent().getTileMap().size()/cX,
                                coords.getY()*roomCell.getContent().getTileMap().size()/cY
                            ));
                            for(int j = 0; j < possiblePositions.size(); j++) {
                                if(
                                    possiblePositions.get(j).getX() < (coords.getX()+minOffset) &&
                                    possiblePositions.get(j).getX() > (coords.getX()-minOffset) &&
                                    possiblePositions.get(j).getY() < (coords.getY()+minOffset) &&
                                    possiblePositions.get(j).getY() > (coords.getY()-minOffset))
                                    {
                                        possiblePositions.remove(j);
                                    }
                            }
                        }
                        catch (Exception e) {}   
                         
                    }  
                }            
            }
        };
    }

    /** 
     * Generates a map
     * @param startRoom
     * @param endRoom
     * @param totalRoomCount
     * @param countX
     * @param countY
     * @return
    */
    public static Map GenerateMap(
        World w,
        Room startRoom,
        Room endRoom,
        int totalRoomCount,
        int countX,
        int countY
    ) {
        ArrayList<GridCell<Room>> rooms = new ArrayList<GridCell<Room>>();
        Grid<Room> roomsGrid = new Grid<Room>(countX, countY);
        startRoom.setRoomPossibleDirections(new Directions[] {Directions.UP});
        roomsGrid.setCell(countX/2, countY/2, startRoom);
        rooms.add(roomsGrid.getCell(countX/2, countY/2));
        for(int i = 0; i < totalRoomCount; i++) {

            //Searches for available spots
            ArrayList<Spot> availableSpots = getAvailableSpots(roomsGrid);

            // Creates new room at a random available spot
            Random rand = new Random();
            Spot newRoomSpot = availableSpots.get(rand.nextInt(availableSpots.size()));

            if(i == totalRoomCount-1) {
                roomsGrid.setCell(newRoomSpot.getSpot(), endRoom);
                
                // Links the last room
                newRoomSpot.getOrigin().getContent().linkRoom(
                    newRoomSpot.getSpot().getContent(), newRoomSpot.getDirection()
                );
            } else {
                roomsGrid.setCell(newRoomSpot.getSpot(), new Room(w, Directions.values()));

                // Links the new room
                newRoomSpot.getOrigin().getContent().linkRoom(
                    newRoomSpot.getSpot().getContent(), newRoomSpot.getDirection()
                );
                linkRoom(roomsGrid, newRoomSpot);
            }
            
            //Register the new room
            rooms.add(newRoomSpot.getSpot());
        }

        Map map = generateMap(rooms, countX, countY);
        map.startRoom = startRoom;
        map.endRoom = endRoom;
        
        map.setActiveRoom(rooms.get(0).getContent().getId());
        return map;
    }
    /** Generates a map 
     * @param rooms
     * @param countX
     * @param countY
     * @return
    */
    private static Map generateMap(ArrayList<GridCell<Room>> rooms, int countX, int countY) {
        Map map = new Map(0,0, countX, countY);

        rooms.forEach(cell->{
            map.addRoom(cell.getContent(), cell.getCoords()[0], cell.getCoords()[1]);
            cell.getContent().setTileMap(generateTileMap(cell.getContent()));
        });
        return map;
    }
    /** Links randomly the newRoomSpot to neighboring rooms on the grid 
     * @param roomsGrid
     * @param newRoomSpot
    */
    private static void linkRoom(Grid<Room> roomsGrid, Spot newRoomSpot) {
        for(Entry<Directions, GridCell<Room>> entry: newRoomSpot.getSpot().getAdjacentCells().entrySet()) {
            // Checks if the current adjacent cell isn't null
            if(entry.getValue() != null && entry.getValue().getContent() != null) {
                // Checks if the current adjacent room can link in the opposite direction
                if(
                    entry.getValue().getContent().getRoomConstraints().contains(entry.getKey().opposite())
                ) {
                    Random rand = new Random();
                    if(rand.nextInt(100)%3==0)
                        newRoomSpot.getSpot().getContent().linkRoom(
                            entry.getValue().getContent(), entry.getKey()
                        );
                }
            }
        }
    }
    /** Returns all the available spots for a given grid 
     * @param grid
     * @return
    */
    private static ArrayList<Spot> getAvailableSpots(Grid<Room> grid) {
        ArrayList<Spot> availableSpots = new ArrayList<Spot>();

        grid.getNonEmptyCells().forEach(cell->{
            cell.getContent().getAvailableDirections(grid).forEach(direction->{
                boolean exists = false;
                for(Spot spot: availableSpots) {
                    if(spot.getSpot().getIndex()==cell.getIndex())
                        exists = true;
                };
                if(!exists)
                    availableSpots.add(
                        new Spot(cell, cell.getAdjacentCells().get(direction), direction)
                    );
            });
        });
        return availableSpots;
    }
}
/** Spot class */
class Spot {
    /** Origin of the spot */
    private GridCell<Room> origin;
    /** Direction from the origin */
    private Directions direction;
    /** Cell of the spot */
    private GridCell<Room> spot;
    /** Returns the origin of the spot 
     * @return
    */
    public GridCell<Room> getOrigin() {
        return origin;
    }
    /** Returns the direction of the spot 
     * @return
    */
    public Directions getDirection() {
        return direction;
    }
    /** Returns the spot 
     * @return
    */
    public GridCell<Room> getSpot() {
        return spot;
    }
    /** Constructs a spot 
     * @param origin
     * @param dest
     * @param direction
    */
    public Spot(GridCell<Room> origin, GridCell<Room> dest, Directions direction) {
        this.origin = origin;
        this.direction = direction;
        this.spot = dest;
    }
}