package com.wyatt92.games.view;


import com.wyatt92.games.model.Model;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class View
{
    private JFrame frame;
    private JPanel panel;
    private static GamePanel gamePanel;
    private static MenuPanel menuPanel;
    private static GameOverPanel gameOverPanel;
    private static OptionsPanel optionsPanel;

    private static final int FRAMEWIDTH = 13 * 64 + 32;
    private static final int FRAMEHEIGHT = 13 * 64 + 32;
    private static final String TITLE = "Bomberman";
    private final Model model;
    private int r;

    public View(Model model) {
        this.model = model;
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(FRAMEWIDTH, FRAMEHEIGHT));
        SpriteLibrary.init();
        gamePanel = new GamePanel(model);
        menuPanel = new MenuPanel();
        gameOverPanel = new GameOverPanel(model);
        optionsPanel = new OptionsPanel();
        addActionListeners();
        setPanel(menuPanel);

        //startMusic()
        r = new Random().nextInt(SpriteLibrary.menu_bgMusic.length);
        SpriteLibrary.menu_bgMusic[r].setFramePosition(0);
        SpriteLibrary.menu_bgMusic[r].start();
        SpriteLibrary.menu_bgMusic[r].loop(Clip.LOOP_CONTINUOUSLY);

        makeVisible();
    }

    private void addActionListeners()
    {
        menuPanel.getStartButton().addActionListener(e -> setUpActionButton());
        menuPanel.getStartButton().addMouseListener(addEnterSound());
        menuPanel.getOptionsButton().addActionListener(e -> setPanel(optionsPanel));
        menuPanel.getQuitButton().addActionListener(e -> System.exit(0));
        menuPanel.getQuitButton().addMouseListener(addEnterSound());

        gameOverPanel.getStartButton().addActionListener(e -> setUpActionButton());
        gameOverPanel.getStartButton().addMouseListener(addEnterSound());
        gameOverPanel.getQuitButton().addActionListener(e -> System.exit(0));
        gameOverPanel.getQuitButton().addMouseListener(addEnterSound());
    }



    private void setUpActionButton()
    {
        model.loadWorld("world1.txt");
        model.resetWorld();
        switchPanel();
    }

    private void switchMusic()
    {

        if (panel == gamePanel)
        {
            SpriteLibrary.game_bgMusic[r].stop();
            r = new Random().nextInt(SpriteLibrary.gameOver_bgMusic.length);
            SpriteLibrary.gameOver_bgMusic[r].setFramePosition(0);
            SpriteLibrary.gameOver_bgMusic[r].start();
            SpriteLibrary.gameOver_bgMusic[r].loop(Clip.LOOP_CONTINUOUSLY);
        } else if (panel == menuPanel)
        {
            SpriteLibrary.menu_bgMusic[r].stop();
            r = new Random().nextInt(SpriteLibrary.game_bgMusic.length);
            SpriteLibrary.game_bgMusic[r].setFramePosition(0);
            SpriteLibrary.game_bgMusic[r].start();
            SpriteLibrary.game_bgMusic[r].loop(Clip.LOOP_CONTINUOUSLY);
        } else
        {
            SpriteLibrary.gameOver_bgMusic[r].stop();
            r = new Random().nextInt(SpriteLibrary.game_bgMusic.length);
            SpriteLibrary.game_bgMusic[r].setFramePosition(0);
            SpriteLibrary.game_bgMusic[r].start();
            SpriteLibrary.game_bgMusic[r].loop(Clip.LOOP_CONTINUOUSLY);
        }

    }

    private MouseAdapter addEnterSound()
    {
        return new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                super.mouseEntered(e);
                SoundLibrary.playSound("cursor_move.wav");
            }
        };
    }

    public void switchPanel()
    {
        switchMusic();
        if(!model.isPlaying() && !model.isGameOver()){
            setPanel(gamePanel);
            model.setPlaying(true);
        } else if(!model.isGameOver() && model.isPlaying()) {
            setPanel(gameOverPanel);
            model.setGameOver(true);
        } else {
            model.setGameOver(false);
            model.setPlaying(true);
            setPanel(gamePanel);
        }
    }

    public void repaint(){
        frame.repaint();
    }

    private void makeVisible() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setFocusable(true);
		frame.setVisible(true);
        frame.requestFocus();
    }

    private void setPanel(JPanel panel) {
        this.panel = panel;
        frame.setContentPane(panel);
        frame.revalidate();
    }

    public JFrame getFrame()
    {
        return frame;
    }

    public void setFrame(GameFrame frame)
    {
        this.frame = frame;
    }
}
