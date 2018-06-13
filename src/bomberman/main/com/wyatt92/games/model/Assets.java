package com.wyatt92.games.model;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 32, height = 32;

    public static BufferedImage player, dirt, grass, stone, tree;

    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("sheet.png"));

        player = sheet.crop(width * 5,0, width, height);
        dirt = sheet.crop(width, 0, width, height);
        grass = sheet.crop(width * 2, 0, width, height);
        stone = sheet.crop(width * 3, 0, width, height);
        tree = sheet.crop(0, height, width, height);

    }
}
