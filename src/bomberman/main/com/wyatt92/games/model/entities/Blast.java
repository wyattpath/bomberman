package com.wyatt92.games.model.entities;


import com.wyatt92.games.model.utils.Animation;
import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.World;
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
     * @param world
     * @param x
     * @param y
     */
    public Blast(World world, float x, float y)
    {
        super(world, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        bounds = getCollisionBounds(0f,0f);
        lastTime = System.currentTimeMillis();
        waitTime = 2000f;
        countdown = waitTime;

        animBlast = new Animation(200, Assets.blast);
    }

    private void checkCollision()
    {
        for(Entity e : world.getEntityManager().getEntities()) {
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(32,32).intersects(bounds)){
                e.destroy();
                e.hurt(3);
            }
        }
    }



    public Blast createNew(int x, int y){
        Blast b = new Blast(super.world, x, y);
        b.setPosition(x, y);
        return b;
    }

    @Override
    public void update()
    {
        animBlast.update();
        checkCollision();

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
    protected void destroy()
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

    public World getWorld() {
        return world;

    }

    public void setWorld(World world) {
        super.world = world;
    }

}
