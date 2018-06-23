package com.wyatt92.games.model.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation
{
    private int animSpeed, index;
    private long lastTime, timer;
    private BufferedImage[] frames;

    public Animation(int animSpeed, BufferedImage[] frames){
    this.animSpeed = animSpeed;
    this.frames = frames;
    index = 0;
    timer = 0;
    lastTime = System.currentTimeMillis();
    }

    public void update(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(timer > animSpeed) {
            index++;
            timer = 0;
            if(index >= frames.length){
                index = 0;
            }
        }
    }


    public BufferedImage getCurrentFrame() {
    return frames[index];
    }
}
