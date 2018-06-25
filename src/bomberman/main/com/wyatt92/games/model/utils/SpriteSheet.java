package com.wyatt92.games.model.utils;

import java.awt.image.BufferedImage;

/**
 * Crops an image.
 */
public class SpriteSheet {

    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }

    public BufferedImage crop(int x, int y, int width, int height) {
        return sheet.getSubimage(x,y, width, height);
    }

    public int getWidth(){
        return sheet.getWidth();
    }

    public int getHeight(){
        return sheet.getHeight();
    }
}
