package com.wyatt92.games.model.entities;

import com.wyatt92.games.controller.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class BombBlastManager {
    private Handler handler;
    private ArrayList<Blast> blasts;

    public BombBlastManager(Handler handler) {
        this.handler = handler;
        blasts = new ArrayList<>();
    }

    public void update()
    {
        Iterator<Blast> it = blasts.iterator();
        while(it.hasNext()) {
            Blast b = it.next();
            b.update();
            if(b.isDestroyed()) {
                it.remove();
            }
        }
    }

    public void draw(Graphics g)
    {
        for(Blast i : blasts)
            i.draw(g);
    }

    public void addBlast(Blast b) {
        b.setHandler(handler);
        blasts.add(b);
    }
}
