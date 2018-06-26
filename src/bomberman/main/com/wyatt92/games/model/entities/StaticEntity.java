package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.Game;
import com.wyatt92.games.model.Model;

/**
 * Static Entities can't be moved.
 */
public abstract class StaticEntity extends Entity
{
    public StaticEntity(Model model, float x, float y, int width, int height) {
        super(model, x, y, width, height);
    }

}
