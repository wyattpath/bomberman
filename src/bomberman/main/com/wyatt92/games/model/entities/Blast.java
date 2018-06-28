package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.Animation;
import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.tiles.Tile;

import java.awt.*;

/**
 * This class
 */
public class Blast extends StaticEntity
{
    private long lastTime;
    private float countdown;
    private float waitTime;
    private static Animation animBlast;



    protected Rectangle bounds;

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

        animBlast = new Animation(200, Assets.blast);
    }

    @Override
    public void update()
    {
        animBlast.update();

        countdown -= System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(countdown < 0) {
            countdown = waitTime;
            setActive(false);
        }
    }

    @Override
    public void draw(Graphics g)
    {
        g.drawImage(animBlast.getCurrentFrame(),(int)super.x- Tile.TILEWIDTH/2,(int)super.y- Tile.TILEHEIGHT/2,Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
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
