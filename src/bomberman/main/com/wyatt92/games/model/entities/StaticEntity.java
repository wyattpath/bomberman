package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.Game;
import com.wyatt92.games.model.Model;

/**
 * Static Entities can't be moved.
 */
public abstract class StaticEntity extends Entity
{
    public StaticEntity(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

}
