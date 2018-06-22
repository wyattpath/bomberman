package com.wyatt92.games.model.entities;

import com.wyatt92.games.controller.Handler;
import com.wyatt92.games.model.Model;
import com.wyatt92.games.model.tiles.Tile;

import java.awt.*;

/**
 * An Entity is any object that can be placed into a level.
 * Entities can be created (spawned) and destroyed through gameplay code
 */
public abstract class Entity implements Model
{
    public static final int DEFAULT_HEALTH = 3;
    protected Handler handler;
    protected float x,y,xOffset, yOffset;
    protected int width, height;
    protected int health;
    protected boolean active = true;
    protected Rectangle bounds;
    protected Point centerPoint;

    /**
     *
     * @param handler
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Entity(Handler handler, float x, float y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        xOffset = width/2;
        yOffset = height/2;
        health = DEFAULT_HEALTH;
        centerPoint = new Point((int)x/width * width + width/2,(int) y/height * height + height/2);

        bounds = new Rectangle(0,0, width, height);
    }

    @Override
    public abstract void update();

    @Override
    public abstract void draw(Graphics g);

    public void hurt(int amount) {
        health -= amount;
        if(health <= 0) {
            active = false;
            destroy();
        }
    }

    protected abstract void destroy();

    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        for(Entity e : handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this)){
                continue;
            }
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
                return true;
            }
        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

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

    public static int getDefaultHealth()
    {
        return DEFAULT_HEALTH;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }


    public Point getCenterPoint(){
        return new Point((int)(x+xOffset)/width * width + width/2,(int) (y+yOffset)/height * height + height/2);
    }
}
