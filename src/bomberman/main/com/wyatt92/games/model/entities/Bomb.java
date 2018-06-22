package com.wyatt92.games.model.entities;

import com.wyatt92.games.controller.Handler;
import com.wyatt92.games.model.Animation;
import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.tiles.Tile;

import java.awt.*;


public class Bomb extends StaticEntity{
    private long lastTime;
    private float countdown;
    private float waitTime;
    private static Animation animBomb;
    private boolean destroyed = false;

    protected static Handler handler;
    protected Rectangle bounds;
    protected int x, y;

    public static Bomb bomb;
    public static final int BOMBWIDTH = 64, BOMBHEIGHT = 64;


    public Bomb(Handler handler, float x, float y)
    {
        super(handler, x, y, BOMBWIDTH, BOMBHEIGHT);
//        bomb = new Bomb(handler, x, y, BOMBWIDTH, BOMBHEIGHT);
//        this.texture = texture;
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
            placeBlast(x + Tile.TILEWIDTH, y); // nextTile on the right
            placeBlast(x - Tile.TILEWIDTH, y); // nextTile on the left
            placeBlast(x, y - Tile.TILEHEIGHT); //nextTile above
            placeBlast(x, y + Tile.TILEHEIGHT); //nextTile below
    }

    private void placeBlast(int x, int y) {
        if(!collisionWithTile(x, y)) {
            handler.getWorld().getBombBlastManager().addBlast(Blast.createNew(x, y));
        }
    }

    public static Bomb createNew(int x, int y){
        Bomb b = new Bomb(handler, x, y);
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

    private boolean checkCollision() {
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this))
                continue;
            if (e.getCollisionBounds(32, 32).intersects(bounds)) {
                return true;
            }
        }
        return false;

    }

    protected boolean collisionWithTile(int x, int y) {
        System.out.println("TilePosition x = " + (x/Tile.TILEWIDTH)+ " y = " + (y/Tile.TILEHEIGHT));
        return handler.getWorld().getTile(x/Tile.TILEWIDTH,y/Tile.TILEHEIGHT).isSolid();
    }

    // GETTERS and SETTERS

    public Handler getHandler()
    {
        return handler;
    }

    public void setHandler(Handler handler)
    {
        this.handler = handler;
    }

    public boolean isDestroyed()
    {
        return destroyed;
    }
}
