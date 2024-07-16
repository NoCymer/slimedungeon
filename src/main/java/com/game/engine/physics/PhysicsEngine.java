package com.game.engine.physics;

import java.util.ArrayList;

import com.game.engine.generation.MapGenerator;
import com.game.engine.generation.Room;
import com.game.engine.tiles.GridCell;
import com.game.engine.trigger.TriggerMap;

public class PhysicsEngine {

	/** Active world */
	public World world;

	/** Whether or not the game has ended */
	public static boolean endGame = false;

	/** Speed of the entities */
	public static double SPEED = 2;

	/**
	 * Updates each object and calculates physics
	 */
	public void update() {
		if(!endGame) {
			world.update();
			
			for (Enemy enemy : world.map.activeRoom.enemies) {
				enemy.update();
			}
				
			for (NPC npc : world.map.activeRoom.npcs) {
				npc.update();
			}

			for (Boss boss : world.map.activeRoom.bosses) {
				boss.update();
			}

			// Apply input
			world.player.vx = world.c.x * 2;
			world.player.vy = -world.c.y * 2;

			// Intertia x
			if (world.c.x == 0) { 
				if(world.player.vx < 0.5 && world.player.vx > -0.5) {
					world.player.vx = 0;
					world.player.ax = 0;
				} else {
					if (world.player.vx > 0)
						world.player.ax -= 0.1;
					else if (world.player.vx < 0)
						world.player.ax += 0.1;
					else
						world.player.ax = 0;
				}
			}

			// Intertia y
			if (world.c.y == 0) {
				if(world.player.vy < 0.5 && world.player.vy > -0.5) {
					world.player.vy = 0;
					world.player.ay = 0;
				} else {
					if (world.player.vy > 0)
						world.player.ay -= 0.1;
					else if (world.player.vy < 0)
						world.player.ay += 0.1;
					else
						world.player.ay = 0;
				}
			}

			// Speed clamping
			world.player.vy = Math.clamp(world.player.vy, -SPEED, SPEED);
			world.player.vx = Math.clamp(world.player.vx, -SPEED, SPEED);

			if (world.player.vx > 0) {
				if (world.player.vy > 0) {
					world.player.sprites.changeActivity("up-right");
				} else if (world.player.vy < 0) {
					world.player.sprites.changeActivity("down-right");
				} else {
					world.player.sprites.changeActivity("right");
				}
			} else if (world.player.vx < 0) {
				if (world.player.vy > 0) {
					world.player.sprites.changeActivity("up-left");
				} else if (world.player.vy < 0) {
					world.player.sprites.changeActivity("down-left");
				} else {
					world.player.sprites.changeActivity("left");
				}
			} else if (world.player.vy > 0) {
				world.player.sprites.changeActivity("up");
			} else if (world.player.vy < 0) {
				world.player.sprites.changeActivity("down");
			}
			world.player.update();
	
			int tileCellSize = (MapGenerator.TILE_SIZE*MapGenerator.TILE_SCALE_FACTOR);

			/** Checks for collision with the world border */
			for(Entity entity : world.getAllActiveRoomEntities()) {
				if(entity.px+entity.width+32 > world.getWorldBorder().width+tileCellSize) {
					entity.px = world.getWorldBorder().width-entity.width-2 + tileCellSize/3 ;
					entity.vx = entity.vx * -0.9;
				}
				if(entity.px <= tileCellSize) {
					entity.px = tileCellSize+2;
					entity.vx = entity.vx * -0.9;
				}
				if(entity.py+entity.height > world.getWorldBorder().height+tileCellSize/2) {
					entity.py = world.getWorldBorder().height-entity.height-2+tileCellSize/2 ;
					entity.vy = entity.vy * -0.9;
				}
				if(entity.py <= tileCellSize) {
					entity.py = tileCellSize+2;
					entity.vy = entity.vy * -0.9;
				}
			}
	
			// Enemy activity
			for (Enemy enemy : world.map.activeRoom.enemies) {
				if (enemy.vx > 0) {
					if (enemy.vy > 0.5) {
						enemy.sprite.changeActivity("up-right");
					} else if (enemy.vy < -0.5) {
						enemy.sprite.changeActivity("down-right");
					} else {
						enemy.sprite.changeActivity("right");
					}
				} else if (enemy.vx < 0) {
					if (enemy.vy > 0.5) {
						enemy.sprite.changeActivity("up-left");
					} else if (enemy.vy < -0.5) {
						enemy.sprite.changeActivity("down-left");
					} else {
						enemy.sprite.changeActivity("left");
					}
				} else if (enemy.vy > 0) {
					enemy.sprite.changeActivity("up");
				} else if (enemy.vy < 0) {
					enemy.sprite.changeActivity("down");
				}
			}
			
			// Boss activity
			for (Boss boss : world.map.activeRoom.bosses) {
				if (boss.vx > 0) {
					if (boss.vy > 0.5) {
						boss.sprite.changeActivity("up-right");
					} else if (boss.vy < -0.5) {
						boss.sprite.changeActivity("down-right");
					} else {
						boss.sprite.changeActivity("right");
					}
				} else if (boss.vx < 0) {
					if (boss.vy > 0.5) {
						boss.sprite.changeActivity("up-left");
					} else if (boss.vy < -0.5) {
						boss.sprite.changeActivity("down-left");
					} else {
						boss.sprite.changeActivity("left");
					}
				} else if (boss.vy > 0) {
					boss.sprite.changeActivity("up");
				} else if (boss.vy < 0) {
					boss.sprite.changeActivity("down");
				}
			}
			
			for (Enemy enemy : world.map.activeRoom.enemies) {
				Collision col = Collision.collision(enemy, world.player);
				/** Checks for collision with the enemy and the player */
				if (Collision.collision(enemy, world.player) != null) {
					world.player.px = world.player.px - (col.correctionValue * Math.cos(col.collisionAngle));
					world.player.py = world.player.py - (col.correctionValue * Math.sin(col.collisionAngle));
	
					enemy.px = enemy.px + (col.correctionValue * Math.cos(col.collisionAngle));
					enemy.py = enemy.py + (col.correctionValue * Math.sin(col.collisionAngle));
				}
				
				/** Checks for collision with the enemies and the enemy */
				for (Enemy enemy1 : world.map.activeRoom.enemies) {
					Collision col1 = Collision.collision(enemy, enemy1);
					if (!enemy.equals(enemy1) && Collision.collision(enemy, enemy1) != null) {
						enemy.px = enemy.px + (col1.correctionValue * Math.cos(col1.collisionAngle));
						enemy.py = enemy.py + (col1.correctionValue * Math.sin(col1.collisionAngle));

						enemy1.px = enemy1.px - (col1.correctionValue * Math.cos(col1.collisionAngle));
						enemy1.py = enemy1.py - (col1.correctionValue * Math.sin(col1.collisionAngle));
					}
				}
			}
			
			for (Boss boss : world.map.activeRoom.bosses) {
				Collision col = Collision.collision(boss, world.player);
				/** Checks for collision with the boss and the player */
				if (Collision.collision(boss, world.player) != null) {
					world.player.px = world.player.px - (col.correctionValue * Math.cos(col.collisionAngle));
					world.player.py = world.player.py - (col.correctionValue * Math.sin(col.collisionAngle));
	
					boss.px = boss.px + (col.correctionValue * Math.cos(col.collisionAngle));
					boss.py = boss.py + (col.correctionValue * Math.sin(col.collisionAngle));
				}
			}
			
			for (NPC npc : world.map.activeRoom.npcs) {
				Collision col = Collision.collision(npc, world.player);
				if (col != null) {
					world.player.px = world.player.px - (col.correctionValue * Math.cos(col.collisionAngle));
					world.player.py = world.player.py - (col.correctionValue * Math.sin(col.collisionAngle));
				}
	
			}
			for (GridCell<Room> roomCell : world.map.rooms) {
				if(!roomCell.isEmpty())
					roomCell.getContent().update();
			}
			for (TriggerMap triggerMap : world.triggerMaps) {
				triggerMap.update();
			}


			ArrayList<Item> cpy = new ArrayList<Item>(world.map.activeRoom.items);
			for (Item item : cpy) {
				item.update();
			}
	
			boolean isPlayerInteracting = false;
			for (NPC npc : world.map.activeRoom.npcs) {
				if (npc.isInTriggerZone()) {
					npc.interact();
					isPlayerInteracting = true;
					break;
				} else
					isPlayerInteracting = false;
			}
			if (!isPlayerInteracting) {
				world.huds.get("npc").setIsShown(false);
				world.huds.get("npc").setInteractable(false);
			}
		}
	}
}
