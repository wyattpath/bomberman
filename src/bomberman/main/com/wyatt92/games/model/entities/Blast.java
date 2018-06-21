package com.wyatt92.games.model.entities;

import com.wyatt92.games.controller.Handler;
import com.wyatt92.games.model.Animation;
import com.wyatt92.games.model.Assets;
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

    protected static Handler handler;
    protected Rectangle bounds;
    protected int x, y;

    public Blast(Handler handler, float x, float y)
    {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
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
            System.out.println("blast inactive");
        }
    }

    private void checkCollision()
    {
        for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(32,32).intersects(bounds)){
                e.destroy();
                e.hurt(3);

            }else {
                this.destroy();
                System.out.println("no entity found");
            }
        }
    }

    @Override
    public void draw(Graphics g)
    {
        g.drawImage(animBlast.getCurrentFrame(),x- Tile.TILEWIDTH/2,y- Tile.TILEHEIGHT/2,Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
        System.out.println("drawing");
    }


    @Override
    protected void destroy()
    {
//        handler.getWorld().getEntityManager().getEntities().remove(this);
    }


    public static Blast createNew(int x, int y){
        Blast b = new Blast(handler, x, y);
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
    public static Handler getHandler() {
        return handler;

    }

    public static void setHandler(Handler handler) {
        Blast.handler = handler;
    }

    public boolean isDestroyed()
    {
        return destroyed;
    }
}
