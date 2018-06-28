package com.wyatt92.games.model.entities;

/**
 * Static Entities can't be moved.
 */
public abstract class StaticEntity extends Entity
{
    public StaticEntity(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

}
