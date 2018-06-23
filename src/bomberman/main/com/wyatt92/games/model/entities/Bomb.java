package com.wyatt92.games.model.entities;


import com.wyatt92.games.model.Animation;
import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.World;
import com.wyatt92.games.model.tiles.Tile;
import com.wyatt92.sounds.Sound;

import java.awt.*;


public class Bomb extends StaticEntity{
    private long lastTime;
    private float countdown;
    private static float waitTime;
    private static Animation animBomb;
    private boolean destroyed = false;

    protected static World world;
    protected Rectangle bounds;
    protected int x, y;
    protected int bombStrength;

    public static Bomb bomb;
    public static final int BOMBWIDTH = 64, BOMBHEIGHT = 64;


    public Bomb(World world, float x, float y, int bombStrength)
    {
        super(world, x, y, BOMBWIDTH, BOMBHEIGHT);
//        bomb = new Bomb(handler, x, y, BOMBWIDTH, BOMBHEIGHT);
//        this.texture = texture;
        this.bombStrength = bombStrength;
        bounds = new Rectangle((int) x,(int) y, BOMBWIDTH, BOMBHEIGHT);
        lastTime = System.currentTimeMillis();
        waitTime = 2000f;
        countdown = waitTime;

        animBomb = new Animation(500, Assets.bomb);
    }


    public void update()
    {
        //Animation
        animBomb.update();

        countdown -= System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(countdown < 0) {
            destroy();
            countdown = waitTime;
            destroyed = true;
        }
    }


    public void draw(Graphics g)
    {
        g.drawImage(animBomb.getCurrentFrame(), x - BOMBWIDTH/2, y-BOMBHEIGHT/2, BOMBWIDTH, BOMBHEIGHT, null);
    }

    protected void destroy()
    {
        placeBlast(x,y,0,0);
        placeBlast(x, y, Tile.TILEWIDTH,0); // nextTile on the right
        placeBlast(x, y, -Tile.TILEWIDTH, 0); // nextTile on the left
        placeBlast(x, y,0,-Tile.TILEHEIGHT); //nextTile above
        placeBlast(x, y,0,Tile.TILEHEIGHT); //nextTile below

    }

    private void placeBlast(int x, int y, int xOffset, int yOffset) {
        boolean stop = false;
        if(bombStrength>5)
            Sound.playSound("boom_L.wav");
        else if(bombStrength > 3)
            Sound.playSound("boom_M.wav");
        else Sound.playSound("boom_S.wav");

        for(int i = 0; i < bombStrength;i++)
        {
            if(stop || collisionWithTile(x + xOffset + i * xOffset, y + yOffset + i *yOffset))
                return;

            world.getBombBlastManager().addBlast(Blast.createNew(x + xOffset + i*xOffset, y + yOffset + i *yOffset));

            Rectangle tempBounds = new Rectangle();
            tempBounds.x = x + xOffset + i * xOffset;
            tempBounds.y = y + yOffset + i *yOffset;
            tempBounds.setSize(BOMBWIDTH, BOMBHEIGHT);
            for (Entity e : world.getEntityManager().getEntities())
            {
                if (e.equals(this))
                    continue;
                if (e.getCollisionBounds(32, 32).intersects(tempBounds))
                {
                    e.destroy();
                    e.hurt(3);
                    stop = true;
                }
            }

        }
    }

    public static Bomb createNew(int x, int y, int bombStrength){
        Bomb b = new Bomb(world, x, y, bombStrength);
        b.setPosition(x, y);
        return b;
    }

    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }

    private void checkCollision(int x, int y) {
        Rectangle tempBounds = new Rectangle(x,y, BOMBWIDTH, BOMBHEIGHT);
        for (Entity e : world.getEntityManager().getEntities()) {
            if (e.equals(this))
                continue;
            if (e.getCollisionBounds(32, 32).intersects(tempBounds)) {
                e.destroy();
                e.hurt(3);
            }
        }

    }

    protected boolean collisionWithTile(int x, int y) {
        System.out.println("TilePosition x = " + (x/Tile.TILEWIDTH)+ " y = " + (y/Tile.TILEHEIGHT));
        return world.getTile(x/Tile.TILEWIDTH,y/Tile.TILEHEIGHT).isSolid();
    }

    // GETTERS and SETTERS

    public World getHandler()
    {
        return world;
    }

    public void setHandler(World world)
    {
        this.world = world;
    }

    public boolean isDestroyed()
    {
        return destroyed;
    }

    public static float getWaitTime()
    {
        return waitTime;
    }
}
