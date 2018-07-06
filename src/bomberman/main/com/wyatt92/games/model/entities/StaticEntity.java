package com.wyatt92.games.model.entities;

/**
 * Static Entities can't be moved.
 */
abstract class StaticEntity extends Entity
{
    StaticEntity(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

}
