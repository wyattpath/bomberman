package com.wyatt92.games.model.entities;


import com.wyatt92.games.model.Animation;
import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.World;
import com.wyatt92.games.model.tiles.Tile;

import java.awt.*;

public class Blast extends StaticEntity
{
//    Rectangle cb;
//    Rectangle ar;
//    int arSize = 20;
    private long lastTime;
    private float countdown;
    private float waitTime;
    private static Animation animBlast;
    private boolean destroyed = false;

    protected static World world;
    protected Rectangle bounds;
    protected int x, y;

    public Blast(World world, float x, float y)
    {
        super(world, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
//        ar = getCollisionBounds(x,y);
//        ar = new Rectangle();
//        ar.width = arSize;
//        ar.height = arSize;
        System.out.println("placed Blast at: x = " + x + " y = " + y );
//        bounds = new Rectangle((int) x,(int) y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
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
        checkCollision();

        countdown -= System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(countdown < 0) {
//            destroy();
            countdown = waitTime;
            destroyed = true;
        }
    }

    private void checkCollision()
    {
        for(Entity e : world.getEntityManager().getEntities()) {
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(32,32).intersects(bounds)){
                e.destroy();
                e.hurt(3);

            }else {
                this.destroy();
            }
        }
    }

    @Override
    public void draw(Graphics g)
    {
        g.drawImage(animBlast.getCurrentFrame(),x- Tile.TILEWIDTH/2,y- Tile.TILEHEIGHT/2,Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
    }


    @Override
    protected void destroy()
    {
//        handler.getWorld().getEntityManager().getEntities().remove(this);
    }


    public static Blast createNew(int x, int y){
        Blast b = new Blast(world, x, y);
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
    // GETTERS and SETTERS
    public World getWorld() {
        return world;

    }

    public void setWorld(World world) {
        this.world = world;
    }

    public boolean isDestroyed()
    {
        return destroyed;
    }
}
