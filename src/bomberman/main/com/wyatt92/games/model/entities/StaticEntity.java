package com.wyatt92.games.model.entities;

import com.wyatt92.games.controller.Handler;

public abstract class StaticEntity extends Entity
{
    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }

}
