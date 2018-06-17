package com.wyatt92.games.model.entities;

import com.wyatt92.games.controller.Handler;
import com.wyatt92.games.model.Assets;

import java.awt.*;


public class Bomb extends StaticEntity{
    private long startTime;
    private float countdown;
    public static Bomb bomb;
    private boolean destroyed = false;

    protected static Handler handler;

    public static final int BOMBWIDTH = 32, BOMBHEIGHT = 32;

    protected Rectangle bounds;
    protected int x, y;

    public Bomb(Handler handler, float x, float y)
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
