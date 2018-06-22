package com.wyatt92.games.model.entities;

import com.wyatt92.games.controller.Handler;

public class PlayerTwo extends Player
{
    public PlayerTwo(Handler handler, float x, float y)
    {
        super(handler, x, y);
    }

    @Override
    protected void getInput()
    {
        xMove = 0;
        yMove = 0;

        if (handler.getKeyManager().UP)
            yMove = -speed;
        if (handler.getKeyManager().DOWN)
            yMove = speed;
        if (handler.getKeyManager().LEFT)
            xMove = -speed;
        if (handler.getKeyManager().RIGHT)
            xMove = speed;
        if (handler.getKeyManager().CTRL)
        {
            placeBomb();
        }
    }
}
