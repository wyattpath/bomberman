package com.wyatt92.games.model;

import com.wyatt92.games.controller.Game;

import java.awt.*;

public class Player extends Creature
{

//    private boolean isMoving = false;
    private Game game;

    public Player(Game game,  float x, float y) {
        super(x, y, Creature.DEFAULT_CHARACTER_WIDTH, Creature.DEFAULT_CHARACTER_HEIGHT);
        this.game = game;

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

        if(game.getKeyManager().UP)
            yMove = -speed;
        if(game.getKeyManager().DOWN)
            yMove = speed;
        if(game.getKeyManager().LEFT)
            xMove = -speed;
        if(game.getKeyManager().RIGHT)
            xMove = speed;


    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Assets.player, (int) x ,(int) y, width, height,null);
    }

}
