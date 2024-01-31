package com.game.engine.view;

import java.awt.Graphics;
import java.util.HashMap;

public abstract class Sprites {

    /** The activity */
    String activity;
    /**compteur interne */
    int iteration;

    /**
     *
     */
    public int num;

    /**
     *
     */
    public HashMap<String, Sprite> sprites;

    /**
     *
     * @return
     */
    public String chain() {
        return (activity + num);
    }

    /**
     *
     * @param x
     * @param y
     * @param g
     */
    public void draw(int x, int y, Graphics g) {
        Sprite s=sprites.get(chain());
        if (s == null)
            s = sprites.get("erreur");
        s.draw(g, x, y);
    }

    /**
     *
     * @param n
     */
    public void changeActivity(String n) {
        if(!activity.equals(n)) {
            activity = n;
            iteration = 0;
            num = 0;
        }
    }
    /** Returns the activity 
     * @return
    */
    public String getActivity() {
        return activity;
    }

    /**
     *
    */
    public abstract void animate();
}
