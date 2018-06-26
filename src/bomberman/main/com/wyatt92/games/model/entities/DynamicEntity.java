package com.wyatt92.games.model.entities;


import com.wyatt92.games.model.Game;
import com.wyatt92.games.model.Model;
import com.wyatt92.games.model.tiles.Tile;

/**
 * The Dynamic Entity is an entity that can be moved around the world.
 *
 */
public abstract class DynamicEntity extends Entity
{
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CHARACTER_WIDTH = 64;
    public static final int DEFAULT_CHARACTER_HEIGHT = 64;

    protected float speed;
    protected float xMove, yMove;


    public DynamicEntity(float x, float y, int width, int height) {
        super( x, y, width, height);
        speed = DEFAULT_SPEED;
    }

    public float getxMove()
    {
        return xMove;
    }

    public void setxMove(float xMove)
    {
        this.xMove = xMove;
    }

    public float getyMove()
    {
        return yMove;
    }

    public void setyMove(float yMove)
    {
        this.yMove = yMove;
    }

}
