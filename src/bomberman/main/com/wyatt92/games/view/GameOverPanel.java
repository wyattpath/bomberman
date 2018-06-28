package com.wyatt92.games.view;

import com.wyatt92.games.model.Animation;
import com.wyatt92.games.model.Assets;
import com.wyatt92.games.model.Model;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel
{
    private Animation animGameOver;



    private JButton startButton;
    private JButton optionsButton;
    private Model model;



    private JButton quitButton;
    Icon startIcon, startIconEntered, quitIcon, quitIconEntered;

    public GameOverPanel(Model model) {
        this.model = model;
        animGameOver = new Animation(10000, Assets.gameOver);
        animGameOver.setRandomized(true);

        startButton = new JButton();
        startIcon = new ImageIcon(Assets.btn_start[0]);
        startIconEntered = new ImageIcon(Assets.btn_start[1]);
        startButton.setIcon(startIcon);
        startButton.setRolloverIcon(startIconEntered);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.setOpaque(false);

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
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(model != null)
        {
            animGameOver.update();
            g.setColor(Color.BLACK);
            g.fillRect(0,0, model.getWidth(), model.getHeight());
            g.drawImage(animGameOver.getCurrentFrame(), 0,0, model.getWidth(), model.getHeight(), null);
//            g.setFont(new Font("Unispace", Font.BOLD, 60));
//            g.drawString("Game Over",world.getWidth()/3, world.getHeight()/2);
            g.setColor(Color.BLUE);
            g.setFont(new Font("Unispace", Font.BOLD, 40));

            g.drawString("The Winner is ", model.getWidth()/4, 400);
            g.setColor(Color.BLACK);
            g.fillRect(model.getWidth()/4 +40, 410, 220,50);
            g.setColor(Color.BLUE);
            g.drawString("Player " + model.getPlayerAlive(), model.getWidth()/4 + 50, 450);
        }
    }


    public JButton getQuitButton()
    {
        return quitButton;
    }

    public JButton getStartButton()
    {
        return startButton;
    }
}
