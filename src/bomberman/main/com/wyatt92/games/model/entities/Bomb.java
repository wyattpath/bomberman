package com.wyatt92.games.model.entities;

import com.wyatt92.games.controller.Handler;
import com.wyatt92.games.model.Assets;

import java.awt.*;
import java.util.Timer;


public class Bomb extends StaticEntity
{
    private long startTime;
    private float countdown;
    public Bomb(Handler handler, float x, float y, int width, int height)
    {
        super(handler, x, y, width, height);
        startTime = System.currentTimeMillis();
        countdown = 2f;
    }

    @Override
    public void update()
    {
    }

    @Override
    public void draw(Graphics g)
    {
        g.drawImage(Assets.bomb, (int)x, (int) y, width, height, null);
    }

    @Override
    protected void destroy()
    {

    }
}
