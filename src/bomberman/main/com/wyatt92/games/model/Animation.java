package com.wyatt92.games.model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation implements Model
{
    private int speed, index;
    private long lastTime, timer;
    private BufferedImage[] frames;

    public Animation(int speed, BufferedImage[] frames){
    this.speed = speed;
    this.frames = frames;
    index = 0;
    timer = 0;
    lastTime = System.currentTimeMillis();
    }

    @Override
    public void update(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(timer > speed) {
            index++;
            timer = 0;
            if(index >= frames.length){
                index = 0;
            }
        }
    }

    @Override
    public void draw(Graphics g)
    {

    }

    public BufferedImage getCurrentFrame() {
    return frames[index];
    }
}
