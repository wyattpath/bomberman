package com.wyatt92.games.model.entities;

import com.wyatt92.games.controller.Handler;
import com.wyatt92.games.model.Assets;

import java.awt.*;

public class Player extends DynamicEntity
{

//    private boolean isMoving = false;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, DynamicEntity.DEFAULT_CHARACTER_WIDTH, DynamicEntity.DEFAULT_CHARACTER_HEIGHT);

        bounds.x = 16;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;
    }

    @Override
    public void update()
    {
        getInput();
        move();
        

    }

    private void placeBomb()
    {
        //TODO
//        BombBlast blast = new BombBlast(handler,x,y,width, height);

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
        if(handler.getKeyManager().SPACE){
            placeBomb();
        }


    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Assets.player, (int) x ,(int) y, width, height,null);

//        g.setColor(Color.red);
//        g.fillRect((int)(x + bounds.x),(int)(y + bounds.y), bounds.width, bounds.height);
    }

    @Override
    protected void destroy()
    {
        System.out.println("You lose!");
    }

}
