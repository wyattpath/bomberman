package com.wyatt92.games.model;

import com.wyatt92.games.controller.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemManager implements Model
{
    private Handler handler;
    private ArrayList<Item> items;

    public ItemManager(Handler handler) {
        this.handler = handler;
        items = new ArrayList<>();
    }

    @Override
    public void update()
    {
        Iterator<Item> it = items.iterator();
        while(it.hasNext()) {
            Item i = it.next();
            i.update();
            if(i.getCount() == Item.PICKED_UP) {
                it.remove();
            }
        }
    }

    @Override
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
