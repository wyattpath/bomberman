package com.wyatt92.games.model;

import com.wyatt92.games.controller.Handler;

import java.awt.*;

/**
 * An Actor is any object that can be placed into a level.
 * Actors can be created (spawned) and destroyed through gameplay code
 */
public abstract class Entity implements Model
{
    protected Handler handler;
    protected float x,y;
    protected int width, height;

    public Entity(Handler handler, float x, float y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public abstract void update();

    @Override
    public abstract void draw(Graphics g);

    // GETTERS AND SETTERS

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }
}
