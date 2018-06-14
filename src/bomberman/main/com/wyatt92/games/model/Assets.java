package com.wyatt92.games.model;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Assets {

    private static final int width = 32, height = 32;

    public static BufferedImage player, dirt, grass, wall, tree, stone, bomb;
    public static BufferedImage[] btn_start;
    public static BufferedImage blast;

    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("sheet.png"));
        SpriteSheet blastSheet = new SpriteSheet((ImageLoader.loadImage("blast.png")));

        player = sheet.crop(width * 5,0, width, height);
        dirt = sheet.crop(width, 0, width, height);
        grass = sheet.crop(width * 2, 0, width, height);
        wall = sheet.crop(width * 3, 0, width, height);
        tree = sheet.crop(0, height, width, height);
        stone = sheet.crop(0, width * 2, width, height);
        bomb = sheet.crop(width, width, width, height);

        btn_start = new BufferedImage[2];
        btn_start[0] = sheet.crop(width * 6, height * 4, width * 2, height);
        btn_start[1] = sheet.crop(width * 6, height * 5, width * 2, height);

        blast = blastSheet.crop(0,0,width,height);

    }
}
