package com.wyatt92.games.view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * An asset is the representation of any object that can be used.
 * It can come from outside sources such as audio files or images.
 */
public class SpriteLibrary
{

    private static final int width = 32, height = 32;
    private static int bgCount, logoCount, gameOverCount;

    static BufferedImage dirt, grass, wall, stone;
    static BufferedImage[] bombStrength, bombCount, playerSpeed;
    static BufferedImage[] btn_start,btn_quit;
    static BufferedImage[] p1_down, p1_up, p1_left, p1_right;
    static BufferedImage[] p2_down, p2_up, p2_left, p2_right;
    static BufferedImage[] bomb;
    static BufferedImage[] blast;
    static BufferedImage[] bg;
    static BufferedImage[] logo;
    static BufferedImage[] gameOver;

    static {
        BufferedImage sheet = loadImage("sheet.png");
        BufferedImage sheetBW = loadImage("sheetBW.png");
        BufferedImage blastSheet = loadImage("blast_anim.png");
        BufferedImage bombSheet = loadImage("bomb_anim.png");
        BufferedImage powerUpsSheet = loadImage("powerups.png");
        BufferedImage menuSheet = loadImage("menu.png");



        dirt = sheet.getSubimage(width, 0, width, height);
        grass = sheet.getSubimage(width * 2, 0, width, height);
        wall = sheet.getSubimage(width * 3, 0, width, height);
        stone = sheet.getSubimage(0, height * 2, width-1, height-1);

        //Player 1
        p1_down = new BufferedImage[2];
        p1_up = new BufferedImage[2];
        p1_left = new BufferedImage[2];
        p1_right = new BufferedImage[2];

        p1_down[0] = sheet.getSubimage(width * 4, 0, width, height);
        p1_down[1] = sheet.getSubimage(width * 5, 0, width, height);
        p1_up[0] = sheet.getSubimage(width * 6, 0, width, height);
        p1_up[1] = sheet.getSubimage(width * 7, 0, width, height);
        p1_right[0] = sheet.getSubimage(width * 4, height, width, height);
        p1_right[1] = sheet.getSubimage(width * 5, height, width, height);
        p1_left[0] = sheet.getSubimage(width * 6, height, width, height);
        p1_left[1] = sheet.getSubimage(width * 7, height, width, height);

        p2_down = new BufferedImage[2];
        p2_up = new BufferedImage[2];
        p2_left = new BufferedImage[2];
        p2_right = new BufferedImage[2];

        p2_down[0] = sheet.getSubimage(width * 4, height * 2, width, height);
        p2_down[1] = sheet.getSubimage(width * 5, height * 2, width, height);
        p2_up[0] = sheet.getSubimage(width * 6, height * 2, width, height);
        p2_up[1] = sheet.getSubimage(width * 7, height * 2, width, height);
        p2_right[0] = sheet.getSubimage(width * 4, height * 3, width, height);
        p2_right[1] = sheet.getSubimage(width * 5, height * 3, width, height);
        p2_left[0] = sheet.getSubimage(width * 6, height * 3, width, height);
        p2_left[1] = sheet.getSubimage(width * 7, height * 3, width, height);

//        stone = new BufferedImage[2];
//        stone[1] = sheetBW.crop(0, height * 2, width-1, height-1);
        //        stone[1] = sheet.crop(1, height * 2+1, width-1, height-1);

        bomb = new BufferedImage[2];
        bomb[0] = bombSheet.getSubimage(0, 0, width*8, height*8);
        bomb[1] = bombSheet.getSubimage(width*8, 0, width*8, height*8);

        blast = new BufferedImage[2];
//        blast[0] = blastSheet.crop(0,0,width,height);
        blast[0] = blastSheet.getSubimage(width,0,width,height);
        blast[1] = blastSheet.getSubimage(0,height,width,height);

        btn_start = new BufferedImage[2];
        btn_start[0] = menuSheet.getSubimage(0, 0, 228, 32);
        btn_start[1] = menuSheet.getSubimage(230, 0, 228, 32);

        btn_quit = new BufferedImage[2];
        btn_quit[0] = menuSheet.getSubimage(0, 70, 228, 32);
        btn_quit[1] = menuSheet.getSubimage(230, 70, 228, 32);

        bombCount = new BufferedImage[2];
        bombCount[0] = powerUpsSheet.getSubimage(5, 0, 150, 150);
        bombCount[1] = powerUpsSheet.getSubimage(10, 2, 150, 150);

        bombStrength = new BufferedImage[2];
        bombStrength[0] = powerUpsSheet.getSubimage(155, 0, 150, 150);
        bombStrength[1] = powerUpsSheet.getSubimage(160, 2, 150, 150);

        playerSpeed = new BufferedImage[2];
        playerSpeed[0] = powerUpsSheet.getSubimage(315, 0, 150, 150);
        playerSpeed[1] = powerUpsSheet.getSubimage(320, 2, 150, 150);

        bgCount = 25;
        bg = new BufferedImage[bgCount];
        for(int i = 0; i < bgCount; i++){
            BufferedImage bgSheet = loadImage("/bg/bg"+i+".png");
            bg[i] = bgSheet.getSubimage(0,0,bgSheet.getWidth(), bgSheet.getHeight());
        }

        logoCount = 6;
        logo = new BufferedImage[logoCount];
        for(int i = 0; i < logoCount; i++){
            BufferedImage logoSheet = loadImage("/logo/logo"+i+".png");
            logo[i] = logoSheet.getSubimage(0,0,logoSheet.getWidth(), logoSheet.getHeight());
        }

        gameOverCount = 6;
        gameOver = new BufferedImage[gameOverCount];
        for(int i = 0; i < gameOverCount; i++){
            BufferedImage logoSheet = loadImage("/gameover/go"+i+".png");
            gameOver[i] = logoSheet.getSubimage(0,0,logoSheet.getWidth(), logoSheet.getHeight());
        }



    }

    private static BufferedImage loadImage(String path) {
        try {

            return ImageIO.read(SpriteLibrary.class.getClassLoader().getResourceAsStream("./images/" + path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
