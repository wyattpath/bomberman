package com.wyatt92.games.model.entities;

import com.wyatt92.games.controller.Handler;
import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.entities.StaticEntity;
import com.wyatt92.games.model.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.Timer;


public class Bomb extends StaticEntity{
    private long startTime;
    private float countdown;
    public static Bomb bomb;
    private boolean destroyed = false;

    protected static Handler handler;
    protected static BufferedImage texture;

    public static final int BOMBWIDTH = 32, BOMBHEIGHT = 32;

    protected Rectangle bounds;
    protected int x, y;

    public Bomb(Handler handler, float x, float y, int width, int height)
    {
        super(handler, x, y, BOMBWIDTH, BOMBHEIGHT);
//        bomb = new Bomb(handler, x, y, BOMBWIDTH, BOMBHEIGHT);
//        this.texture = texture;
        bounds = new Rectangle((int) x,(int) y, BOMBWIDTH, BOMBHEIGHT);
        startTime = System.currentTimeMillis();
        countdown = 2f;
    }


    public void update()
    {
    }


    public void draw(Graphics g)
    {
        g.drawImage(Assets.bomb, x, y, BOMBWIDTH, BOMBHEIGHT, null);
    }

    protected void destroy()
    {

    }

    public static Bomb createNew(int x, int y){
        Bomb b = new Bomb(handler, x, y, BOMBWIDTH, BOMBHEIGHT);
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
