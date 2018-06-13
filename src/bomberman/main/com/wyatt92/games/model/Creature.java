package com.wyatt92.games.model;

import com.wyatt92.games.controller.Handler;

/**
 * The Pawn is the physical representation of a player or an AI within the world.
 *
 */
public abstract class Creature extends Entity
{
    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CHARACTER_WIDTH = 64;
    public static final int DEFAULT_CHARACTER_HEIGHT = 64;

    protected int health;
    protected float speed;
    protected float xMove, yMove;

    private enum Direction {UP, DOWN, LEFT, RIGHT};
    private Direction mDirection;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
    }

    public void move() {
        x += xMove;
        y += yMove;
    }

    // GETTERS and SETTERS
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
