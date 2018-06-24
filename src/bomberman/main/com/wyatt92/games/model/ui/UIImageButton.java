package com.wyatt92.games.model.ui;

import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.utils.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject

{
    private BufferedImage[] images;
    private Clicker clicker;
    long waitTime, countdown,lastTime;

    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, Clicker clicker)
    {
        super(x, y, width, height);
        this.images = images;
        this.clicker = clicker;

        lastTime = System.currentTimeMillis();
        waitTime = Assets.menu_cursorMoveFX.getFrameLength()/10;
    }

    @Override
    public void update()
    {

        if(hovering){
            countdown -= System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            System.out.println(countdown);
            if(countdown < 0)
            {
                Sound.playSound("cursor_move.wav");
                countdown = waitTime;
            }
        }
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
        Assets.menu_selectFX.start();
        clicker.onClick();
    }

}

