package com.wyatt92.games.model;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Assets {

    private static final int width = 32, height = 32;

    public static BufferedImage player, dirt, grass, wall, tree, stone, bomb, powerup;
    public static BufferedImage blast;
    public static BufferedImage[] btn_start;
    public static BufferedImage[] player_down, player_up, player_left, player_right;


    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("sheet.png"));
        SpriteSheet blastSheet = new SpriteSheet((ImageLoader.loadImage("blast.png")));
        SpriteSheet bombSheet = new SpriteSheet((ImageLoader.loadImage("bomb.png")));

        player_down = new BufferedImage[2];
        player_up = new BufferedImage[2];
        player_left = new BufferedImage[2];
        player_right = new BufferedImage[2];

        player_down[0] = sheet.crop(width * 4, 0, width, height);
        player_down[1] = sheet.crop(width * 5, 0, width, height);
        player_up[0] = sheet.crop(width * 6, 0, width, height);
        player_up[1] = sheet.crop(width * 7, 0, width, height);
        player_right[0] = sheet.crop(width * 4, height, width, height);
        player_right[1] = sheet.crop(width * 5, height, width, height);
        player_left[0] = sheet.crop(width * 6, height, width, height);
        player_left[1] = sheet.crop(width * 7, height, width, height);



        dirt = sheet.crop(width, 0, width, height);
        grass = sheet.crop(width * 2, 0, width, height);
        wall = sheet.crop(width * 3, 0, width, height);
        tree = sheet.crop(0, height, width, height);
        stone = sheet.crop(0, width * 2, width, height);
        bomb = bombSheet.crop(0, 0, width*2, height*2);

        btn_start = new BufferedImage[2];
        btn_start[0] = sheet.crop(width * 6, height * 4, width * 2, height);
        btn_start[1] = sheet.crop(width * 6, height * 5, width * 2, height);

        blast = blastSheet.crop(0,0,width,height);
        powerup = sheet.crop(width, height, width, height);


    }
}
