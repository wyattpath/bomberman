package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.World;

/**
 * Static Entities can't be moved.
 */
public abstract class StaticEntity extends Entity
{
    public StaticEntity(World world, float x, float y, int width, int height) {
        super(world, x, y, width, height);
    }

}
