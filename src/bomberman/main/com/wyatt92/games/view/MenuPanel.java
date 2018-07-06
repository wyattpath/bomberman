package com.wyatt92.games.view;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel
{
    private Animation animBG;
    private Animation animLogo;
    private JButton startButton, optionsButton, quitButton;



    public MenuPanel() {

        animLogo = new Animation(10000, Assets.logo);
        animLogo.setRandomized(true);
        animBG = new Animation(5000, Assets.bg);
        animBG.setRandomized(true);


        startButton = new StartButton();
        optionsButton = new JButton("Options");
        quitButton = new QuitButton();


        this.add(startButton);
//        this.add(optionsButton);
        this.add(quitButton);
        this.setDoubleBuffered(true);
        setFocusable(true);
        setVisible(true);

    }



    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        animLogo.update();
        animBG.update();
        g.create();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
//        System.out.println(model.getWidth());
        g.drawImage(animBG.getCurrentFrame(), 0, 0, getWidth(), getHeight(), null);
        g.drawImage(animLogo.getCurrentFrame(), 0, getHeight()- animLogo.getCurrentFrame().getHeight(), 256, 128, null);

    }








    public JButton getStartButton()
    {
        return startButton;
    }


    public Animation getAnimBG()
    {
        return animBG;
    }

    public Animation getAnimLogo()
    {
        return animLogo;
    }

    public JButton getOptionsButton()
    {
        return optionsButton;
    }

    public JButton getQuitButton()
    {
        return quitButton;
    }
}
