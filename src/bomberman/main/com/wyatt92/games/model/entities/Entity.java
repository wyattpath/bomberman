package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.Model;
import com.wyatt92.games.model.Game;

import java.awt.*;

/**
 * An Entity is any object that can be placed into a level.
 * Entities can be created (spawned) and destroyed through GamePlay.
 * Loses health if it gets hurt and consequently can be destroyed if the health drops to 0;
 * If not set to active it gets removed by the EntityManager.
 * All entities have a default health that can be changed individually for subclasses.
 * Can collide with Tiles and other Entities.
 */
public abstract class Entity
{
    public static final int DEFAULT_HEALTH = 3;
    protected Model model;
    protected float x,y,xOffset, yOffset;
    protected int width, height;
    protected int health;
    protected boolean active = true;
    protected Rectangle bounds;
    protected Point centerPoint;

    /**
     * Constructor
     *
     * @param model world where the entity should be created
     * @param x x-coordinate of entity
     * @param y y-coordinate of entity
     * @param width width of entity
     * @param height height of entity
     */
    public Entity(Model model, float x, float y, int width, int height) {
        this.model = model;
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


    public abstract void update();


    public abstract void draw(Graphics g);

    public void hurt(int amount) {
        health -= amount;
        if(health <= 0) {
            setActive(false);
            destroy();
        }
    }

    public abstract void destroy();



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
