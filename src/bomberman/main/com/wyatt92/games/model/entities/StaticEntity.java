package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.Game;

/**
 * Static Entities can't be moved.
 */
public abstract class StaticEntity extends Entity
{
    public StaticEntity(Game game, float x, float y, int width, int height) {
        super(game, x, y, width, height);
    }

}
