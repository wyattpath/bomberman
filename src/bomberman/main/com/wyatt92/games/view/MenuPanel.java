package com.wyatt92.games.view;


import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MenuPanel extends JPanel
{
    private Animation animBG;
    private Animation animLogo;
    private JButton startButton, optionsButton, quitButton;
    private Icon  quitIcon, quitIconEntered;
    private int r;

    public MenuPanel() {

        animLogo = new Animation(10000, Assets.logo);
        animLogo.setRandomized(true);
        animBG = new Animation(5000, Assets.bg);
        animBG.setRandomized(true);


        startButton = new StartButton();
//        startButton.addActionListener(e -> setupActionButton());
        optionsButton = new JButton("Options");

        quitButton = new JButton();
        quitIcon = new ImageIcon(Assets.btn_quit[0]);
        quitIconEntered = new ImageIcon(Assets.btn_quit[1]);
        quitButton.setRolloverIcon(quitIconEntered);
        quitButton.setIcon(quitIcon);
        quitButton.setBorderPainted(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setFocusPainted(false);
        quitButton.setOpaque(false);

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






    public void setQuitEntered(){
        quitButton.setIcon(quitIconEntered);
    }

    public void setQuitExited() {
        quitButton.setIcon(quitIcon);
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
