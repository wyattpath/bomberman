package com.wyatt92.games.model.entities;

import com.wyatt92.games.controller.Handler;
import com.wyatt92.games.model.Assets;

import java.awt.*;

public class BombBlast extends StaticEntity
{
    Rectangle cb;
    Rectangle ar;
    int arSize = 20;

    public BombBlast(Handler handler, float x, float y, int width, int height)
    {
        super(handler, x, y, width, height);
        ar = getCollisionBounds(x,y);
        ar = new Rectangle();
        ar.width = arSize;
        ar.height = arSize;
        System.out.println("placed Bomb");

    }

    @Override
    public void update()
    {
//        checkCollision();
    }

    private void checkCollision()
    {
        for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0,0).intersects(ar)){
                e.destroy();
                System.out.println("entity destroyed");
            }else {
                this.destroy();
                System.out.println("no entity found");
            }
        }
    }

    @Override
    public void draw(Graphics g)
    {
        g.drawImage(Assets.blast,(int) x,(int) y,width, height, null);
        System.out.println("drawing");
    }

    @Override
    protected void destroy()
    {
        handler.getWorld().getEntityManager().getEntities().remove(this);
    }
}
