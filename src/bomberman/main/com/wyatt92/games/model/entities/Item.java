package com.wyatt92.games.model.entities;

import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.Animation;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Grants a player additional abilities when picked up.
 */
public class Item extends StaticEntity
{
    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;

    private int id;


    protected Rectangle bounds;
    protected boolean pickedUp = false;

    /**
     * Constructor
     *
     */
    public Item(float x, float y ,int id){
        super((int)x, (int)y, ITEMWIDTH, ITEMHEIGHT);
        this.id = id;


        bounds = new Rectangle((int)x,(int) y, ITEMWIDTH, ITEMHEIGHT);
    }

    public void update() {

    }

    public void draw(Graphics g){
    }

    // GETTERS and SETTERS

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public boolean isPickedUp()
    {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp)
    {
        this.pickedUp = pickedUp;
    }


    public Rectangle getBounds()
    {
        return bounds;
    }

    public void setBounds(Rectangle bounds)
    {
        this.bounds = bounds;
    }

}
