package com.game.engine.physics;

import java.util.Timer;
import java.util.TimerTask;


/** State that the enemy can have */
enum State {
    ROAMING, IDLE, CHASING;
}

/** AI script of the enemy */
public class EnemyAI {
    /** Enemy controlled */
    Entity enemy;

    /** State of the enemy */
    State state;

    /** Zone in which the enemy can attack */
    private TriggerZone attackZone = new TriggerZone(20, 20);

    /** Whether or not the enemy can attack */
    boolean canAttack = true;

    

    /**
     * Creates an AI Script for a given enemy
     * @param enemy
     */
    public EnemyAI(Entity enemy) {
        this.enemy = enemy;
        state = State.ROAMING;
    }

    /** Update called every frame */
    public void update() {
        if(enemy.room != enemy.world.map.activeRoom) return;
        double[] newSpeed = moveTowards(enemy.px, enemy.py, enemy.world.player.px, enemy.world.player.py);
        enemy.vx = newSpeed[0];
        enemy.vy = newSpeed[1];
        if(enemy.isInTriggerZone(enemy.world.player,attackZone) && canAttack) {
            canAttack = false;
            enemy.attack(enemy.world.player);
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    canAttack = true;
                }
            };
            Timer timer = new Timer("Timer");
            long delay = 3000L;
            timer.schedule(task, delay);
        }
    }

    /** 
     * Calculates the x and y balance to move from v1 towards v2
     */
    public double[] moveTowards(double v1x, double v1y, double v2x, double v2y) {
        double adjacent= v2x-v1x;
        double opposite = v2y-v1y;
        double angle = Math.abs(Math.atan(opposite/adjacent));
        double xSign = adjacent < 0.0 ? -1.0 :  1.0 ;
        double ySign = opposite < 0.0 ? -1.0 :  1.0 ;
        return new double[] {
            Math.cos(angle) * xSign,
            Math.sin(angle) * ySign
        };
    }

}
