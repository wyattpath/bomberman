package com.wyatt92.games.model;

import com.wyatt92.games.model.utils.ImageLoader;
import com.wyatt92.games.model.utils.SpriteSheet;
import com.wyatt92.resources.sounds.Sound;

import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;

/**
 * An asset is the representation of any object that can be used.
 * It can come from outside sources such as audio files or images.
 */
public class Assets {

    private static final int width = 32, height = 32;

    public static BufferedImage dirt, grass, wall, tree, stone, bombStrength, bombCount;
    public static BufferedImage[] btn_start,btn_quit;
    public static BufferedImage[] player_down, player_up, player_left, player_right;
    public static BufferedImage[] bomb;
    public static BufferedImage[] blast;

    public static Clip menu_bgMusic, menu_pauseFX, menu_selectFX, menu_cursorMoveFX;
    public static Clip game_bgMusic, game_bombSet, game_bombBoomS, game_bombBoomM, game_bombBoomL;

    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("sheet.png"));
        SpriteSheet blastSheet = new SpriteSheet((ImageLoader.loadImage("blast_anim.png")));
        SpriteSheet bombSheet = new SpriteSheet((ImageLoader.loadImage("bomb_anim.png")));
        SpriteSheet powerUpsSheet = new SpriteSheet(ImageLoader.loadImage("powerups.png"));
        SpriteSheet menuSheet = new SpriteSheet(ImageLoader.loadImage("menu.png"));

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

        bomb = new BufferedImage[2];
        bomb[0] = bombSheet.crop(0, 0, width*8, height*8);
        bomb[1] = bombSheet.crop(width*8, 0, width*8, height*8);

        blast = new BufferedImage[2];
        blast[0] = blastSheet.crop(0,0,width,height);
        blast[1] = blastSheet.crop(0,width,width,height);

        btn_start = new BufferedImage[2];
        btn_start[0] = menuSheet.crop(0, 0, 228, 35);
        btn_start[1] = menuSheet.crop(230, 0, 228, 35);

        btn_quit = new BufferedImage[2];
        btn_quit[0] = menuSheet.crop(0, 70, 228, 35);
        btn_quit[1] = menuSheet.crop(230, 70, 228, 35);

        bombCount = powerUpsSheet.crop(0, 0, 152, 152);
        bombStrength = powerUpsSheet.crop(152, 0, 152, 152);

        //Sounds
        menu_bgMusic = Sound.clipForLoopFactory("Undertale002.wav");
        menu_selectFX = Sound.clipForLoopFactory("select.wav");
        menu_cursorMoveFX = Sound.clipForLoopFactory("cursor_move.wav");
        game_bgMusic = Sound.clipForLoopFactory("Undertale036.wav");




    }
}
