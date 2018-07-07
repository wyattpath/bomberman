package com.wyatt92.games.view;


import com.wyatt92.games.model.Model;

import javax.swing.*;
import java.awt.*;

class GameOverPanel extends JPanel
{
    private final Animation animGameOver;

    private final JButton startButton;
    private final JButton optionsButton;
    private final Model model;



    private final JButton quitButton;
    private final Icon  quitIcon;
    private final Icon quitIconEntered;

    public GameOverPanel(Model model) {
        this.model = model;
        animGameOver = new Animation(10000, Assets.gameOver);
        animGameOver.setRandomized(true);

        startButton = new StartButton();
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
