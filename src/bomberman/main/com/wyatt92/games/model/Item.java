package com.wyatt92.games.model;

import com.wyatt92.games.controller.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item implements Model
{
    public static Item[] items = new Item[256];
    public static Item powerupItem = new Item(Assets.powerup, "Powerup", 0);

    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32, PICKED_UP = -1;

    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int id;

    protected int x, y, count;

    public Item(BufferedImage texture, String name, int id){
        this.texture = texture;
        this.name = name;
        this.id = id;
        count = 1;

        items[id] = this;
    }

    public void update() {

    }

    @Override
    public void draw(Graphics g){
        if(handler != null)
            draw(g, x, y);
    }

    public void draw(Graphics g, int x, int y)
    {
        g.drawImage(texture,x, y, ITEMWIDTH, ITEMHEIGHT, null);
    }

    public Item createNew(int x, int y){
        Item i = new Item(texture, name, id);
        i.setPosition(x,y);
        return i;
    }
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
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

    public BufferedImage getTexture()
    {
        return texture;
    }

    public void setTexture(BufferedImage texture)
    {
        this.texture = texture;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }
}
