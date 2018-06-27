package com.wyatt92.games.model.items;

import com.wyatt92.games.model.entities.StaticEntity;
import com.wyatt92.games.view.Animation;
import com.wyatt92.games.model.entities.Player;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Grants a player additional abilities when picked up.
 */
public abstract class Item extends StaticEntity
{
    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;
    public static Item[] items = new Item[3];
    private static Item bombStrengthItem = new BombStrengthItem(0);
    private static Item playerSpeedItem = new PlayerSpeedItem(1);
    private static Item MaxBombsItem = new MaxBombsItem(2);
    int id;

    protected Animation animation;
    protected Rectangle bounds;
    protected int x, y, count;
    protected boolean pickedUp = false;

    /**
     * Constructor
     *
     */
    public Item(float x, float y, BufferedImage[] images, int id){
        super(x, y, ITEMWIDTH, ITEMHEIGHT);
        this.id = id;
        this.animation = new Animation(500,images);
        count = 1;

        items[id] = this;
        bounds = new Rectangle((int)x,(int) y, ITEMWIDTH, ITEMHEIGHT);
    }


    public void update() {
        animation.update();
    }

    public void draw(Graphics g){
            g.drawImage(animation.getCurrentFrame(),x, y, ITEMWIDTH, ITEMHEIGHT, null);
    }

    public abstract Item createNew(int x, int y);

    public Rectangle getBounds()
    {
        return bounds;
    }

    public void setBounds(Rectangle bounds)
    {
        this.bounds = bounds;
    }



    public abstract void addEffect(Player player);

    protected void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.bounds.x = x;
        this.bounds.y = y;
    }

    // GETTERS and SETTERS


    public int getId()
    {
        return id;
    }

    public boolean isPickedUp()
    {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp)
    {
        this.pickedUp = pickedUp;
    }

    public static Item[] getItems()
    {
        return items;
    }
}
