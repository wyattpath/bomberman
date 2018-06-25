package com.wyatt92.games.model.states;

import com.wyatt92.games.model.World;
import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.ui.UIImageButton;
import com.wyatt92.games.model.ui.UIManager;
import com.wyatt92.games.model.utils.Animation;

import javax.sound.sampled.Clip;
import java.awt.*;

/**
 * Represents the Main menu of the game.
 * Adds Buttons and objects with the UIManager.
 * Updates the uiManager
 */
public class MenuState extends State
{
    private UIManager uiManager;
    private UIImageButton start;
    private UIImageButton quit;
    private Animation animBG;
    private Animation animLogo;




    /**
     *
     * @param world world of the MenuState
     * @param uiManager uiManager of World
     */
    public MenuState(World world, UIManager uiManager) {
        super(world);
        this.uiManager = uiManager;

        start = new UIImageButton(400, 600, 228, 35, Assets.btn_start, () ->
        {
            if(world.isGameOver()){

                world.resetWorld();
            }
            State.setCurrentState(State.getGameState());
        });
        quit = new UIImageButton(400, 635, 228, 35, Assets.btn_quit, () ->
        {
            System.exit(0);
        });
//        animLogo = new UIAnimation(256,64,512, 256,Assets.logo);
        animLogo = new Animation(10000,Assets.logo);
        animLogo.setRandom(true);
//        animBG = new UIAnimation(0,0,world.getWidth(), world.getHeight(),Assets.bg);
        animBG = new Animation(5000,Assets.bg);
        animBG.setRandom(true);
        uiManager.addObject(start);
        uiManager.addObject(quit);
    }

    @Override
    public void update()
    {
        animLogo.update();
        animBG.update();
        uiManager.update();
    }

    @Override
    public void draw(Graphics g)
    {
        g.setColor(Color.BLACK);
        if(world!= null)
            g.fillRect(0,0,world.getWidth(), world.getWidth());
        g.drawImage(animBG.getCurrentFrame(), 0,0,world.getWidth(), world.getHeight(), null);
        uiManager.draw(g);
        g.drawImage(animLogo.getCurrentFrame(),256,64,512, 256, null);
    }

    @Override
    protected void playLoopMusic()
    {
        Assets.menu_bgMusic.start();
        Assets.menu_bgMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Override
    protected void stopLoopMusic()
    {
        Assets.menu_bgMusic.stop();
    }


}
