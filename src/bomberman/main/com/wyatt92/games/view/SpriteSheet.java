package com.wyatt92.games.view;

import java.awt.image.BufferedImage;

/**
 * Crops an image.
 */
class SpriteSheet {

    private final BufferedImage sheet;

    SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }

    BufferedImage crop(int x, int y, int width, int height) {
        return sheet.getSubimage(x,y, width, height);
    }

    int getWidth(){
        return sheet.getWidth();
    }

    int getHeight(){
        return sheet.getHeight();
    }
}
