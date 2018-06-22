package com.wyatt92.games.model.entities;

import com.wyatt92.games.controller.Handler;

public class PlayerOne extends Player
{
    public PlayerOne(Handler handler, float x, float y)
    {
        super(handler, x, y);
    }

    @Override
    protected void getInput()
    {
        xMove = 0;
        yMove = 0;

        if (handler.getKeyManager().W)
            yMove = -speed;
        if (handler.getKeyManager().S)
            yMove = speed;
        if (handler.getKeyManager().A)
            xMove = -speed;
        if (handler.getKeyManager().D)
            xMove = speed;
        if (handler.getKeyManager().SPACE)
        {
            placeBomb();
        }
    }


}
