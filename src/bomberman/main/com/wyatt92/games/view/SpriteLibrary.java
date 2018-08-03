package com.wyatt92.games.view;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.Buffer;

/**
 * An asset is the representation of any object that can be used.
 * It can come from outside sources such as audio files or images.
 */
public class SpriteLibrary
{

    private static final int width = 32, height = 32;
    private static int bgCount, bgMusicCount, goMusicCount, gMusicCount, logoCount, gameOverCount;

    static BufferedImage dirt, grass, wall, stone;
    static BufferedImage[] bombStrength, bombCount, playerSpeed;
    static BufferedImage[] btn_start,btn_quit;
    static BufferedImage[] player_down, player_up, player_left, player_right;
    static BufferedImage[] bomb;
    static BufferedImage[] blast;
    static BufferedImage[] bg;
    static BufferedImage[] logo;
    static BufferedImage[] gameOver;


    private static Clip menu_pauseFX, menu_selectFX, menu_cursorMoveFX;
    static Clip[] menu_bgMusic,game_bgMusic, gameOver_bgMusic;

    static void init() {
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

        //Animation
        player_down = new BufferedImage[2];
        player_up = new BufferedImage[2];
        player_left = new BufferedImage[2];
        player_right = new BufferedImage[2];

        player_down[0] = sheet.getSubimage(width * 4, 0, width, height);
        player_down[1] = sheet.getSubimage(width * 5, 0, width, height);
        player_up[0] = sheet.getSubimage(width * 6, 0, width, height);
        player_up[1] = sheet.getSubimage(width * 7, 0, width, height);
        player_right[0] = sheet.getSubimage(width * 4, height, width, height);
        player_right[1] = sheet.getSubimage(width * 5, height, width, height);
        player_left[0] = sheet.getSubimage(width * 6, height, width, height);
        player_left[1] = sheet.getSubimage(width * 7, height, width, height);

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


        //Sounds
        menu_selectFX = SoundLibrary.clipForLoopFactory("select.wav");
        menu_cursorMoveFX = SoundLibrary.clipForLoopFactory("cursor_move.wav");

        bgMusicCount = 2;
        menu_bgMusic = new Clip[bgMusicCount];
        for(int i = 0 ; i < bgMusicCount;i++)
            menu_bgMusic[i] = SoundLibrary.clipForLoopFactory("Undertale00"+ i +".wav");

        gMusicCount = 9;
        game_bgMusic = new Clip[gMusicCount];
        for(int i = 0 ; i < gMusicCount;i++)
            game_bgMusic[i] = SoundLibrary.clipForLoopFactory("music0"+ i +".wav");

        goMusicCount = 2;
        gameOver_bgMusic = new Clip[goMusicCount];
        for(int i = 0 ; i < goMusicCount;i++)
            gameOver_bgMusic[i]  = SoundLibrary.clipForLoopFactory("gomusic0"+ i +".wav");


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
