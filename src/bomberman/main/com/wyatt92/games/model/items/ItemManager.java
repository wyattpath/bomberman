package com.wyatt92.games.model.items;

import com.wyatt92.games.controller.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemManager
{
    private Handler handler;
    private ArrayList<Item> items;

    public ItemManager(Handler handler) {
        this.handler = handler;
        items = new ArrayList<>();
    }


    public void update()
    {
        Iterator<Item> it = items.iterator();
        while(it.hasNext()) {
            Item i = it.next();
            i.update();
            if(i.isPickedUp()) {
                it.remove();
            }
        }
    }


    public void draw(Graphics g)
    {
        for(Item i : items)
            i.draw(g);
    }

    public void addItem(Item i) {
        i.setHandler(handler);
        items.add(i);
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
}
