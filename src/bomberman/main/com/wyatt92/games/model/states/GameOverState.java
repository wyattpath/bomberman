package com.wyatt92.games.model.states;



import com.wyatt92.games.model.World;
import com.wyatt92.games.model.ui.UIObject;

import javax.swing.*;
import java.awt.*;

public class GameOverState extends State
{

    public GameOverState(World world)
    {

        this.world = world;
    }

    @Override
    public void update()
    {

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
            g.drawString("The Winner is Player " + getWinner(),world.getWidth()/3, world.getHeight()/2);
        }


    }

    @Override
    protected void playLoopMusic()
    {

    }

    @Override
    protected void stopLoopMusic()
    {

    }
}
