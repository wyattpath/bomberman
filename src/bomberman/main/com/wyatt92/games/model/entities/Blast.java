package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.Tile;
import java.awt.*;

/**
 * This class
 */
public class Blast extends StaticEntity
{
    private long lastTime;
    private float countdown;
    private float waitTime;

    private Rectangle bounds;

    /**
     *
     * @param x
     * @param y
     */
    public Blast(float x, float y)
    {
        super( x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        bounds = getCollisionBounds(0f,0f);
        lastTime = System.currentTimeMillis();
        waitTime = 2000f;
        countdown = waitTime;


    }

    @Override
    public void update()
    {


        countdown -= System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(countdown < 0) {
            countdown = waitTime;
            setActive(false);
        }
    }




    // GETTERS and SETTERS

    public Rectangle getBounds()
    {
        return bounds;
    }

    public void setBounds(Rectangle bounds)
    {
        this.bounds = bounds;
    }




}
