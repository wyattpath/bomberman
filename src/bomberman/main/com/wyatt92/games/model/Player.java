package com.wyatt92.games.model;

import com.wyatt92.games.controller.Game;
import com.wyatt92.games.controller.Handler;

import java.awt.*;

public class Player extends Creature
{

//    private boolean isMoving = false;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CHARACTER_WIDTH, Creature.DEFAULT_CHARACTER_HEIGHT);
    }

    @Override
    public void update()
    {
        getInput();
        move();
    }
    private void getInput(){
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().UP)
            yMove = -speed;
        if(handler.getKeyManager().DOWN)
            yMove = speed;
        if(handler.getKeyManager().LEFT)
            xMove = -speed;
        if(handler.getKeyManager().RIGHT)
            xMove = speed;


    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Assets.player, (int) x ,(int) y, width, height,null);
    }

}
