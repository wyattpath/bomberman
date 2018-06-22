package com.wyatt92.games.model.ui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject

{
    private BufferedImage[] images;
    private Clicker clicker;

    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, Clicker clicker)
    {
        super(x, y, width, height);
        this.images = images;
        this.clicker = clicker;
    }

    @Override
    public void update()
    {

    }

    @Override
    public void draw(Graphics g)
    {
        if(hovering)
            g.drawImage(images[1], (int) x, (int) y, width, height, null);
        else
            g.drawImage(images[0], (int) x, (int) y, width, height, null);
    }

    @Override
    public void onClick()
    {
        clicker.onClick();
    }
}

