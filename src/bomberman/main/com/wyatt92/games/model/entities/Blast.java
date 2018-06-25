package com.wyatt92.games.model.entities;


import com.wyatt92.games.model.utils.Animation;
import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.Game;
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

    public Rectangle getBounds()
    {
        return bounds;
    }

    public void setBounds(Rectangle bounds)
    {
        this.bounds = bounds;
    }

    protected Rectangle bounds;

    /**
     *
     * @param game
     * @param x
     * @param y
     */
    public Blast(Game game, float x, float y)
    {
        super(game, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
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
            destroy();
        }
    }

    @Override
    public void draw(Graphics g)
    {
        g.drawImage(animBlast.getCurrentFrame(),(int)super.x- Tile.TILEWIDTH/2,(int)super.y- Tile.TILEHEIGHT/2,Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
    }

    @Override
    public void destroy()
    {
        setActive(false);
    }


    // GETTERS and SETTERS
    public Point getPosition() {
        return new Point((int)super.x,(int)super.y);
    }

    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }

    public Game getWorld() {
        return game;

    }

    public void setWorld(Game game) {
        super.game = game;
    }

}
