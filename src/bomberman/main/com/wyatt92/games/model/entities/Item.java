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
    private ArrayList<BufferedImage[]> images;
    private int id;

    protected Animation animation;
    protected Rectangle bounds;
    protected boolean pickedUp = false;

    /**
     * Constructor
     *
     */
    public Item(float x, float y ,int id){
        super((int)x, (int)y, ITEMWIDTH, ITEMHEIGHT);
        this.id = id;
        images = new ArrayList<>();
        images.add(Assets.playerSpeed);
        images.add(Assets.bombStrength);
        images.add(Assets.bombCount);

        this.animation = new Animation(500,images.get(id));
        bounds = new Rectangle((int)x,(int) y, ITEMWIDTH, ITEMHEIGHT);
    }

    public void update() {
        animation.update();
    }

    public void draw(Graphics g){
            g.drawImage(animation.getCurrentFrame(),(int)x, (int)y, ITEMWIDTH, ITEMHEIGHT, null);
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


    public Rectangle getBounds()
    {
        return bounds;
    }

    public void setBounds(Rectangle bounds)
    {
        this.bounds = bounds;
    }
}
