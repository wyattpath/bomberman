package com.wyatt92.games.model.entities;

import com.wyatt92.games.controller.Handler;
import com.wyatt92.games.model.Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class BombManager
{
    private Handler handler;
    private ArrayList<Bomb> bombs;

    public BombManager(Handler handler) {
        this.handler = handler;
        bombs = new ArrayList<>();
    }

    public void update()
    {
        Iterator<Bomb> it = bombs.iterator();
        while(it.hasNext()) {
            Bomb b = it.next();
            b.update();
            if(b.isDestroyed()) {
                it.remove();
            }
        }
    }

    public void draw(Graphics g)
    {
        for(Bomb i : bombs)
            i.draw(g);
    }

    public void addBomb(Bomb b) {
        b.setHandler(handler);
        bombs.add(b);
    }
}
