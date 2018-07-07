package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.Tile;
import java.awt.*;

/**
 * A Blast is placed after the countdown of a bomb reaches 0.
 * It damages all entities on the same tile.
 */
public class Blast extends StaticEntity
{
    private long lastTime;
    private float countdown;
    private final float waitTime;

    private Rectangle bounds;

    /**
     *
     * @param x x-coordinate in float
     * @param y y-coordinate in float
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





}
