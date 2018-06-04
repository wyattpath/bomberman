package com.wyatt92.games.model;

import java.awt.*;

/**
 * An Actor is any object that can be placed into a level.
 * Actors can be created (spawned) and destroyed through gameplay code
 */
public abstract class Actor {
    protected float x,y;

    public Actor(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public abstract void update();

    public abstract void draw(Graphics g);
}
