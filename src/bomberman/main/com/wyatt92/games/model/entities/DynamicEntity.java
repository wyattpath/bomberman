package com.wyatt92.games.model.entities;

/**
 * The Dynamic Entity is an entity that can be moved around the world.
 *
 */
public abstract class DynamicEntity extends Entity
{
    static final float DEFAULT_SPEED = 3.0f;
    static final int DEFAULT_CHARACTER_WIDTH = 64;
    static final int DEFAULT_CHARACTER_HEIGHT = 64;

    float speed;
    float xMove, yMove;


    DynamicEntity(float x, float y, int width, int height) {
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
