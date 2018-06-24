package com.wyatt92.games.model.states;

import com.wyatt92.games.model.World;
import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.entities.Player;
import com.wyatt92.games.model.ui.UIImageButton;
import com.wyatt92.games.model.ui.UIManager;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
    private World world;



    /**
     *
     * @param world world of the MenuState
     * @param uiManager uiManager of World
     */
    public MenuState(World world, UIManager uiManager) {
        super(world);
        this.world = world;
        this.uiManager = uiManager;

        start = new UIImageButton(400, 400, 228, 35, Assets.btn_start, () ->
        {
            if(world.isGameOver()){

                world.resetWorld();
            }
            State.setCurrentState(State.getGameState());
        });
        quit = new UIImageButton(400, 435, 228, 35, Assets.btn_quit, () -> System.exit(0));
        uiManager.addObject(start);
        uiManager.addObject(quit);
    }

    @Override
    public void update()
    {
        uiManager.update();
    }

    @Override
    public void draw(Graphics g)
    {
        g.setColor(Color.BLUE);
        if(world!= null)
            g.fillRect(0,0,world.getWidth(), world.getWidth());
        uiManager.draw(g);
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
