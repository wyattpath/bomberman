package com.wyatt92.games.model.items;

import com.wyatt92.games.view.Animation;
import com.wyatt92.games.model.entities.Player;


import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Grants a player additional abilities when picked up.
 */
public abstract class Item
{
    public static Item[] items = new Item[10];
    public static Item bombStrengthItem = new BombStrengthItem(1);
    public static Item bombCountItem = new MaxBombsItem(2);
    public static Item playerSpeedItem = new PlayerSpeedItem(3);


    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;

    protected Animation animation;
    protected String name;
    protected final int id;

    public Rectangle getBounds()
    {
        return bounds;
    }

    public void setBounds(Rectangle bounds)
    {
        this.bounds = bounds;
    }

    protected Rectangle bounds;

    protected int x, y, count;
    protected boolean pickedUp = false;

    /**
     * Constructor
     *
     * @param images animationframes as BufferedImages
     * @param name name as String
     * @param id id as Integer
     */
    public Item(BufferedImage[] images, String name, int id){
        this.animation = new Animation(500,images);
        this.name = name;
        this.id = id;
        count = 1;

        bounds = new Rectangle(x,y, ITEMWIDTH, ITEMHEIGHT);

        items[id] = this;
    }


    public void update() {
        animation.update();
    }




    public void draw(Graphics g){
            g.drawImage(animation.getCurrentFrame(),x, y, ITEMWIDTH, ITEMHEIGHT, null);

    }



    public abstract Item createNew(int x, int y);

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }


    public abstract void addEffect(Player player);

    // GETTERS and SETTERS

    public Animation getAnimation()
    {
        return animation;
    }

    public void setAnimation(Animation animation)
    {
        this.animation = animation;
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

    public void setPickedUp(boolean pickedUp)
    {
        this.pickedUp = pickedUp;
    }
}
