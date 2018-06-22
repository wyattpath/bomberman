package com.wyatt92.games.model.items;

import com.wyatt92.games.controller.Handler;
import com.wyatt92.games.model.Model;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Item implements Model
{
    public static Item[] items = new Item[5];
    public static Item bombStrengthItem = new BombStrengthItem(1);
    public static Item bombCountItem = new BombCountItem(2);


    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;

    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int id;

    protected Rectangle bounds;

    protected int x, y, count;
    protected boolean pickedUp = false;

    public Item(BufferedImage texture, String name, int id){
        this.texture = texture;
        this.name = name;
        this.id = id;
        count = 1;

        bounds = new Rectangle(x,y, ITEMWIDTH, ITEMHEIGHT);

        items[id] = this;
    }

    @Override
    public void update() {
        if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)){
            pickedUp = true;
            addEffect();
        }
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

    public abstract Item createNew(int x, int y);

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }


    protected void addEffect()
    {
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

    public boolean isPickedUp()
    {
        return pickedUp;
    }
}
