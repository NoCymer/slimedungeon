package com.game.engine.hud.map;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

import com.game.engine.generation.Map;
import com.game.engine.hud.HudElement;
import com.game.engine.generation.Room;
import com.game.engine.view.Coords;
import com.game.engine.tiles.Directions;
import com.game.engine.tiles.GridCell;
import com.game.engine.view.Sprite;

import java.util.Map.Entry;

import javax.imageio.ImageIO;

import java.awt.FontMetrics;

/** MapHudBackground class */
public class MapHudBackground extends HudElement{
    /** The map to draw */
	private Map map;    
    /** Constructs a map hud 
     * @param map
     * @param x
     * @param y
     * @param width
     * @param height
    */
    public MapHudBackground(Coords origin, Map map, int x, int y, int width, int height) {
        super(origin, x, y, width, height);
        this.map = map;
    }

    @Override
    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(new Color(100, 100, 100, 0));
        try{
            new Sprite(
                getX(),
                getY(),
                1,
                ImageIO.read(
                    new File("assets/misc/MenuBackground.png")
                )
                
            ).draw(g, getX(), getY());
        } catch (Exception e) { }
        g.setColor(c);


		int roomsize = getWidth() / (map.rooms.getxCount()*2);
        int interRoomOffset = roomsize;
        for(GridCell<Room> roomCell: map.rooms) {
            if(roomCell.getContent() != null) {
                int x = getX() + 10 + roomCell.getCoords()[0]*(roomsize + interRoomOffset) + 10;
                int y = getY() + 10 + roomCell.getCoords()[1]*(roomsize + interRoomOffset) + 10;
                if(map.activeRoom.equals(roomCell.getContent()))
                    g.fillOval(x+roomsize/4,y+roomsize/4,roomsize/2,roomsize/2);

                g.drawRect(
                    x,
                    y,
                    roomsize,
                    roomsize
                ); 
                
                
                String str = "" +roomCell.getContent().getId();
                FontMetrics metrics = g.getFontMetrics(g.getFont());
                int xStr = x + (roomsize - metrics.stringWidth(str)) / 2;
                int yStr = y + ((roomsize - metrics.getHeight()) / 2) + metrics.getAscent();
                g.drawString(
                    str,
                    xStr,
                    yStr
                );

                for(Entry<Directions, Room> nRoom: roomCell.getContent().getNeighbors().entrySet()) {
                    if(nRoom.getValue() != null)
                        switch (nRoom.getKey()) {
                            case DOWN:
                                g.drawLine(x+roomsize/2, y+roomsize, x+roomsize/2, y+roomsize + interRoomOffset);
                                break;
                            case UP:
                                g.drawLine(x+roomsize/2, y, x+roomsize/2, y - interRoomOffset);
                                break;
                            case LEFT:
                                g.drawLine(x, y+roomsize/2, x - interRoomOffset, y+roomsize/2);
                                break;
                            case RIGHT:
                                g.drawLine(x+roomsize, y+roomsize/2, x + roomsize + interRoomOffset, y+roomsize/2);
                                break;
                        }
                }
            }
        }
        
    }

    @Override
    public void onClick() {}
    
}
