package com.wyatt92.games.model.ui;

import com.wyatt92.games.model.utils.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class UIAnimation extends UIObject
{
    private BufferedImage[] images;
    private Animation anim;

    public UIAnimation(float x, float y, int width, int height, BufferedImage[] images)
    {
        super(x, y, width, height);
        this.images = images;
        anim = new Animation(5000, images);

    }

    @Override
    public void update()
    {
        anim.update();
    }

    @Override
    public void draw(Graphics g)
    {
        g.drawImage(anim.getCurrentFrame(), (int)x,(int)y,width, height, null);
    }

    @Override
    public void onClick()
    {

    }

    public void setAnim(Animation anim)
    {
        this.anim = anim;
    }

    public Animation getAnim()
    {
        return anim;
    }
}
