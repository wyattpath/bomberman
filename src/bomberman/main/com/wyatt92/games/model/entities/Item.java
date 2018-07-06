package com.wyatt92.games.model.entities;


import java.awt.*;

/**
 * Grants a player additional abilities when picked up.
 */
public class Item extends StaticEntity
{
    private static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;

    private int id;
    private Rectangle bounds;
    private boolean pickedUp = false;

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
