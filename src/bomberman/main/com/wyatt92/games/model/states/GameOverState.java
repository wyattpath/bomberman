package com.wyatt92.games.model.states;



import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.World;
import com.wyatt92.games.model.ui.UIImageButton;
import com.wyatt92.games.model.ui.UIManager;
import com.wyatt92.games.model.ui.UIObject;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

public class GameOverState extends State
{
    private UIManager uiManager;
    private World world;

    public GameOverState(World world, UIManager uiManager)
    {
        super(world);
        this.world = world;
        this.uiManager = uiManager;
    }

    @Override
    public void update()
    {
        uiManager.update();
    }

    @Override
    public void draw(Graphics g)
    {
        if(world!= null)
        {
            g.setColor(Color.RED);
            g.fillRect(0,0,world.getWidth(), world.getHeight());
            g.setFont(new Font("Unispace", Font.BOLD, 60));
            g.setColor(Color.WHITE);
            g.drawString("Game Over",world.getWidth()/3, world.getHeight()/3);
            g.setFont(new Font("Unispace", Font.BOLD, 40));
            g.drawString("The Winner is Player " + getWinner(),world.getWidth()/4, world.getHeight()-200);
        }

        uiManager.draw(g);

    }

    @Override
    protected void playLoopMusic()
    {
        Assets.gameOver_bgMusic.setFramePosition(0);
        Assets.gameOver_bgMusic.start();
        Assets.gameOver_bgMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Override
    protected void stopLoopMusic()
    {
        Assets.gameOver_bgMusic.stop();
    }
}
